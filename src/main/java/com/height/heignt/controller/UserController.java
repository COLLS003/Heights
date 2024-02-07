package com.height.heignt.controller;


import com.height.heignt.models.User;
import com.height.heignt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//set the config url
@RequestMapping(path="/users")
public class UserController {
    //get user repository bean
    @Autowired
    private UserRepository userRepository;


    //add a new user on post request
    @PostMapping(path = "/create")
    public @ResponseBody  String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String password){
        //response body states that the string is in the respons and not the view
        //requestParam means it is a parameter from the get and post  request

        //create a new user object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        //save the user via the repository
        userRepository.save(user);
        return  "user added ";

    }
    //read all the users in the system
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    //login controller
    @PostMapping(path = "/login")
    public @ResponseBody ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        User response = userRepository.findByEmail(email);
        if (response != null) {
            // User with the provided email exists in the database
            // Now, check if the provided password matches the stored password
            if (response.getPassword().equals(password)) {
                // Password matches, user is successfully authenticated
                return ResponseEntity.ok(response); // Return user details
            } else {
                // Password does not match, return an error message
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            // User with the provided email does not exist in the database
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


}
