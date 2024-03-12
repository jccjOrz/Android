package com.jc.std_backend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.std_backend.mapper.UserMapper;
import com.jc.std_backend.pojo.User;
import com.jc.std_backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
       Map<String,String>map=new HashMap<>();
       if(username==null){
           map.put("error_message","用户名不能为空");
           return map;
       }

       if (password==null){
           map.put("error_message","密码不能为空");
           return map;
       }
       if (confirmedPassword==null){
           map.put("error_message","重复密码不能为空");
           return map;
       }
       username =username.trim();
       if (username.length()==0){
           map.put("error_message","用户名不能为空");
           return map;
       }

        if(password.length()==0){
            map.put("error_message","密码不能为空");
            return map;
        }
        if (confirmedPassword.length()==0){
            map.put("error_message","重复密码不能为空");
            return map;
        }
        if (password.length()>100||confirmedPassword.length()>100){
            map.put("error_message","密码长度不能超过100");
            return map;
        }
        if (!password.equals(confirmedPassword)){
            map.put("error_message","俩次输入的密码不一致");
            return map;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://jccj.oss-cn-nanjing.aliyuncs.com/photo/preview.jpg";
        User user = new User(null, username, encodedPassword, photo);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
