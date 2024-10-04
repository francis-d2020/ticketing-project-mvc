package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final RoleService roleService;
    private final UserService userService;


    public UserController(RoleService  roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createUser(Model model)
    {//userDTO holds fields that we dont want to show in the view
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("users",userService.findAll());
        return "/user/create"; // Without redirect we are using html file paths
    }

    //saving new user and redirect to the create page
    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("users", userService.findAll());

            return "/user/create";
        }


        userService.save(user);

        return "redirect:/user/create"; // With redirect we are using endpoints

    }


    //catch the user we want to update with PathVariable
    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user",userService.findById(username));
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("users",userService.findAll());

        return "/user/update";
    }

    //updating the user details
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user) {
        userService.update(user);

        return "redirect:/user/create";
    }

    //delete the user
    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        userService.deleteById(username);

        return "redirect:/user/create";

    }





}
