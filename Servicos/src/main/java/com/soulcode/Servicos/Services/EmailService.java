package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Services.Exceptions.EmailSendingFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    @Value("${default.sender}")
    private String email;

    public void sendMessage(String destinatario, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinatario);
            message.setFrom(email);
            message.setSubject(subject);
            message.setText(content);

            sender.send(message);
        }catch (Exception ex) {
            throw new EmailSendingFailedException(ex.getMessage());
        }
    }
}
