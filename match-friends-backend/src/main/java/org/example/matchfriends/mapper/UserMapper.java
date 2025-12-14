package org.example.matchfriends.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.matchfriends.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
