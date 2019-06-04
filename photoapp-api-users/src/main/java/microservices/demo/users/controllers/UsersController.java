package microservices.demo.users.controllers;

import microservices.demo.users.model.CreateUserRequestModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        return "Create user method called.";
    }
}
