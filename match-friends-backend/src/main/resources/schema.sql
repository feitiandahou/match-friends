create table if not exists `user`(
                                     `id` bigint not null comment "主键ID",
                                     `user_account` varchar(64) not null comment "登陆账号 唯一",
    `username` varchar(64) not null comment "用户昵称",
    `password` varchar(128) not null comment "加密后的密码",
    `avatar_url` varchar(512) comment "头像地址",
    `gender` TINYINT comment "性别（0-女, 1-男）",
    `age` int comment "年龄",
    `tags` varchar(1024) comment "标签（JSON 数组字符串）",
    `role` varchar(32) not null default 'user' comment "角色",
    `status` TINYINT not null default 0 comment "状态",
    `is_deleted` TINYINT not null default 0 comment "逻辑删除",
    `create_time` DATETIME not null default current_timestamp comment "创建时间",
    `update_time` DATETIME not null default current_timestamp on update current_timestamp comment "更新时间",
    -- 主键
    primary key (`id`),
    -- 唯一索引 防止重复账号
    UNIQUE KEY `uniq_user_account` (`user_account`)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';