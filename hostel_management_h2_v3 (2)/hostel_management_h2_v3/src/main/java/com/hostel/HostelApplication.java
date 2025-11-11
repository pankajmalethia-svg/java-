package com.hostel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hostel.model.User;
import com.hostel.repository.UserRepository;

@SpringBootApplication
public class HostelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HostelApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository users) {
        return args -> {
            // ✅ Correct check: based on username, not ID
            if (!users.existsByUsername("admin")) {
                users.save(new User("admin", "admin", "ADMIN"));
                System.out.println("✅ Default admin user created (admin / admin)");
            }
        };
    }
}
