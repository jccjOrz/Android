package com.jc.std_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.std_backend.pojo.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
