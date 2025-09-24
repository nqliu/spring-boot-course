package top.nql.boot.service;

import top.nql.boot.entity.LoginRequest;
import top.nql.boot.entity.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
