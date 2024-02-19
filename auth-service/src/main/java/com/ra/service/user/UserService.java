package com.ra.service.user;


import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.request.UserRegister;
import com.ra.model.dto.request.UserToUpdateInfor;
import com.ra.model.dto.request.UserToUpdatePassword;
import com.ra.model.dto.response.UserResponseToAdmin;
import com.ra.model.dto.response.UserResponseToUser;
import com.ra.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    User userLogin();
    ResponseEntity<?> handleLogin(UserLogin userLogin);
    ResponseEntity<String> handleRegister(UserRegister userRegister);

    Page<UserResponseToAdmin> getAll(Pageable pageable);
    User updateStatus(Long id);
    List<User> findByUsername(String keyword);
    List<UserResponseToAdmin> findByKeyWord(String keyWord);
    User findById(Long id);
    User save(User user);
    User inforLoginAcc();
    User updateInforAcc(UserToUpdateInfor userToUpdateInfor);
    void updatePasswordAcc(UserToUpdatePassword userToUpdatePassword);
    UserResponseToUser displayInforAcc ();
    UserResponseToAdmin displayUserToAdmin(User user);
    UserResponseToUser displayUserToUser(User user);
}
