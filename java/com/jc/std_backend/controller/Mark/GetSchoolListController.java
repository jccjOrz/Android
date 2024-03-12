package com.jc.std_backend.controller.Mark;

import com.jc.std_backend.pojo.Marks;
import com.jc.std_backend.service.Marks.GetSchoolListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetSchoolListController {
    @Autowired
    private GetSchoolListService getSchoolListService;
    @GetMapping("/api/user/marks/school/")
    public List<Marks> getSchoolList(){
        return getSchoolListService.getSchoolList();
    }
}
