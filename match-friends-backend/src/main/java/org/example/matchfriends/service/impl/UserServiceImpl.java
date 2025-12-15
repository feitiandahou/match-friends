package org.example.matchfriends.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.example.matchfriends.common.ErrorCode;
import org.example.matchfriends.constant.UserConstant;
import org.example.matchfriends.entity.User;
import org.example.matchfriends.exception.BusinessException;
import org.example.matchfriends.mapper.UserMapper;
import org.example.matchfriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.matchfriends.constant.UserConstant.USER_LOGIN_STATE;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public long userRegister(String userName ,String userAccount, String userPassword) {
        User user = new User();
        user.setUserAccount(userAccount);
        user.setPassword(userPassword);
        user.setUsername(userName);
        int result = mapper.insert(user);
        if(result < 1) return -1;
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("password", userPassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return null;
        }
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }

    @Override
    public Integer userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getRole() .equals(UserConstant.ADMIN_ROLE);
    }
    @Override
    public boolean isAdmin(User loginUser) {
        return loginUser != null && loginUser.getRole().equals(UserConstant.ADMIN_ROLE);
    }


    @Override
    public List<User> searchList(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
        List<User> userList = this.list(queryWrapper);
        return userList;
    }

    @Override
    public List<User> searchUserByTags(List<String> tagNameList) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(queryWrapper);
        Gson gson = new Gson();
        return userList.stream().filter(user -> {
            String tagStr = user.getTags();
            Set<String> tempTagNameSet = gson.fromJson(tagStr, new TypeToken<Set<String>>(){}.getType());
            tempTagNameSet = Optional.ofNullable(tempTagNameSet).orElseGet(HashSet::new);
            for (String tagName : tagNameList) {
                if(!tempTagNameSet.contains(tagName)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        if(request == null) {
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (userObj == null) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        return (User) userObj;
    }

    @Override
    public int updateUser(User user, User loginUser) {
        long userId = user.getId();
        if(userId <= 0) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        boolean hasUpdate = Stream.of(
                user.getUserAccount(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatarUrl(),
                user.getTags(),
                user.getRole()
        ).anyMatch(StringUtils::isNotBlank) || user.getGender() != null ||
                user.getAge()!= null || user.getStatus()!= null;
        if(!hasUpdate) throw  new BusinessException(ErrorCode.PARAMS_ERROR, "请提供至少一个需要更新的字段");
        if(!isAdmin(loginUser) && userId != loginUser.getId()){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        User oldUser = userMapper.selectById(userId);
        if(oldUser == null) throw new BusinessException(ErrorCode.NULL_ERROR);
        return userMapper.updateById(user);
    }

}
