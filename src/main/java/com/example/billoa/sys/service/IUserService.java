package com.example.billoa.sys.service;

import com.example.billoa.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String,Object> getUserInfo(String token);

    void addUser(User user);

    User getUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);

    User getUserByUsername(String username);
}
