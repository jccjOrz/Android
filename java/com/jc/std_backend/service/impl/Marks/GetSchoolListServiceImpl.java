package com.jc.std_backend.service.impl.Marks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.std_backend.mapper.MarksMapper;
import com.jc.std_backend.mapper.TaskMapper;
import com.jc.std_backend.pojo.Marks;
import com.jc.std_backend.pojo.Task;
import com.jc.std_backend.service.Marks.GetSchoolListService;
import com.jc.std_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetSchoolListServiceImpl implements GetSchoolListService {
    @Autowired
    private MarksMapper marksMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Override
    public List<Marks> getSchoolList() {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();
        String username=loginUser.getUsername();
        QueryWrapper<Marks> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("kinds","school");
        queryWrapper.and(qw -> qw.eq("datetime", "xxxx-xx-xx").or().apply("DATE(datetime) > {0}", LocalDate.now()));
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq("chosen", username);

        List<Task> tasks = taskMapper.selectList(taskQueryWrapper);
        if (!tasks.isEmpty()) {
            List<Integer> markIds = new ArrayList<>();
            for (Task task : tasks) {
                String markid = task.getMarkid();
                markIds.add(Integer.valueOf(markid));
            }
            queryWrapper.notIn("id", markIds);
        }
        queryWrapper.orderByDesc("id");
        return marksMapper.selectList(queryWrapper);
    }

}
