package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.controller.UserController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.Cookie;  // Correct import for Jakarta Cookie
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;  // For Spring Boot 2.4+ and Jakarta EE


import com.example.demo.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
    
    @Autowired
    private UserRepository userRepository;  // Make sure this line is added


    @GetMapping("/hi")
    public String sayHi() {
        return "Hi, welcome to the Spring Boot application!";
    }
   
    @RequestMapping(value = "/testpost", method = RequestMethod.POST)
    public String testpost() {
        return "ok";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<String> userSignIn(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            // Assuming userRepository is already injected
            User user = userRepository.findByUsername(username);
    
            if (user != null && user.getPassword().equals(password)) {
                // For demonstration purposes, a fake JWT is being returned
                // If authentication is successful, generate the token
                String token = JwtTokenUtil.generateToken(username);
                setJwtCookie(response,token);
                // Create a JSON response containing the token
                return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            // Log the exception (you can use a logger here)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/protected")
    public ResponseEntity<?> protectedRoute(HttpServletRequest request) {
        // Retrieve JWT from HttpOnly cookie
        String token = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token found");
        }

        // Validate the token (e.g., using JwtTokenUtil)
        try {
            String username = JwtTokenUtil.validateTokenAndGetUsername(token); // Validate token and extract username
            return ResponseEntity.ok("Welcome, " + username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }

    private void setJwtCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);  // Makes the cookie inaccessible to JavaScript
        cookie.setSecure(true);    // Ensures the cookie is sent over HTTPS
        cookie.setPath("/");       // Makes the cookie available for the entire domain
        cookie.setMaxAge(60 * 60 * 24);  // Set expiration time (e.g., 1 day)
    
        // Manually set the SameSite attribute (workaround for older versions)
        response.addCookie(cookie);
        
        // Manually add the SameSite attribute via the "Set-Cookie" header
        response.setHeader("Set-Cookie", "jwt=" + token + "; HttpOnly; Secure; Path=/; Max-Age=86400; SameSite=Strict");
    }

    // @RequestMapping(value = "/signin", method = RequestMethod.POST)
    // public int userSignIn() {
    //     return 12;
    //     // try {
    //     //     String username = loginRequest.getUsername();
    //     //     String password = loginRequest.getPassword();
    
    //     //     // Use the injected repository to find the user by username
    //     //     User user = userRepository.findByUsername(username);
    
    //     //     if (user != null && user.getPassword().equals(password)) {
    //     //         String fakeToken = "jwt-token-sample"; // Placeholder for real JWT
    //     //         return ResponseEntity.ok(fakeToken);
    //     //     } else {
    //     //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    //     //     }
    
    //     // } catch (Exception e) {
    //     //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    //     // }    
    // }
    
}


// package com.example.demo.controller;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.json.JSONObject;

// @RestController
// public class GreetingController {

//     @GetMapping("/hi")
//     public String sayHi() {
//         return "Hi, welcome to the Spring Boot application!";
//     }

    
    
//         // @PostMapping("/signin")
//         // public int userSignIn(@RequestBody String loginObject) {
//         //     try {
//         //         // Parse the login object, assuming it's a JSON string with username and
//         //         // password
//         //         JSONObject loginDetails = new JSONObject(loginObject);
//         //         String username = loginDetails.getString("username");
//         //         String password = loginDetails.getString("password");

//         //         // // Example: Check credentials in database or service
//         //         // boolean isAuthenticated = authenticationService.authenticate(username, password);

//         //         // if (isAuthenticated) {
//         //         //     // You can generate a JWT token here
//         //         //     String jwtToken = authenticationService.generateJwtToken(username);
//         //         //     return ResponseEntity.ok(jwtToken); // Return token or status as needed
//         //         // } else {
//         //         //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//         //         // }

//         //     } catch (Exception e) {

//         //     }

//         //     return 1;
//         // }
// }
