package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/site")
public class SiteController {

    @RequestMapping("/list")
    @ResponseBody
    public String Index() {
        return "{\"code\":16546546546}";
    }

}
