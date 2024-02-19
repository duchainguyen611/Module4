package com.ra.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceIMPL implements EmailService{
@Autowired
private JavaMailSender javaMailSender;
    @Override
    public String sendMail() {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("nguyenduchai611@gmail.com");
            simpleMailMessage.setTo("duchaiprovip01@gmail.com");
            simpleMailMessage.setText("Cảm ơn bạn!");
            simpleMailMessage.setSubject("Cửa hàng");
            javaMailSender.send(simpleMailMessage);
            return "Ok đã gửi";
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
