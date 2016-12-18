package com.testcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "login";
    }
}
