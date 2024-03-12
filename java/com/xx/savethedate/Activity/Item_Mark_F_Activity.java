package com.xx.savethedate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class Item_Mark_F_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView musername;
    private TextView muserclass;
    private TextView mcontent;
    private TextView mtitle;
    private TextView mdate;
    private Button mfinish;
    private Button mback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_user_mark_content);
        initview();
    }
    @Override
    public void onClick(View v) {
        //监听注册按钮
        if (v.getId() == R.id.Selected_f) {
            complete();

            finish();
        }else if (v.getId()==R.id.Cancel_f){

            Intent intent = new Intent(this, User_Unfinish_Activity.class);
            startActivity(intent);
            finish();
        }
    }

    public void complete(){
        String selectedid=getIntent().getStringExtra("selectedid");
        String selectedchosen=getIntent().getStringExtra("selectedchosen");
        OkHttpClient client=new OkHttpClient();
        System.out.println(selectedid);
        RequestBody requestBody=new FormBody.Builder()
                .add("id",selectedid)
                .add("chosen",selectedchosen)
                .build();
        // 创建POST请求
        String jwtToken= TokenManager.getInstance().getJwtToken();
        Request request = new Request.Builder()
                .url("http://101.132.163.184/api/user/marks/update/")
                .header("Authorization", "Bearer " + jwtToken )
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Item_Mark_F_Activity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                        //e.printStackTrace();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseData=response.body().string();


                try {
                    JSONObject jsonResponse=new JSONObject(responseData);
                    String errorMessage = jsonResponse.getString("error_message");
                    if("success".equals(errorMessage)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Item_Mark_F_Activity.this, "收录成功", Toast.LENGTH_SHORT).show();
                                //e.printStackTrace();
                            }
                        });
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Intent intent = new Intent(this, User_Unfinish_Activity.class);
        startActivity(intent);
    }
    public void initview(){
        musername=(TextView) findViewById(R.id.Username_f);
        muserclass=(TextView)findViewById(R.id.UserClasss_f);
        mcontent=(TextView)findViewById(R.id.contentText);
        mtitle=(TextView)findViewById(R.id.Title_f);
       // mdate=(TextView)findViewById(R.id.dateText);
        mfinish=(Button)findViewById(R.id.Selected_f);
        mback=(Button)findViewById(R.id.Cancel_f);
        mfinish.setOnClickListener(this);
        mback.setOnClickListener(this);

        String selectedtitle =getIntent().getStringExtra("selectedtitle");
        String selectedcontent =getIntent().getStringExtra("selectedcontent");
        String selectedcreatedusername =getIntent().getStringExtra("selectedcreatedusername");
        String selectedkind =getIntent().getStringExtra("selectedkind");
       // String selectedtime=getIntent().getStringExtra("selectedtime");
        muserclass.setText(selectedkind);
        musername.setText(selectedcreatedusername);
        mcontent.setText(selectedcontent);
        mtitle.setText(selectedtitle);
        //mdate.setText(selectedtime);

    }


}
