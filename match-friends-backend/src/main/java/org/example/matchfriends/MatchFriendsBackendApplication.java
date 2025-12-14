package org.example.matchfriends;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.matchfriends.mapper")
public class MatchFriendsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchFriendsBackendApplication.class, args);
    }

}
