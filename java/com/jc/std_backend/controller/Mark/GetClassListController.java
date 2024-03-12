package com.jc.std_backend.controller.Mark;

import com.jc.std_backend.pojo.Marks;
import com.jc.std_backend.service.Marks.GetClassListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetClassListController {
    @Autowired
    private GetClassListService getClassListService;
    @GetMapping("/api/user/marks/class/")
    public List<Marks>getClassList(){
        return getClassListService.getClassList();
    }
}
