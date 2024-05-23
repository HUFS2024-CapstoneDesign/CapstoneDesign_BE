package com.example.capstone.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailUtil {

  private final JavaMailSender javaMailSender;
  private final RedisUtil redisUtil;

  public String generateRandomNumber() {
    Random random = new Random();
    StringBuilder randNum = new StringBuilder();

    for (int i = 0; i < 10; i++) {
      randNum.append(random.nextInt(10));
    }

    return randNum.toString();
  }

  public MimeMessage createEmail(String email)
      throws MessagingException, UnsupportedEncodingException {

    MimeMessage message = javaMailSender.createMimeMessage();

    message.addRecipients(Message.RecipientType.TO, email);
    message.setSubject("비밀번호 찾기 메일입니다.");

    String code = generateRandomNumber();
    String content = "<br><br>" + "인증번호는 " + code + "입니다." + "<br>";

    message.setText(content, "utf-8", "html");
    message.setFrom(new InternetAddress("pos06058@naver.com", "CatchHealth"));

    redisUtil.createEmailCertification(email, code);

    return message;
  }

  public void sendEmail(String toMail) throws Exception {

    MimeMessage message = createEmail(toMail);

    try {
      javaMailSender.send(message);
    } catch (MailException e) {
      e.printStackTrace();
      throw new IllegalArgumentException();
    }
  }
}
