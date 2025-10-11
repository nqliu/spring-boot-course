package top.nql.boot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StockAdjustDTO {
    @NotNull(message = "调整数量不能为空")
    @Min(value = 1, message = "调整数量必须大于0")
    private Integer amount;
    @Pattern(regexp = "in|out", message = "类型必须是in或out")
    private String type;
    private Integer adjustAmount;


}