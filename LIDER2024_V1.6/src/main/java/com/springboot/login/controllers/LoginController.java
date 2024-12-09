package com.springboot.login.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/client")
    public String client() {
        return "client";
    }

    @GetMapping("/postventa")
    public String postventa() {
        return "postventa";
    }

    @GetMapping("/almacen")
    public String almacen() {
        return "almacen";
    }

    @GetMapping("/restaurar-contrasena")
    public String restauraContrasena() {
        return "/pages-login/restaurar-contrasena";
    }

}
