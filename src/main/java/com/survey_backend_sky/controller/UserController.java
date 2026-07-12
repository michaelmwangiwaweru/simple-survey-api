package com.survey_backend_sky.controller;

import com.survey_backend_sky.entity.Role;
import com.survey_backend_sky.entity.User;
import com.survey_backend_sky.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> allUsers() {

        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public User oneUser(@PathVariable Long id) {

        return userService.getUser(id);

    }

    @PostMapping
    public User create(@RequestBody User user) {

        return userService.createUser(user);

    }

    @PutMapping("/{id}")
    public User update(
            @PathVariable Long id,
            @RequestBody User user
    ) {

        return userService.updateUser(id, user);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        userService.deleteUser(id);

    }

    @PatchMapping("/{id}/role")
    public User changeRole(

            @PathVariable Long id,

            @RequestParam Role role

    ) {

        return userService.changeRole(id, role);

    }

    @PatchMapping("/{id}/status")
    public User changeStatus(

            @PathVariable Long id,

            @RequestParam Boolean enabled

    ) {

        return userService.changeStatus(id, enabled);

    }

}