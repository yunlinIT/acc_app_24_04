package com.koreait.exam.acc_app_2024_04.app.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
public class AdmHomeController {

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showIndex(){
        return "redirect:/adm/home/main";
    }

    @GetMapping("/home/main")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showMain() {
        return "adm/home/main";
    }
}
