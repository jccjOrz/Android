package com.jc.std_backend.service.user.account;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface LoginService {
    public Map<String,String> getToken(String username, String password);
}
