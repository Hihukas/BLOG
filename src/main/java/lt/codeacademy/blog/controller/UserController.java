package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.data.User;
import lt.codeacademy.blog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/public/registration")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/save")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/save")
    public String createUser(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.createUser(user);

        redirectAttributes.addFlashAttribute("message", "lt.codeacademy.blog.registration.page.registration.success");

        return "redirect:/public/registration/save";
    }
}
