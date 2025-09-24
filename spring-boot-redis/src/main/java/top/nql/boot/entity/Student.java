package top.nql.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Serial
    private static final long serialVersionUID = -6000586252003590L;
    private  String id;
    private  String name;
    private  Address address;
}
