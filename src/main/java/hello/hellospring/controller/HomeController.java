package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")            //localhost8080으로 들어갈시
    public String home(){

        return "home";
    }

}
