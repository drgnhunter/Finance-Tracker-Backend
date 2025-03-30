package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

@RestController
public class GreetingController {

    @GetMapping("/hi")
    public String sayHi() {
        return "Hi, welcome to the Spring Boot application!";
    }

    @PostMapping("/signin")
    public int userSignIn(@RequestBody String loginObject) {
        try {
            // Parse the login object, assuming it's a JSON string with username and
            // password
            JSONObject loginDetails = new JSONObject(loginObject);
            String username = loginDetails.getString("username");
            String password = loginDetails.getString("password");

            // // Example: Check credentials in database or service
            // boolean isAuthenticated = authenticationService.authenticate(username, password);

            // if (isAuthenticated) {
            //     // You can generate a JWT token here
            //     String jwtToken = authenticationService.generateJwtToken(username);
            //     return ResponseEntity.ok(jwtToken); // Return token or status as needed
            // } else {
            //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            // }

        } catch (Exception e) {

        }

        return 1;
    }
}
