package com.example.webproject.controller;

import com.example.webproject.model.DTOS.UserRegistrationDTO;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    private final SecurityContextRepository securityContextRepository;

    @Autowired
    public AuthController(UserService userService, AuthService authService, SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.authService = authService;
        this.securityContextRepository = securityContextRepository;
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO(){
        return new UserRegistrationDTO();
    }


    //@ModelAttribute("loginDTO")
    //public UserLoginDTO initLoginDTO(){
    //    return new UserLoginDTO();
    //}

    @GetMapping("/register")
    public String register(){
        if(this.authService.isLogged()) {
            return "redirect:/home";
        }
        return "/register";
    }

    @PostMapping("/register")
    public String registerNewUser(
            @Valid UserRegistrationDTO registrationDTO,
            HttpServletRequest request,
            HttpServletResponse response) {


        userService.registerUser(registrationDTO, successfulAuth -> {
            // populating security context
            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);

            strategy.setContext(context);

            securityContextRepository.saveContext(context, request, response);
        });

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(){
        if(this.authService.isLogged()) {
            return "redirect:/home";
        }
        return "/login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/login";
    }






}
