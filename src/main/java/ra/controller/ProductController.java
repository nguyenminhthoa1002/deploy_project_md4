package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.*;
import ra.model.service.*;
import ra.payload.request.ProductRequest;
import ra.payload.request.SearchByPriceColorSize;
import ra.payload.request.SearchProductByColorOrSize;
import ra.payload.respone.*;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IColorService colorService;
    @Autowired
    private ISizeService sizeService;
    @Autowired
    private ICatalogService catalogService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private IProductDetailService productDetailService;

    //    -------------------------- ROLE : ADMIN & MODERATOR --------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<ProductResponse> getAllProduct() {
        List<Product> listProduct = productService.findAll();
        List<ProductResponse> listProductResponse = new ArrayList<>();
        for (Product pro : listProduct) {
            Set<String> listColorHex = new HashSet<>();
            Set<String> listSizeName = new HashSet<>();
            Set<String> listSubImageString = new HashSet<>();

            Set<ProductDetail> listProductDetail = productDetailService.findByProduct_ProductId(pro.getProductId());
            for (ProductDetail proDe : listProductDetail) {
                if(proDe.getColor().isColorStatus()){
                listColorHex.add(proDe.getColor().getColorHex());}
                if(proDe.getSize().isSizeStatus()){
                listSizeName.add(proDe.getSize().getSizeName());}
            }
            for (Image image : pro.getListSubImage()) {
                if (image.isImageStatus()){
                listSubImageString.add(image.getImageLink());}
            }
            ProductResponse productResponse = new ProductResponse(pro.getProductId(), pro.getProductName(), pro.getProductDescription(),
                    pro.getProductImportPrice(), pro.getProductExportPrice(), pro.getTotalQuantity(), pro.getProductImg(), pro.getCatalog().getCatalogName(),
                    pro.getProductCreateDate(), pro.isProductStatus(), listColorHex, listSizeName, listSubImageString);
            listProductResponse.add(productResponse);
        }
        return listProductResponse;
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ProductResponse getProductById(@PathVariable("productId") int productId) {
        Product pro = (Product) productService.findById(productId);
        Set<String> listColorName = new HashSet<>();
        Set<String> listSizeName = new HashSet<>();
        Set<String> listSubImageString = new HashSet<>();
        Set<ProductDetail> listProductDetail = productDetailService.findByProduct_ProductId(productId);
        for (ProductDetail proDe : listProductDetail) {
            listColorName.add(proDe.getColor().getColorName());
            listSizeName.add(proDe.getSize().getSizeName());
        }

        for (Image image : pro.getListSubImage()) {
            listSubImageString.add(image.getImageLink());
        }
        ProductResponse productResponse = new ProductResponse(pro.getProductId(), pro.getProductName(), pro.getProductDescription(),
                pro.getProductImportPrice(), pro.getProductExportPrice(), pro.getTotalQuantity(), pro.getProductImg(), pro.getCatalog().getCatalogName(),
                pro.getProductCreateDate(), pro.isProductStatus(), listColorName, listSizeName, listSubImageString);
        return productResponse;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ProductResponse createProduct(@RequestBody ProductRequest product) {
        Product proNew = new Product();
        proNew.setProductName(product.getProductName());
        proNew.setProductDescription(product.getProductDescription());
        proNew.setProductImportPrice(product.getProductImportPrice());
        proNew.setProductExportPrice((float) (product.getProductImportPrice() * 1.2));
        proNew.setProductImg(product.getProductImg());
        Catalog catalog = (Catalog) catalogService.findById(product.getCatalogId());
        proNew.setCatalog(catalog);
        LocalDateTime time = LocalDateTime.now();
        proNew.setProductCreateDate(time);
        proNew.setProductStatus(true);
        Set<Color> listColor = colorService.findByColorIdIn(product.getColorStrArr());
        proNew.setListColor(listColor);
        Set<Size> listSize = sizeService.findBySizeIdIn(product.getSizeStrArr());
        proNew.setListSize(listSize);
        productService.saveOrUpdate(proNew);
        Set<String> listSubImage = new HashSet<>();
        for (String str : product.getListSubImage()) {
            listSubImage.add(str);
            Image image = new Image();
            image.setImageLink(str);
            image.setImageStatus(true);
            image.setProduct(proNew);
            imageService.saveOrUpdate(image);
        }

        Set<String> listColorName = new HashSet<>();
        for (Color color : listColor) {
            listColorName.add(color.getColorName());
        }
        Set<String> listSizeName = new HashSet<>();
        for (Size size : listSize) {
            listSizeName.add(size.getSizeName());
        }

        ProductResponse productResponse = new ProductResponse(proNew.getProductId(), proNew.getProductName(), proNew.getProductDescription(),
                proNew.getProductImportPrice(), proNew.getProductExportPrice(), proNew.getTotalQuantity(), proNew.getProductImg(), proNew.getCatalog().getCatalogName(),
                proNew.getProductCreateDate(), proNew.isProductStatus(), listColorName, listSizeName, listSubImage);
        return productResponse;

    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ProductResponse updateProduct(@PathVariable("productId") int productId, @RequestBody ProductRequest product) {
        Product productUpdate = (Product) productService.findById(productId);
        Catalog catalog = (Catalog) catalogService.findById(product.getCatalogId());
        Set<ProductDetail> listProDetail = productDetailService.findByProduct_ProductId(productId);
        Set<ProductDetail> listProDetail1 = productDetailService.findByProductDetailIdIn(product.getProDetailArr());

        productUpdate.setProductName(product.getProductName());
        productUpdate.setProductDescription(product.getProductDescription());
        productUpdate.setProductImportPrice(product.getProductImportPrice());
        productUpdate.setProductExportPrice((float) (product.getProductImportPrice() * 1.2));
        productUpdate.setProductImg(product.getProductImg());
        LocalDateTime time = LocalDateTime.now();
        productUpdate.setProductCreateDate(time);
        productUpdate.setProductStatus(product.isProductStatus());
        productUpdate.setCatalog(catalog);
        Set<Color> listColor = colorService.findByColorIdIn(product.getColorStrArr());
        productUpdate.setListColor(listColor);
        Set<Size> listSize = sizeService.findBySizeIdIn(product.getSizeStrArr());
        productUpdate.setListSize(listSize);

        List<Image> listSubImage = imageService.findAll();
        Set<String> listImageStrExist = new HashSet<>();

        for (Image img : listSubImage) {
            listImageStrExist.add(img.getImageLink());
            listImageStrExist.add(img.getImageLink());
        }

//        Request product truyen len
        Set<String> listSub = new HashSet<>();
        for (String img : product.getListSubImage()) {
            listSub.add(img);
        }

        ArrayList<String> listNewImgLink = new ArrayList<>();
        for (String imgLink : listSub) {
            boolean test = listImageStrExist.add(imgLink);
            if (test) {
                listNewImgLink.add(imgLink);
            }
        }

        for (String strLinkImg : listNewImgLink) {
            Image image = new Image();
            image.setImageLink(strLinkImg);
            image.setImageStatus(true);
            image.setProduct(productUpdate);
            imageService.saveOrUpdate(image);
        }


        Set<Image> listSubImgUpdate = new HashSet<>();
        for (String strSubImage : product.getListSubImage()) {
            Image subImg = imageService.findByImageLink(strSubImage);
            listSubImgUpdate.add(subImg);
        }
        productUpdate.setListSubImage(listSubImgUpdate);

        if (listProDetail != null) {
            if (product.isProductStatus()) {
                int totalQuantity = 0;
                for (ProductDetail pd : listProDetail) {
                    pd.setProductDetailStatus(true);
                    totalQuantity += pd.getQuantity();
                    productDetailService.saveOrUpdate(pd);
                }
                productUpdate.setTotalQuantity(totalQuantity);
            } else {
                for (ProductDetail pd : listProDetail) {
                    pd.setProductDetailStatus(false);
                    productDetailService.saveOrUpdate(pd);
                }
                productUpdate.setTotalQuantity(0);
            }
        }

        productService.saveOrUpdate(productUpdate);


        Set<String> listSubImageResponse = new HashSet<>();
        for (Image img : listSubImgUpdate) {
            listSubImageResponse.add(img.getImageLink());
        }

        Set<String> listColorName = new HashSet<>();
        for (Color color : listColor) {
            listColorName.add(color.getColorName());
        }
        Set<String> listSizeName = new HashSet<>();
        for (Size size : listSize) {
            listSizeName.add(size.getSizeName());
        }

        ProductResponse productResponse = new ProductResponse(productUpdate.getProductId(), productUpdate.getProductName(), productUpdate.getProductDescription(),
                productUpdate.getProductImportPrice(), productUpdate.getProductExportPrice(), productUpdate.getTotalQuantity(), productUpdate.getProductImg(), productUpdate.getCatalog().getCatalogName(),
                productUpdate.getProductCreateDate(), productUpdate.isProductStatus(), listColorName, listSizeName, listSubImageResponse);
        return productResponse;
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void deleteProduct(@PathVariable("productId") int productId) {
        productService.delete(productId);
        Product productDelete = (Product) productService.findById(productId);
        Set<ProductDetail> listProDetail = productDetailService.findByProduct_ProductId(productId);
        if (listProDetail != null) {
            for (ProductDetail pd : listProDetail) {
                pd.setProductDetailStatus(false);
                productDetailService.saveOrUpdate(pd);
            }
            productDelete.setTotalQuantity(0);
        }
        productService.saveOrUpdate(productDelete);
    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<ProductResponse> searchProduct(@RequestParam("searchName") String searchName) {
        List<Product> listPro = productService.searchProduct(searchName);
        List<ProductResponse> listProResponse = new ArrayList<>();
        for (Product pro : listPro) {
            ProductResponse pr = new ProductResponse();
            pr.setProductId(pro.getProductId());
            pr.setProductName(pro.getProductName());
            pr.setProductDescription(pro.getProductDescription());
            pr.setProductImportPrice(pro.getProductImportPrice());
            pr.setProductExportPrice(pro.getProductExportPrice());
            pr.setProductImg(pro.getProductImg());
            pr.setCatalogName(pro.getCatalog().getCatalogName());
            pr.setProductCreateDate(pro.getProductCreateDate());
            pr.setProductStatus(pro.isProductStatus());
            Set<String> listColorName = new HashSet<>();
            for (Color color : pro.getListColor()) {
                listColorName.add(color.getColorName());
            }
            pr.setListColorName(listColorName);
            Set<String> listSizeName = new HashSet<>();
            for (Size size : pro.getListSize()) {
                listSizeName.add(size.getSizeName());
            }
            pr.setListSizeName(listSizeName);
            Set<String> listSubImage = new HashSet<>();
            for (Image img : pro.getListSubImage()) {
                listSubImage.add(img.getImageLink());
            }
            pr.setListSubImage(listSubImage);
            listProResponse.add(pr);
        }
        return listProResponse;
    }

    @GetMapping("/getAllProductByCatalogId")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Set<ProductResponse> getAllProductByCatalogId(@RequestParam("catId") int catId) {
        Set<Product> listPro = productService.findByCatalog_CatalogId(catId);
        Set<ProductResponse> listProResponse = new HashSet<>();
        for (Product pro : listPro) {
            ProductResponse pr = new ProductResponse();
            pr.setProductId(pro.getProductId());
            pr.setProductName(pro.getProductName());
            pr.setProductDescription(pro.getProductDescription());
            pr.setProductImportPrice(pro.getProductImportPrice());
            pr.setProductExportPrice(pro.getProductExportPrice());
            pr.setProductImg(pro.getProductImg());
            pr.setCatalogName(pro.getCatalog().getCatalogName());
            pr.setProductCreateDate(pro.getProductCreateDate());
            pr.setProductStatus(pro.isProductStatus());
            Set<String> listColorName = new HashSet<>();
            for (Color color : pro.getListColor()) {
                listColorName.add(color.getColorName());
            }
            pr.setListColorName(listColorName);
            Set<String> listSizeName = new HashSet<>();
            for (Size size : pro.getListSize()) {
                listSizeName.add(size.getSizeName());
            }
            pr.setListSizeName(listSizeName);
            Set<String> listSubImage = new HashSet<>();
            for (Image img : pro.getListSubImage()) {
                listSubImage.add(img.getImageLink());
            }
            pr.setListSubImage(listSubImage);
            listProResponse.add(pr);
        }
        return listProResponse;
    }

    @PostMapping("initCreateOrUpdate")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public InforForCreateOrUpdateProduct initCreateOrUpdate() {
        List<Catalog> listCat = catalogService.getCatalogForCreateProduct();
        List<CatalogResponse> listCatalogRes = new ArrayList<>();
        for (Catalog cat : listCat) {
            CatalogResponse catalogResponse = new CatalogResponse();
            catalogResponse.setCatalogId(cat.getCatalogId());
            catalogResponse.setCatalogName(cat.getCatalogName());
            listCatalogRes.add(catalogResponse);
        }

        List<Color> listColor = colorService.getColorForUser();
        List<ColorResponse> listColorRes = new ArrayList<>();
        for (Color color : listColor) {
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setColorId(color.getColorId());
            colorResponse.setColorHex(color.getColorHex());
            colorResponse.setColorName(color.getColorName());
            listColorRes.add(colorResponse);
        }

        List<Size> listSize = sizeService.getSizeForUser();
        List<SizeResponse> listSizeRes = new ArrayList<>();
        for (Size size : listSize) {
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setSizeId(size.getSizeId());
            sizeResponse.setSizeName(size.getSizeName());
            listSizeRes.add(sizeResponse);
        }

        InforForCreateOrUpdateProduct infor = new InforForCreateOrUpdateProduct();
        infor.setListCatalog(listCatalogRes);
        infor.setListColor(listColorRes);
        infor.setListSize(listSizeRes);
        return infor;
    }

    //    -------------------------- ROLE : USER --------------------
    @GetMapping("/displayProduct")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<ProductResponseForUser> displayProduct() {
        Set<Product> listProduct = productService.findByProductStatusIsTrue();
        List<ProductResponseForUser> productResponseForUserList = new ArrayList<>();
        for (Product pro : listProduct) {
            ProductResponseForUser proRes = new ProductResponseForUser();
            proRes.setProductId(pro.getProductId());
            proRes.setProductName(pro.getProductName());
            proRes.setProductExportPrice(pro.getProductExportPrice());
            proRes.setProductImg(pro.getProductImg());
            Set<String> listSubImage = new HashSet<>();
            if (pro.getListSubImage().size() != 0) {
                for (Image img : pro.getListSubImage()) {
                    listSubImage.add(img.getImageLink());
                }
            }
            proRes.setListSubImage(listSubImage);
            productResponseForUserList.add(proRes);
        }
        return productResponseForUserList;
    }

    @GetMapping("/searchProductByProductExportPrice")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<ProductResponseForUser> searchProductByProductExportPriceBetween(@RequestParam("min") float min, @RequestParam("max") float max) {
        List<Product> listProduct = productService.searchProductByProductExportPriceBetween(min, max);
        List<ProductResponseForUser> responseForUserList = new ArrayList<>();
        for (Product pro : listProduct) {
            ProductResponseForUser prfu = new ProductResponseForUser();
            prfu.setProductId(pro.getProductId());
            prfu.setProductName(pro.getProductName());
            prfu.setProductExportPrice(pro.getProductExportPrice());
            prfu.setProductImg(pro.getProductImg());
            Set<String> listSubImage = new HashSet<>();
            if (pro.getListSubImage().size() != 0) {
                for (Image img : pro.getListSubImage()) {
                    listSubImage.add(img.getImageLink());
                }
            }
            prfu.setListSubImage(listSubImage);
            responseForUserList.add(prfu);
        }
        return responseForUserList;
    }

    @GetMapping("/searchProductByColor")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<ProductResponseForUser> searchProductByColorName(@RequestBody SearchProductByColorOrSize search) {
        Set<Color> listColor = colorService.findByColorIdIn(search.getSearch());
        Set<Product> listSearch = productService.findByListColorIn(listColor);

        List<ProductResponseForUser> responseForUserList = new ArrayList<>();
        for (Product product : listSearch) {
            ProductResponseForUser proRes = new ProductResponseForUser();
            proRes.setProductId(product.getProductId());
            proRes.setProductName(product.getProductName());
            proRes.setProductExportPrice(product.getProductExportPrice());
            proRes.setProductImg(product.getProductImg());
            Set<String> listSubImage = new HashSet<>();
            if (product.getListSubImage().size() != 0) {
                for (Image img : product.getListSubImage()) {
                    listSubImage.add(img.getImageLink());
                }
            }
            proRes.setListSubImage(listSubImage);
            responseForUserList.add(proRes);
        }
        return responseForUserList;
    }

    @GetMapping("/searchProductBySize")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<ProductResponseForUser> searchProductBySizeName(@RequestBody SearchProductByColorOrSize search) {
        Set<Size> listSize = sizeService.findBySizeIdIn(search.getSearch());
        Set<Product> listSearch = productService.findByListSizeIn(listSize);

        List<ProductResponseForUser> responseForUserList = new ArrayList<>();
        for (Product product : listSearch) {
            ProductResponseForUser proRes = new ProductResponseForUser();
            proRes.setProductId(product.getProductId());
            proRes.setProductName(product.getProductName());
            proRes.setProductExportPrice(product.getProductExportPrice());
            proRes.setProductImg(product.getProductImg());
            Set<String> listSubImage = new HashSet<>();
            if (product.getListSubImage().size() != 0) {
                for (Image img : product.getListSubImage()) {
                    listSubImage.add(img.getImageLink());
                }
            }
            proRes.setListSubImage(listSubImage);
            responseForUserList.add(proRes);
        }
        return responseForUserList;
    }

    @GetMapping("searchProductByColorSizePrice")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<ProductResponseForUser> findByListColorInAndListSizeInAndProductExportPriceBetween(@RequestBody SearchByPriceColorSize search) {
        Set<Color> listColor = colorService.findByColorIdIn(search.getListColor());
        Set<Size> listSize = sizeService.findBySizeIdIn(search.getListsize());
        float min = search.getMin();
        float max = search.getMax();
        Set<Product> listSearch = productService.findByListColorInAndListSizeInAndProductExportPriceBetween(listColor, listSize, min, max);
        List<ProductResponseForUser> responseForUserList = new ArrayList<>();
        for (Product product : listSearch) {
            ProductResponseForUser proRes = new ProductResponseForUser();
            proRes.setProductId(product.getProductId());
            proRes.setProductName(product.getProductName());
            proRes.setProductExportPrice(product.getProductExportPrice());
            proRes.setProductImg(product.getProductImg());
            Set<String> listSubImage = new HashSet<>();
            if (product.getListSubImage().size() != 0) {
                for (Image img : product.getListSubImage()) {
                    listSubImage.add(img.getImageLink());
                }
            }
            proRes.setListSubImage(listSubImage);
            responseForUserList.add(proRes);
        }
        return responseForUserList;
    }
}
