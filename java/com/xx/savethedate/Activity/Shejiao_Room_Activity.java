package com.xx.savethedate.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.xx.savethedate.Adapter.ViewPagerAdapter;
import com.xx.savethedate.R;
import com.xx.savethedate.utils.TokenManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Shejiao_Room_Activity extends AppCompatActivity {
    private TextView mClass;
    private TextView mMark_one;
    private TextView mMark_date;
    private ViewPager Room_VP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_shejiao_room);
        initView();
    }
    private void initView(){
        Room_VP=(ViewPager)findViewById(R.id.shejiao_room_viewpager);
        mClass=(TextView) findViewById(R.id.room_name);
        mMark_one=(TextView) findViewById(R.id.room_o);
        mMark_date=(TextView) findViewById(R.id.room_d);

        mMark_one.setOnClickListener(new MyOnClickListener(0));
        mMark_date.setOnClickListener(new MyOnClickListener(1));

        LayoutInflater layoutInflater=LayoutInflater.from(this);

        View v1=layoutInflater.inflate(R.layout.son_room_one,null);
        View v2=layoutInflater.inflate(R.layout.son_room_date,null);

        final ArrayList<View> views=new ArrayList<>();
        views.add(v1);
        views.add(v2);

        ListView listView1=(ListView) v1.findViewById(R.id.O_room);
        ListView listView2=(ListView) v2.findViewById(R.id.D_room);

        Room_VP.setAdapter(new ViewPagerAdapter(views));


        ArrayList<String> timeList=new ArrayList<>();

        ArrayList<String> titleList2=new ArrayList<>();
        ArrayList<String> contentList2=new ArrayList<>();
        ArrayList<String> createdusernameList2=new ArrayList<>();
        ArrayList<String> kindsList2=new ArrayList<>();
        ArrayList<String> idList2=new ArrayList<>();

        ArrayList<String> idList1=new ArrayList<>();
        ArrayList<String> titleList1=new ArrayList<>();
        ArrayList<String> contentList1=new ArrayList<>();
        ArrayList<String> createdusernameList1=new ArrayList<>();
        ArrayList<String> kindsList1=new ArrayList<>();
        OkHttpClient client=new OkHttpClient();
        String jwtToken= TokenManager.getInstance().getJwtToken();
        Request request=new Request.Builder()
                .url("http://101.132.163.184/api/user/marks/room/")
                .header("Authorization", "Bearer " + jwtToken )
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 网络请求失败，切换到主线程进行UI更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Shejiao_Room_Activity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    JSONArray marklist = null;
                    try {
                        marklist = new JSONArray(responseBody);

                        for (int i=0;i<marklist.length();i++){
                            JSONObject mark=marklist.getJSONObject(i);
                            String id=mark.getString("id");
                            String title=mark.getString("title");
                            String kind=mark.getString("kinds");
                            String content=mark.getString("content");
                            String createdusername=mark.getString("createdusername");
                            String time=mark.getString("datetime");
                            if (time.equals("xxxx-xx-xx")){
                                idList1.add(id);
                                kindsList1.add(kind);
                                titleList1.add(title);
                                contentList1.add(content);
                                createdusernameList1.add(createdusername);
                            }else {
                                idList2.add(id);
                                timeList.add(time);
                                kindsList2.add(kind);
                                titleList2.add(title);
                                contentList2.add(content);
                                createdusernameList2.add(createdusername);
                            }

                        }
                        //callback.onDataReceived(kindsList);
                        //kindsList2=kindsList;
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Shejiao_Room_Activity.this,R.layout.item,titleList1);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Shejiao_Room_Activity.this,R.layout.item,titleList2);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView1.setAdapter(adapter1);
                                listView2.setAdapter(adapter2);
                            }
                        });


                        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // 获取点击的item对应的数据
                                String selectedtitle = titleList1.get(position);
                                String selectedcontent= contentList1.get(position);
                                String selectedkind= kindsList1.get(position);
                                String selectedcreatedusername=createdusernameList1.get(position);
                                String selectedid=idList1.get(position);
                                // 创建一个Intent对象，指定当前Activity和目标Activity
                                Intent intent = new Intent(Shejiao_Room_Activity.this, Item_Mark_Activity.class);
                                // 将数据放入Intent中，使用putExtra方法
                                intent.putExtra("selectedtitle", selectedtitle);
                                intent.putExtra("selectedcontent",selectedcontent);
                                intent.putExtra("selectedkind",selectedkind);
                                intent.putExtra("selectedcreatedusername",selectedcreatedusername);
                                intent.putExtra("selectedid",selectedid);

                                // 启动新的Activity
                                startActivity(intent);
                                finish();
                            }
                        });
                        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // 获取点击的item对应的数据
                                String selectedtitle = titleList2.get(position);
                                String selectedcontent= contentList2.get(position);
                                String selectedkind= kindsList2.get(position);
                                String selectedcreatedusername=createdusernameList2.get(position);
                                String selectedtime=timeList.get(position);
                                String selectedid=idList2.get(position);
                                // 创建一个Intent对象，指定当前Activity和目标Activity
                                Intent intent = new Intent(Shejiao_Room_Activity.this, Item_Mark_D_Activity.class);
                                // 将数据放入Intent中，使用putExtra方法
                                intent.putExtra("selectedtitle", selectedtitle);
                                intent.putExtra("selectedcontent",selectedcontent);
                                intent.putExtra("selectedkind",selectedkind);
                                intent.putExtra("selectedcreatedusername",selectedcreatedusername);
                                intent.putExtra("selectedtime",selectedtime);
                                intent.putExtra("selectedid",selectedid);

                                // 启动新的Activity
                                startActivity(intent);
                                finish();
                            }
                        });


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        });





    }

    public class MyOnClickListener implements View.OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){index=i;}

        @Override
        public void onClick(View view) {
            Room_VP.setCurrentItem(index);
        }
    }

}
