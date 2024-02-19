package com.ra.controller.user;

import com.ra.model.dto.request.UserToUpdateInfor;
import com.ra.model.dto.request.UserToUpdatePassword;
import com.ra.model.dto.response.UserResponseToUser;
import com.ra.model.entity.User;
import com.ra.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user/account")
public class UserAccountController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<UserResponseToUser> displayInforAcc(){
        UserResponseToUser userResponseToUser = userService.displayInforAcc();
        return new ResponseEntity<>(userResponseToUser, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UserResponseToUser> updateInforAccount(@RequestBody @Valid UserToUpdateInfor userToUpdateInfor){
        User userUpdate = userService.updateInforAcc(userToUpdateInfor);
        return new ResponseEntity<>(userService.displayUserToUser(userUpdate), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> updatePasswordAccount(@RequestBody @Valid UserToUpdatePassword userToUpdatePassword){
        userService.updatePasswordAcc(userToUpdatePassword);
        return new ResponseEntity<>("Change password success!",HttpStatus.OK);
    }

}
