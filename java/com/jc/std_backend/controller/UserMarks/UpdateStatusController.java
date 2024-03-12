package com.jc.std_backend.controller.UserMarks;

import com.jc.std_backend.service.UserMarks.SubmitService;
import com.jc.std_backend.service.UserMarks.UpdateStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class UpdateStatusController {
    @Autowired
    private UpdateStatusService updateStatus;
    @PostMapping("/api/user/marks/update/")
    public Map<String, String> updateStatus(@RequestParam Map<String,String>data) {
        return updateStatus.updateStatus(data);
    }
}
