package com.ra.controller.permitAll;


import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.request.UserRegister;
import com.ra.service.email.EmailService;
import com.ra.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/log-in")
    public ResponseEntity<?> handleLogin(@RequestBody @Valid UserLogin userLogin){
        return userService.handleLogin(userLogin);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> handleRegister(@RequestBody @Valid UserRegister userRegister){
        return userService.handleRegister(userRegister);
    }

//    @GetMapping("/email")
//    public ResponseEntity<?> email(){
//        emailService.sendMail();
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
}
