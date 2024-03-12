package com.jc.std_backend.service.impl.Marks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.std_backend.mapper.MarksMapper;
import com.jc.std_backend.pojo.Marks;
import com.jc.std_backend.pojo.User;
import com.jc.std_backend.service.Marks.PublishService;
import com.jc.std_backend.service.impl.utils.UserDetailsImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.ParseException;
import java.util.TimeZone;

@Service

public class PublishServiceImpl implements PublishService {
    @Autowired
    private MarksMapper marksMapper;
    @Override
    public Map<String, String> publishMarks(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();


        String kinds=data.get("kinds");
        String title=data.get("title");
        String content=data.get("content");
        String datetime=data.get("datetime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date now=new Date();
        String noww= sdf.format(now);
        String errorMessage="";
        System.out.println(noww);
        Map<String,String> map=new HashMap<>();
        if (datetime==null||datetime.isEmpty()){
            errorMessage="日期不能为空";
        }else if (datetime.equals("xxxx-xx-xx")){
            datetime="xxxx-xx-xx";

        }else {
            try{
                Date dateline=sdf.parse(datetime);
                Date currenttime=sdf.parse(noww);
                if (dateline.before(currenttime)){
                    errorMessage="别搞";
                }
            }catch (ParseException e) {
                throw new RuntimeException();
            }

        }

        if (kinds==null||kinds.length()==0){
            errorMessage="不可能出现的错误";
        }
        if(title==null||title.length()==0){
            errorMessage="标题不能为空";
        }
        if (content==null||content.length()==0){
            errorMessage="任务内容不能为空";
        }
        if (title.length()>25){
            errorMessage="标题不能超过25";
        }
        if (content.length()>50){
            errorMessage="内容不能超过50";
        }
        if (!errorMessage.isEmpty()){
            map.put("error_message",errorMessage);
        }else {
            Marks marks=new Marks(
                    null,
                    kinds,
                    title,
                    content,
                    loginUser.getUsername(),
                    datetime
            );
           // System.out.println(loginUser.getUsername());
            marksMapper.insert(marks);
            map.put("error_message","success");

        }
        return map;
    }
}
