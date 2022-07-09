package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("servicos")
public class EmailController {
    @Autowired
    private EmailService emailService;


    @PostMapping("/email")
    public ResponseEntity<?> send(@RequestParam("email") String email, @RequestParam("subject") String subject, @RequestParam("content") String content) {
        emailService.sendMessage(email, subject, content);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
