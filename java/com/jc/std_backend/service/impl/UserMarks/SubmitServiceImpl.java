package com.jc.std_backend.service.impl.UserMarks;

import com.jc.std_backend.mapper.TaskMapper;
import com.jc.std_backend.pojo.Task;
import com.jc.std_backend.service.UserMarks.SubmitService;
import com.jc.std_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SubmitServiceImpl implements SubmitService {
    @Autowired
    private TaskMapper taskMapper;
    @Override
    public Map<String, String> submit(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();

        String kinds=data.get("kinds");
        String title=data.get("title");
        String content=data.get("content");
        String datetime=data.get("datetime");
        String createdusername=data.get("createdusername");
        String markid= data.get("markid");
        Map<String,String> map=new HashMap<>();
        Task task =new Task(
                null,
                kinds,
                title,
                content,
                createdusername,
                datetime,
                null,
                markid,
                loginUser.getUsername()
        );
        taskMapper.insert(task);
        map.put("error_message","success");
        return map;
    }
}
