package com.spring.mmm.domain.users.service;

import com.spring.mmm.common.config.RedisDao;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserEmailSendService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisDao redisDao;

    private String authNumber;

    public void makeRandomNumber() {
        Random r = new Random();
        authNumber = "";
        for(int i = 0; i < 6; i++) {
            authNumber += Integer.toString(r.nextInt(10));
        }

    }


    public String joinEmail(String email) {
        makeRandomNumber();
        String setFrom = "badacura@gmail.com";
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다.";
        String content =
                "'막내야 뭐 먹을래?'를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "인증번호를 입력해주세요";
        mailSend(setFrom, toMail, title, content);
        return authNumber;
    }

    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        redisDao.setDataExpire(authNumber,toMail,60*5L);
    }

    public boolean checkAuthNum(String email,String authNum){
        if(redisDao.getData(authNum)==null | !redisDao.getData(authNum).equals(email)){
            return false;
        }
        else {
            return true;
        }
    }

}