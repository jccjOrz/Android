package com.jc.std_backend.controller.UserMarks;

import com.jc.std_backend.service.UserMarks.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SubmitController  {
    @Autowired
    private SubmitService submitService;
    @PostMapping("/api/user/marks/submit")
    public Map<String, String> submit(@RequestParam Map<String,String>data) {
        return submitService.submit(data);
    }
}
