package top.nql.boot.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookUpdateDTO {
    @Pattern(regexp = "^.{0,200}$", message = "书名不能超过200个字符")
    private String title;

    @Pattern(regexp = "^.{0,128}$", message = "作者名不能超过128个字符")
    private String author;

    @Pattern(regexp = "^.{0,64}$", message = "分类不能超过64个字符")
    private String category;
}