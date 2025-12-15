package org.example.matchfriends.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.matchfriends.common.BaseResponse;
import org.example.matchfriends.common.ErrorCode;
import org.example.matchfriends.common.ResultUtils;
import org.example.matchfriends.entity.User;
import org.example.matchfriends.exception.BusinessException;
import org.example.matchfriends.model.request.UserLoginRequest;
import org.example.matchfriends.model.request.UserRegisterRequest;
import org.example.matchfriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.matchfriends.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        String userName = userRegisterRequest.getUsername();
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)) throw  new BusinessException(ErrorCode.PARAMS_ERROR);
        long result = userService.userRegister(userName ,userAccount, userPassword);
        return ResultUtils.success(result);
    }


    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)) return null;
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> logout(HttpServletRequest request){
        if(request == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        Integer res = userService.userLogout(request);
        return ResultUtils.success(res);
    }
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        return ResultUtils.success(userService.getLoginUser(request));
    }
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if(!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        List<User> users = userService.searchList(username);
        return ResultUtils.success(users);
    }
    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUserByTags(@RequestParam(required = false) List<String> tagNameList){
        if(CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUserByTags(tagNameList);
        return ResultUtils.success(userList);
    }
    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user, HttpServletRequest request){
        if(user == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        int result = userService.updateUser(user, loginUser);
        return ResultUtils.success(result);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        if(!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if(id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }


}
