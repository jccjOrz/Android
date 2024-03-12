package com.xx.savethedate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xx.savethedate.R;
import com.xx.savethedate.utils.TokenManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Message;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtAccount;
    private EditText mEdtPwd;
    private Button mBtnLogin;
    private Button mBtnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView(){
        mEdtAccount=(EditText) findViewById(R.id.edt_account);
        mEdtPwd=(EditText) findViewById(R.id.edt_pwd);
        mBtnLogin=(Button) findViewById(R.id.btn_login);
        mBtnRegister=(Button) findViewById(R.id.btn_register);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        int id=v.getId();
        if(id==R.id.btn_register){
            Intent intent=new Intent(FirstActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.btn_login) {
            attemptLogin();

        }
    }

    private void attemptLogin(){
        String username=mEdtAccount.getText().toString();
        String password=mEdtPwd.getText().toString();
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .url("http://101.132.163.184/api/user/account/token/")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                // 网络请求失败，切换到主线程进行UI更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FirstActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                       //e.printStackTrace();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String jwtToken=jsonObject.getString("token");
                        TokenManager.getInstance().setJwtToken(jwtToken);
                        // 构建带有Authorization头部的请求
                        Request authorizedRequest = new Request.Builder()
                                .url("http://101.132.163.184/api/user/account/info/")
                                .header("Authorization", "Bearer " + jwtToken)
                                .build();
                        //发送
                        client.newCall(authorizedRequest).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                // 网络请求失败，切换到主线程进行UI更新
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(FirstActivity.this, "受保护资源请求失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                                    if (response.isSuccessful()){
                                        // 受保护资源请求成功，切换到主线程进行UI更新
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent=new Intent(FirstActivity.this,StdMainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    }else {
                                        // 受保护资源请求失败，切换到主线程进行UI更新
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(FirstActivity.this, "登录失败请检查你的账号密码知否正确test", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                            }
                        });

                    }catch (JSONException e) {
                        // JSON解析异常，切换到主线程进行UI更新
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FirstActivity.this, "JSON解析异常", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }



                }else {
                    // 登录请求失败，切换到主线程进行UI更新
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FirstActivity.this, "登录失败请检查你的账号密码知否正确", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}