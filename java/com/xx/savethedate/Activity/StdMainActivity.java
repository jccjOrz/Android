package com.xx.savethedate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xx.savethedate.Adapter.ViewPagerAdapter;
import com.xx.savethedate.R;

import java.util.ArrayList;

public class StdMainActivity extends AppCompatActivity {
    private ImageView mImage_add;
    private ImageView mImage_shejiao;
    private ImageView mImage_usermsg;
    private ImageView mImage_out;
    private ViewPager main_ViewPager;
    private LinearLayout main_LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_main);
        initView();
    }
    private void initView(){
        main_ViewPager=(ViewPager)findViewById(R.id.act_main_viewpager);
        main_LinearLayout=(LinearLayout)findViewById(R.id.act_main_LinearLayout);
        mImage_shejiao=(ImageView) findViewById(R.id.main_image1);
        mImage_add=(ImageView) findViewById(R.id.main_image2);
        mImage_usermsg=(ImageView) findViewById(R.id.main_image3);


        mImage_shejiao.setOnClickListener(new MyOnClickListener(0));
        mImage_add.setOnClickListener(new MyOnClickListener(1));
        mImage_usermsg.setOnClickListener(new MyOnClickListener(2));


        LayoutInflater layoutInflater=LayoutInflater.from(this);

        View v1=layoutInflater.inflate(R.layout.main_tab_shejiao,null);
        View v2=layoutInflater.inflate(R.layout.main_tab_publish,null);
        View v3=layoutInflater.inflate(R.layout.main_tab_user,null);


        final ArrayList<View> views=new ArrayList<>();
        views.add(v1);
        views.add(v2);
        views.add(v3);



        main_ViewPager.setAdapter(new ViewPagerAdapter(views));
    }

    public class MyOnClickListener implements View.OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){index=i;}

        @Override
        public void onClick(View view) {
            main_ViewPager.setCurrentItem(index);
        }
    }
    public void roomchat(View v){
        Intent intent=new Intent(StdMainActivity.this, Shejiao_Room_Activity.class);
        startActivity(intent);
    }
    public void classchat(View v){
        Intent intent=new Intent(StdMainActivity.this, Shejiao_Class_Activity.class);
        startActivity(intent);
    }
    public void schoolchat(View v){
        Intent intent=new Intent(StdMainActivity.this, Shejiao_School_Activity.class);
        startActivity(intent);
    }
    public void sendTo(View v){
        Intent intent=new Intent(StdMainActivity.this, PublishActivity.class);
        startActivity(intent);
    }
    public void unfinished(View v){
        Intent intent=new Intent(StdMainActivity.this, User_Unfinish_Activity.class);
        startActivity(intent);
    }
    public void finished(View v){
        Intent intent=new Intent(StdMainActivity.this, User_Finish_Activity.class);
        startActivity(intent);
    }
    public void lapsed(View v){
        Intent intent=new Intent(StdMainActivity.this, User_Lapsed_Activity.class);
        startActivity(intent);
    }
    public void out(View view){
        finish();
    }
}
