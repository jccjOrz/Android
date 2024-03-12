package com.jc.std_backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName(value = "usermarks")
public class Task {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String kinds;
    private String title;
    private String content;
    private String createdusername;
    private String datetime;
    private Integer status;
    private String markid;
    private String chosen;
}
