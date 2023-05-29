package com.example.billoa.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.billoa.common.utils.Jwtutil;
import com.example.billoa.common.utils.Md5Utils;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Menu;
import com.example.billoa.sys.entity.User;
import com.example.billoa.sys.entity.UserRole;
import com.example.billoa.sys.mapper.UserMapper;
import com.example.billoa.sys.mapper.UserRoleMapper;
import com.example.billoa.sys.service.IMenuService;
import com.example.billoa.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private Jwtutil jwtutil;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IMenuService iMenuService;


    @Override
    public Map<String, Object> login(User user) {

        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.baseMapper.selectOne(queryWrapper);

        //结果不为空，并且密码匹配，则生成token
        if(loginUser!=null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
            loginUser.setPassword(null);
            //生成token
            String token = jwtutil.createToken(loginUser);

            //返回数据
            Map<String,Object> data=new HashMap<>();
            data.put("token",token);
            return data;
        }
        return null;
    }

    public Map<String,Object> getUserInfo(String token){
        User loginUser=null;
        try {
            loginUser = jwtutil.parseToken(token, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(loginUser!=null){
            Map<String,Object> data=new HashMap<>();
            data.put("username",loginUser.getUsername());
            data.put("avatar",loginUser.getAvatar());
            //角色
            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            data.put("roles",roleList);

            //权限列表
            List<Menu> menuList = iMenuService.getMenuListByUserId(loginUser.getId());
            data.put("menuList",menuList);

            return data;
        }
        return  null;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        // 写入用户表
        this.baseMapper.insert(user);
        // 写入用户角色表
        List<Integer> roleIdList = user.getRoleIdList();
        if(roleIdList != null){
            for (Integer roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(null, user.getId(), roleId));
            }
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.baseMapper.selectById(id);

        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);

        List<Integer> roleIdList = userRoleList.stream()
                .map(userRole -> {return userRole.getRoleId();})
                .collect(Collectors.toList());
        user.setRoleIdList(roleIdList);

        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        // 更新用户表
        this.baseMapper.updateById(user);
        // 清除原有角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,user.getId());
        userRoleMapper.delete(wrapper);
        // 设置新的角色
        List<Integer> roleIdList = user.getRoleIdList();
        if(roleIdList != null){
            for (Integer roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        this.baseMapper.deleteById(id);
        // 清除原有角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        userRoleMapper.delete(wrapper);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = this.baseMapper.selectOne(wrapper);
        return user;
    }

}
