package com.jc.std_backend.service.UserMarks;

import com.jc.std_backend.pojo.Task;

import java.util.List;

public interface GetUnfinishedListService {
    List<Task> getUnfinishedList();
}