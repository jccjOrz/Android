package com.jc.std_backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marks {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String kinds;
    private String title;
    private String content;
    private String createdusername;
    private String datetime;
}
