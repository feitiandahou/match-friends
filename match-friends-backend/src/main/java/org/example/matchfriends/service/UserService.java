package org.example.matchfriends.service;


import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.matchfriends.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    long  userRegister(String userName, String userAccount, String userPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    Integer userLogout(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    boolean isAdmin(User loginUser);

    List<User> searchList(String username);

    List<User> searchUserByTags(List<String> tagNameList);

    User getLoginUser(HttpServletRequest request);

    int updateUser(User user, User loginUser);
}
