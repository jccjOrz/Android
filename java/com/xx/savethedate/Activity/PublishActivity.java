package com.xx.savethedate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xx.savethedate.R;
import com.xx.savethedate.utils.TokenManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mTitle;
    private EditText mContent;
    private EditText mdatelinetime;
    private Button mClassBtn;
    private Button mRoomBtn;
    private Button mSchoolBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_to_class);
        initView();
    }

    public void initView(){
        mTitle=(EditText) findViewById(R.id.publish_title);
        mContent=(EditText) findViewById(R.id.publish_content);
        mdatelinetime=(EditText) findViewById(R.id.datatime);
        mClassBtn=(Button) findViewById(R.id.SendToClass);
        mRoomBtn=(Button) findViewById(R.id.SendToRoom);
        mSchoolBtn=(Button) findViewById(R.id.SendToSchool);


        mClassBtn.setOnClickListener(this);
        mRoomBtn.setOnClickListener(this);
        mSchoolBtn.setOnClickListener(this);
        System.out.println("运行到这儿initView");

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SendToClass) {
            System.out.println("onclick");
            sendToClass();
        } else if (v.getId()==R.id.SendToRoom) {
            sendToRoom();
        } else if (v.getId()==R.id.SendToSchool) {
            sendtoSchool();
        }
    }
    public void sendToClass(){
        System.out.println("运行到这儿sendtoclass");
        String title=mTitle.getText().toString();
        String kinds="class";
        String content=mContent.getText().toString();
        String datetime=mdatelinetime.getText().toString();
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("title",title)
                .add("kinds",kinds)
                .add("content",content)
                .add("datetime",datetime)
                .build();
        // 创建POST请求
        // 从TokenManager中获取JWT令牌
        String jwtToken = TokenManager.getInstance().getJwtToken();
        Request request = new Request.Builder()
                .url("http://101.132.163.184/api/user/marks/publish/")
                .header("Authorization", "Bearer " + jwtToken )
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 网络请求失败，切换到主线程进行UI更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PublishActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(PublishActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if ("不可能出现的错误".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "不可能出现的错误", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                        return;
                    }else if("标题不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    } else if("任务内容不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "任务内容不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("标题不能超过25".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "标题不能超过25", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("内容不能超过50".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "内容不能超过50", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("日期不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("别搞".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "哥们，别搞好好看看今天日期", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "发生未知错误", Toast.LENGTH_SHORT).show();
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
    public void sendToRoom(){
        String title=mTitle.getText().toString();
        String kinds="room";
        String content=mContent.getText().toString();
        String datetime=mdatelinetime.getText().toString();
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("title",title)
                .add("kinds",kinds)
                .add("content",content)
                .add("datetime",datetime)
                .build();
        // 创建POST请求
        String jwtToken=TokenManager.getInstance().getJwtToken();
        Request request = new Request.Builder()
                .url("http://101.132.163.184/api/user/marks/publish/")
                .header("Authorization", "Bearer " + jwtToken )
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 网络请求失败，切换到主线程进行UI更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PublishActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(PublishActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if ("不可能出现的错误".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "不可能出现的错误", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                        return;
                    }else if("标题不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    } else if("任务内容不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "任务内容不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("标题不能超过25".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "标题不能超过25", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("内容不能超过50".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "内容不能超过50", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("日期不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("别搞".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "哥们，别搞好好看看今天日期", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "发生未知错误", Toast.LENGTH_SHORT).show();
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
    public void sendtoSchool(){
        String title=mTitle.getText().toString();
        String kinds="school";
        String content=mContent.getText().toString();
        String datetime=mdatelinetime.getText().toString();
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("title",title)
                .add("kinds",kinds)
                .add("content",content)
                .add("datetime",datetime)
                .build();
        // 创建POST请求
        String jwtToken=TokenManager.getInstance().getJwtToken();
        Request request = new Request.Builder()
                .url("http://101.132.163.184/api/user/marks/publish/")
                .header("Authorization", "Bearer " + jwtToken )
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 网络请求失败，切换到主线程进行UI更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PublishActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(PublishActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if ("不可能出现的错误".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "不可能出现的错误", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                        return;
                    }else if("标题不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    } else if("任务内容不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "任务内容不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("标题不能超过25".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "标题不能超过25", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("内容不能超过50".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "内容不能超过50", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("日期不能为空".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "日期不能为空", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else if("别搞".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "哥们，别搞好好看看今天日期", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PublishActivity.this, "发生未知错误", Toast.LENGTH_SHORT).show();
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
