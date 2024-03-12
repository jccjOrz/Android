package com.jc.std_backend.controller.Mark;

import com.jc.std_backend.service.Marks.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PublishController {
    @Autowired
    private PublishService publishService;
    @PostMapping("/api/user/marks/publish/")
    public Map<String ,String>publishMarks(@RequestParam Map<String,String>data){
        return publishService.publishMarks(data);
    }
}
