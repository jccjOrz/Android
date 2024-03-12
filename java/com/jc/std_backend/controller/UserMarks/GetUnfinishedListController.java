package com.jc.std_backend.controller.UserMarks;

import com.jc.std_backend.pojo.Task;
import com.jc.std_backend.service.UserMarks.GetUnfinishedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetUnfinishedListController {
    @Autowired
    private GetUnfinishedListService getUnfinishedListService;
    @GetMapping("/api/user/marks/unfinished/")
    public List<Task> getUnfinishedList(){
        return getUnfinishedListService.getUnfinishedList();
    }
}
