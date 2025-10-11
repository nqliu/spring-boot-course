package top.nql.config.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.nql.config.enums.ExpressStatus;

@RestController
@RequestMapping("/express")
public class ExpressController {
    @GetMapping("/express/{status}")
    public String getExpressInfo(@PathVariable ExpressStatus status) {
        return "当前快递状态：" + status.getLabel();
    }
}




