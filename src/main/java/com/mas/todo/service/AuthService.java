package com.mas.todo.service;

import com.mas.todo.dto.LoginDto;
import com.mas.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
