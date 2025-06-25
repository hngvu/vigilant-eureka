package com.orchidservice.controller;

//import com.orchidservice.config.jwt.JwtUtil;
import com.orchidservice.dto.response.ApiResponse;
import com.orchidservice.dto.response.AuthResponse;
import com.orchidservice.dto.LoginDTO;
import com.orchidservice.exception.AppException;
import com.orchidservice.exception.ErrorCode;
import com.orchidservice.entity.Accounts;
import com.orchidservice.response.LoginRespone;
import com.orchidservice.service.AccountService;
//import com.orchidservice.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/accounts")
@RestController
@AllArgsConstructor
public class AccountsController {
    @Autowired
    private  AccountService accountService;



    @GetMapping("/profile")
    public ResponseEntity<Accounts> authenticateAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Accounts accounts = (Accounts) authentication.getPrincipal();
        return ResponseEntity.ok(accounts);
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginRespone>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(accountService.login(loginDTO));
    }


}
