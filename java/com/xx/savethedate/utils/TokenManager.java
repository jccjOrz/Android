package com.xx.savethedate.utils;

public class TokenManager {
    private static TokenManager instance;
    private String jwtToken;

    private TokenManager() {
        // 私有构造函数，防止外部实例化
    }

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void setJwtToken(String token) {
        this.jwtToken = token;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
