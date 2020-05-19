package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index controller.
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String indexPage() {
        return "index";
    }

}
