package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserCotroller {

    @RequestMapping("/auth")
    @ResponseBody
    public String auth() {
        return "{ id: 1, name: \"foo\", token: \"123456\" }";
    }

}
