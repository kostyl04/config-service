package com.kostylenko.config_service.config_service_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/web", "/web/meta"})
    public String test() {
        return "/index";
    }
}
