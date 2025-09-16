package top.nql.config.model;


import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class Team {
    @Value("${team.leader}")
    @NotBlank(message = "负责人姓名不能为空")
    @Length(min = 2, max = 10,message = "姓名长度在2-10之间")
    private String leader;

    @Value("${team.age}")
    @Min(value=1,message = "团队年限不能小于1年")
    @Max(value=5,message = "团队年限不能大于5年")
    private Integer age;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$",message = "手机号格式不正确")
    private String phone;

    @Value("${team.createDate}")
    @Past(message = "创建时间不能早于当前时间")
    private LocalDate createDate;
}
