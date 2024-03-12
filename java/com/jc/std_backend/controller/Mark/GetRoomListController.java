package com.jc.std_backend.controller.Mark;

import com.jc.std_backend.pojo.Marks;
import com.jc.std_backend.service.Marks.GetClassListService;
import com.jc.std_backend.service.Marks.GetRoomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetRoomListController {
    @Autowired
    private GetRoomListService getRoomListService;
    @GetMapping("/api/user/marks/room/")
    public List<Marks> getRoomList(){
        return getRoomListService.getRoomList();
    }
}
