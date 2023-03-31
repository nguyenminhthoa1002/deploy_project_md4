package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Color;
import ra.model.entity.Size;
import ra.model.service.ISizeService;
import ra.payload.request.SearchProductByColorOrSize;
import ra.payload.respone.SizeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/size")
public class SizeController {

    @Autowired
    private ISizeService sizeService;

    //    -------------------------- ROLE : ADMIN & MODERATOR --------------------

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Size> getAllSize() {
        return sizeService.findAll();
    }

    @GetMapping("/{sizeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Size getById(@PathVariable("sizeId") int sizeId) {
        return (Size) sizeService.findById(sizeId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Size createSize(@RequestBody Size size) {
        size.setSizeStatus(true);
        return (Size) sizeService.saveOrUpdate(size);
    }

    @PutMapping("/{sizeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Size updateSize(@PathVariable("sizeId") int sizeId, @RequestBody Size size) {
        Size sizeUpdate = (Size) sizeService.findById(sizeId);
        sizeUpdate.setSizeName(size.getSizeName());
        sizeUpdate.setSizeStatus(size.isSizeStatus());
        return (Size) sizeService.saveOrUpdate(sizeUpdate);
    }

    @DeleteMapping("/{sizeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void deleteSize(@PathVariable("sizeId") int sizeId) {
        sizeService.delete(sizeId);
    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Size> searchSize(@RequestParam("searchName") String searchName) {
        return sizeService.searchSize(searchName);
    }

    @PostMapping("getListSizeForSearch")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Set<Color> findBySizeIdIn(@RequestBody SearchProductByColorOrSize search){
        return sizeService.findBySizeIdIn(search.getSearch());
    }

    //    -------------------------- ROLE : USER --------------------
    @GetMapping("/getSizeForUser")
//    @PreAuthorize("hasRole('USER')")
    public List<SizeResponse> getSizeForUser() {
        List<Size> listSize = sizeService.getSizeForUser();
        List<SizeResponse> list = new ArrayList<>();
        for (Size size : listSize) {
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setSizeId(size.getSizeId());
            sizeResponse.setSizeName(size.getSizeName());
            list.add(sizeResponse);
        }
        return list;
    }
}
