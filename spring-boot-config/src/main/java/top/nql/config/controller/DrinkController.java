package top.config.config.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.config.config.enums.DrinkType;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    @GetMapping("/drink/{type}")
    public String getDrinkType(@PathVariable DrinkType type) {
        return "您选择的是："+ type.getLabel()+",价格是："+type.getPrice();
    }

}
