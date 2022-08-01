package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hi")
    public String hello(Model model){
        model.addAttribute("data","hello");
        return "hello";


    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody   //html의 body부에 내가 직접 return 하겠다.
    public String helloString(@RequestParam("name")String name){
        return "hello" + name;  //view에 내리는게 아니라 그대로 문자를 보냄
    }

    @GetMapping("hello-api")

    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name) {

        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }
    static class Hello{
        private String name;

        public String getName() {   //변수가 private이라서 변수에 접근하려면 얘 써야됨(set도)
            return name;

        }

        public void setName(String name) {
            this.name = name;
        }
    }

}


