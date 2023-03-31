package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Catalog;
import ra.model.entity.Color;
import ra.model.service.IColorService;
import ra.payload.request.SearchProductByColorOrSize;
import ra.payload.respone.CatalogResponse;
import ra.payload.respone.ColorResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/color")
public class ColorController {

    @Autowired
    private IColorService colorService;

    //    -------------------------- ROLE : ADMIN & MODERATOR --------------------
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Color> getAllColor() {
        return colorService.findAll();
    }

    @GetMapping("/{colorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Color getColorById(@PathVariable("colorId") int colorId) {
        return (Color) colorService.findById(colorId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Color createColor(@RequestBody Color color) {
        color.setColorStatus(true);
        return (Color) colorService.saveOrUpdate(color);
    }

    @PutMapping("/{colorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Color updateColor(@PathVariable("colorId") int colorId, @RequestBody Color color) {
        Color colorUpdate = (Color) colorService.findById(colorId);
        colorUpdate.setColorHex(color.getColorHex());
        colorUpdate.setColorName(color.getColorName());
        colorUpdate.setColorStatus(color.isColorStatus());
        return (Color) colorService.saveOrUpdate(colorUpdate);
    }

    @DeleteMapping("/{colorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void deleteColor(@PathVariable("colorId") int colorId) {
        colorService.delete(colorId);
    }

    @GetMapping("search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Color> searchColor(@RequestParam("searchName") String searchName) {
        return colorService.searchColor(searchName);
    }

    @PostMapping("getListColorForSearch")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Set<Color> findByColorIdIn(@RequestBody SearchProductByColorOrSize search){
        return colorService.findByColorIdIn(search.getSearch());
    }

    //    -------------------------- ROLE : USER --------------------
    @GetMapping("getColorForUser")
//    @PreAuthorize("hasRole('USER')")
    public List<ColorResponse> getColorForUser() {
        List<Color> listColor = colorService.getColorForUser();
        List<ColorResponse> list = new ArrayList<>();
        for (Color color : listColor) {
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setColorId(color.getColorId());
            colorResponse.setColorHex(color.getColorHex());
            colorResponse.setColorName(color.getColorName());
            list.add(colorResponse);
        }
        return list;
    }
}
