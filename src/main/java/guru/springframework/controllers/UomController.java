package guru.springframework.controllers;

import guru.springframework.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UomController {

    private final UnitOfMeasureService unitOfMeasureService;

    public UomController(UnitOfMeasureService unitOfMeasureService) {
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping({"/uom", "/uom.html"})
    public String getUomPage(Model model) {

        model.addAttribute("uoms", unitOfMeasureService.listAllUoms());
        //model.addAttribute("basketball","some string");
        return "uom";
    }
}
