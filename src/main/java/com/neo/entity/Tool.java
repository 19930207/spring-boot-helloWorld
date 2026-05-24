package com.neo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tool")
public class Tool {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String thumbnail;

    private String version;

    private String description;

    private String usageInstructions;

    private Integer popularity;

    private String url;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
