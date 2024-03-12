package com.jc.std_backend.service.impl.UserMarks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.std_backend.mapper.TaskMapper;
import com.jc.std_backend.pojo.Task;
import com.jc.std_backend.service.UserMarks.UpdateStatusService;
import com.jc.std_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateStatusServiceImpl implements UpdateStatusService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Map<String, String> updateStatus(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();
        Integer id= Integer.valueOf(data.get("id"));
        //Integer status=data.get("status");
        QueryWrapper<Task> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("chosen",loginUser.getUsername());
        Task task=new Task();
        task.setStatus(1);
        taskMapper.update(task,queryWrapper);
        Map<String,String> map=new HashMap<>();
        map.put("error_message","success");
        return map;
    }
}
