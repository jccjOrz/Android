package com.jc.std_backend.controller.UserMarks;

import com.jc.std_backend.pojo.Task;
import com.jc.std_backend.service.UserMarks.GetLapsedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetLapsedListController {
    @Autowired
    private GetLapsedListService getLapsedListService;
    @GetMapping("/api/user/marks/lapsed/")
    public List<Task> getLapsedList(){
        return getLapsedListService.getLapsedList();
    }
}
