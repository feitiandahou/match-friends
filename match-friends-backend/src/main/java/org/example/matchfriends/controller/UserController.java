package org.example.matchfriends.controller;

import org.example.matchfriends.common.BaseResponse;
import org.example.matchfriends.common.ErrorCode;
import org.example.matchfriends.common.ResultUtils;
import org.example.matchfriends.entity.User;
import org.example.matchfriends.exception.BusinessException;
import org.example.matchfriends.model.request.UserRegisterRequest;
import org.example.matchfriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        String userName = userRegisterRequest.getUserName();
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)) return null;
        long result = userService.userRegister(userName ,userAccount, userPassword);
        return ResultUtils.success(result);
    }


    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
