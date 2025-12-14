package org.example.matchfriends.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
@TableName("user")
public class User {
    /**
     * 主键ID（雪花ID，由 MyBatis-Plus 自动生成）
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String userAccount;

    private String username;

    private String password;

    private String avatarUrl;

    private Integer gender;

    private Integer age;

    private String tags;

    private String role;

    private Integer status;
    @TableLogic
    private String isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
