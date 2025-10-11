package top.nql.boot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("book")

public class BookEntity {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Integer stock;
    @TableLogic
    private Integer deleted;
    private Integer version;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}