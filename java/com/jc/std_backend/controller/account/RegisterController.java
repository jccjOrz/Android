package com.jc.std_backend.controller.account;

import com.jc.std_backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/api/user/account/register/")
    public Map<String,String> register(@RequestParam Map<String,String>map){
        String username=map.get("username");
        String password=map.get("password");
        String confirmedPasword=map.get("confirmedPassword");
        return registerService.register(username,password,confirmedPasword);
    }
}