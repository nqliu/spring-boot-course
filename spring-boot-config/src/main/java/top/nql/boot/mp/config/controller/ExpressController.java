package top.nql.boot.mp.config.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.nql.boot.mp.config.enums.ExpressStatus;

@RestController
@RequestMapping("/express")
public class ExpressController {
    @GetMapping("/{status}")
    public String getExpressInfo(@PathVariable ExpressStatus status) {
        return "当前快递状态：" + status.getLabel();
    }
}




