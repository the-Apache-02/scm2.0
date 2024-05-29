package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.form.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String testing(){
        return "/testing";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Smart contact manager");
        model.addAttribute("youtube", "learning bird");
        model.addAttribute("link", "https://google.com");
        System.out.println("home page handler");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("isLoggedin", false);
        System.out.println("abot page handler");
        return "about";
    }

    @RequestMapping("/service")
    public String service(Model model) {
        // model.addAttribute("isLoggedin", false);
        System.out.println("servicepage handler");
        return "service";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        // userForm.setUsername("namo");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session) {
        // validate the userForm data

        if(rBindingResult.hasErrors()){
            System.out.println("errors");
            return "register";
        }
        // convert the userForm data to user
        System.out.println(userForm.toString());
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhonenumber(userForm.getPhonenumber());
        user.setAbout(userForm.getAbout());
        // call the service and save the data
        userService.createUser(user);
        Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);

        return "redirect:/register";
    }

}
