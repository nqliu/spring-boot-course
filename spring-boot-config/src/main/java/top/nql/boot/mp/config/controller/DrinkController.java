package top.nql.boot.mp.config.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.nql.boot.mp.config.enums.DrinkType;

@RestController
public class DrinkController {
    @GetMapping("/drink/{type}")
    public String getDrinkType(@PathVariable DrinkType type) {
        return "您选择的是："+ type.getLabel()+",价格是："+type.getPrice();
    }

}
