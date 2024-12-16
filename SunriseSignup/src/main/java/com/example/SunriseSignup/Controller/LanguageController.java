package com.example.SunriseSignup.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LanguageController {

    @GetMapping("/lang")
    public ModelAndView index(HttpServletRequest request) {
        // Default language is 'en'
        String language = request.getParameter("lang");
        if (language == null) {
            language = "en";
        }
        // Redirect to the appropriate language folder within multilingual-website/translated_html
        return new ModelAndView("redirect:/multilingual-website/translated_html/" + language + "/index.html");
    }
}
