package com.xx.savethedate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xx.savethedate.R;

public class Item_Mark_P_D_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView musername;
    private TextView muserclass;
    private TextView mcontent;
    private TextView mtitle;
    private Button mback;

    private TextView mdate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_user_mark_d);
        initview();
    }
    @Override
    public void onClick(View v) {
        //监听注册按钮
        if (v.getId() == R.id.secondButton) {

            Intent intent = new Intent(this, User_Finish_Activity.class);
            startActivity(intent);
            finish();
        }

    }
    public void initview(){
        musername=(TextView) findViewById(R.id.nameText);
        muserclass=(TextView)findViewById(R.id.companyText);
        mcontent=(TextView)findViewById(R.id.contentText);
        mtitle=(TextView)findViewById(R.id.titleText);
        mback=(Button)findViewById(R.id.secondButton);
        mdate=(TextView)findViewById(R.id.dateText);
        mback.setOnClickListener(this);
        String selectedtitle =getIntent().getStringExtra("selectedtitle");
        String selectedcontent =getIntent().getStringExtra("selectedcontent");
        String selectedcreatedusername =getIntent().getStringExtra("selectedcreatedusername");
        String selectedkind =getIntent().getStringExtra("selectedkind");
        String selectedtime=getIntent().getStringExtra("selectedtime");
        mdate.setText(selectedtime);
        muserclass.setText(selectedkind);
        musername.setText(selectedcreatedusername);
        mcontent.setText(selectedcontent);
        mtitle.setText(selectedtitle);
    }
}
