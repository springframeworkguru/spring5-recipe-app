package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"","/","index"})
    public String getIndexPage(){
        System.out.println("Something...changed");
        return "index"; //ligado a una pagina de inicio
    }
}
