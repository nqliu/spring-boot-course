package top.nql.boot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.nql.boot.entity.LoginRequest;
import top.nql.boot.entity.LoginResponse;
import top.nql.boot.result.Result;
import top.nql.boot.service.LoginService;

@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse=loginService.login(loginRequest);
        return Result.ok(loginResponse);
    }
}
