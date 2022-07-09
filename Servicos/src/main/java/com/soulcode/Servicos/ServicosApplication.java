package com.soulcode.Servicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class ServicosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicosApplication.class, args);
    }

    @Scheduled(cron = "0 0 0 ? * ?")
    public void cronJobSch() throws Exception {
        System.out.println("Rotina diária do servidor...");
    }
}
