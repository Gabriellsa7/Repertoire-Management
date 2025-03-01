package com.academico.webproject.controller;

import com.academico.webproject.model.Band;
import com.academico.webproject.model.User;
import com.academico.webproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    //Remember Comment All the Code to increase my Explanation
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        User createUser = userService.createUser(user);
        return ResponseEntity.ok(createUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content to indicate successful deletion
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{userId}/bands")
    public ResponseEntity<List<Band>> getBandsByUserId(@PathVariable User userId) {
        List<Band> bands = userService.getBandsByUserId(userId);
        if (bands.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bands);
    }

    @GetMapping("/{bandId}/members")
    public ResponseEntity<List<User>> getMembersByBand(@PathVariable String bandId) {
        List<User> members = userService.getMembersByBandId(bandId);
        return ResponseEntity.ok(members);
    }

}
