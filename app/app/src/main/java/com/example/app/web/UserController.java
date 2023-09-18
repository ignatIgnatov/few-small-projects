package com.example.app.web;

import com.example.app.model.view.UserPasswordViewModel;
import com.example.app.model.view.UserViewModel;
import com.example.app.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Provider;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<UserViewModel> getUserById(@PathVariable("id") Long id) {
        Optional<UserViewModel> userViewModel = userService.getUserById(id);

        if (userViewModel.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userViewModel.get());
    }

    @GetMapping("/admin/all")
    ResponseEntity<List<UserViewModel>> getAllUsers(Principal principal) {

        String name = principal.getName();
        List<UserViewModel> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserViewModel> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody UserViewModel userViewModel) {

        Long updatedUserId = userService.updateUser(userViewModel);

        if (updatedUserId == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pwd/{id}")
    ResponseEntity<UserPasswordViewModel> updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UserPasswordViewModel userPasswordViewModel
    ) {
        Long updatedUserId = userService.updatePassword(userPasswordViewModel);

        if (updatedUserId == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<UserViewModel> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
