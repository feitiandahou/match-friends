package org.example.matchfriends.service;

import org.example.matchfriends.entity.User;
import org.example.matchfriends.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User()
                .setUserAccount("test123")
                .setUsername("testuser")
                .setPassword("encrypted_pwd");
        int instered = userMapper.insert(user);
        assertThat(instered).isEqualTo(1);
        List<User> users = userMapper.selectList(null);
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getUsername()).isEqualTo("testuser");
    }
}
