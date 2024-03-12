package com.jc.std_backend.service.user.account;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface RegisterService {
    public Map<String,String>register(String username,String password,String confirmedPassword);
}
