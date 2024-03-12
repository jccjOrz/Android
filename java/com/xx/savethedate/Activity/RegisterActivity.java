package com.xx.savethedate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.xx.savethedate.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {
    private TextView mTextView1;
    private EditText mRegisterUser;
    private TextView mTextView2;
    private EditText mRegisterPassword;
    private TextView mTextView3;
    private EditText mRegisterRePassword;


    private Button mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    private void initView() {
        mTextView1 = (TextView) findViewById(R.id.textView);
        mRegisterUser = (EditText) findViewById(R.id.edt_account);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mRegisterPassword = (EditText) findViewById(R.id.edt_pwd);
        mTextView3= (TextView) findViewById(R.id.textView3);
        mRegisterRePassword = (EditText) findViewById(R.id.edt_repwd);
        mRegister = (Button) findViewById(R.id.btn_register);
        mRegister.setOnClickListener(this);
//        //添加actionbar左上角的返回键
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public void onClick(View v) {
        //监听注册按钮
        if (v.getId() == R.id.btn_register) {
            register();

        }
    }
   public void register(){
        String username=mRegisterUser.getText().toString();
        String password=mRegisterPassword.getText().toString();
        String confirmedPassword=mRegisterRePassword.getText().toString();
       System.out.println("username " + username); // send
       OkHttpClient client=new OkHttpClient();

       RequestBody requestBody=new FormBody.Builder()
               .add("username",username)
               .add("password",password)
               .add("confirmedPassword",confirmedPassword)
               .build();
       // 创建POST请求
       Request request = new Request.Builder()
               .url("http://101.132.163.184/api/user/account/register/")
               .post(requestBody)
               .build();
       //发起请求
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(@NonNull Call call, @NonNull IOException e) {
               // 网络请求失败，切换到主线程进行UI更新
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(RegisterActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                       //e.printStackTrace();
                   }
               });
           }

           @Override
           public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseData=response.body().string();
               System.out.println("Response Data: " + responseData); // 打印返回的数据
                try {
                    JSONObject jsonResponse=new JSONObject(responseData);
                    String errorMessage=jsonResponse.getString("error_message");
                    if("success".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(RegisterActivity.this,FirstActivity.class);
                                startActivity(intent);
                                finish();
                                //Toast.makeText(RegisterActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if ("用户名不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                        return;
                    }else if("密码不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    } else if("重复密码不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "重复密码不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("俩次输入的密码不一致".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "俩次输入密码不一致", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("用户名已存在".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("密码长度不能超过100".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "密码长度不能超过100", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "发生未知错误", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
           }
       });
   }
}