package top.nql.boot.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.nql.boot.common.Result;
import top.nql.boot.service.ExceptionService;
import top.nql.boot.entity.User;
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private ExceptionService exceptionService;
    @GetMapping("/{id}")
    public Result<String> getUserById(@PathVariable Integer id){
        if(id==0){
            ExceptionService.unAuthorError();
    }else if(id==1){
        ExceptionService.systemError();
    }else{
            int n=1/0;
            return Result.ok("查询成功");
        }
        return Result.error();
        }
    @PostMapping("/user")
    public Result<?> addUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Result.ok(user);
    }
}
