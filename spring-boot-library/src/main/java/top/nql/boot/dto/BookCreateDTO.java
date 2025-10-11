package top.nql.boot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookCreateDTO {
    @NotBlank(message = "书名不能为空")
    private String title;
    private String author;
    @NotBlank(message = "ISBN不能为空")
    private String isbn;
    private String category;
    @Min(value = 0, message = "库存不能为负数")
    private Integer stock;
}
