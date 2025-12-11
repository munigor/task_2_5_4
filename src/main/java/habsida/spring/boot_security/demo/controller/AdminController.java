package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.OnCreate;
import habsida.spring.boot_security.demo.dto.OnUpdate;
import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model
            .addAttribute("user", userService.getDto())
            .addAttribute("title", "Add User")
            .addAttribute("url", "/admin/add")
            .addAttribute("roles", roleService.findAll());
        return "admin/form";
    }

    @PostMapping("/add")
    public String addUser(
        @Validated(OnCreate.class) @ModelAttribute(name = "user") UserDto dto,
        BindingResult result,
        Model model) {

        if(result.hasErrors()) {
            model
                .addAttribute("title", "Add User")
                .addAttribute("url", "/admin/add")
                .addAttribute("roles", roleService.findAll());
            return "admin/form";
        }
        userService.add(dto);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long userId, Model model) {
        model
            .addAttribute("user", userService.getDto(userId))
            .addAttribute("title", "Update User")
            .addAttribute("url", "/admin/edit")
            .addAttribute("roles", roleService.findAll());
        return "admin/form";
    }

    @PostMapping("/edit")
    public String editUser(
        @Validated(OnUpdate.class) @ModelAttribute(name = "user")  UserDto dto,
        BindingResult result,
        Model model) {

        if(result.hasErrors()) {
            model
                .addAttribute("title", "Update User")
                .addAttribute("url", "/admin/edit")
                .addAttribute("roles", roleService.findAll());
            return "admin/form";
        }
        userService.update(dto);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String remove(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return "redirect:/admin";
    }
}
