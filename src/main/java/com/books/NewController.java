package com.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewController {
    @RequestMapping("/")
    public String main(Model model){
        model.addAttribute("message","new");
        return "hello";
    }
}
