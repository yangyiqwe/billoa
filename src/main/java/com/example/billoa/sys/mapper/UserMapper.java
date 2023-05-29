package com.example.billoa.sys.mapper;

import com.example.billoa.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.billoa.sys.entity.UserRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
public interface UserMapper extends BaseMapper<User> {

    List<String>getRoleNameByUserId(Integer userId);

}
