package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/site")
@CrossOrigin
public class SiteController {

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        return "{\"code\":16546546546}";
    }

}
