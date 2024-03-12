package com.jc.std_backend.service.impl.UserMarks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.std_backend.mapper.TaskMapper;
import com.jc.std_backend.pojo.Task;
import com.jc.std_backend.service.UserMarks.GetFinishedListService;
import com.jc.std_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class GetFinishedListServiceImpl implements GetFinishedListService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> getFinishedList() {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();
        QueryWrapper<Task> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chosen",loginUser.getUsername());
        queryWrapper.eq("status",1);
        queryWrapper.and(qw -> qw.eq("datetime", "xxxx-xx-xx").or().apply("DATE(datetime) > {0}", LocalDate.now()));
        queryWrapper.orderByDesc("id");
        return taskMapper.selectList(queryWrapper);
    }
}
