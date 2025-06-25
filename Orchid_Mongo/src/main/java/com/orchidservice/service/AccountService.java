package com.orchidservice.service;

import com.orchidservice.dto.LoginDTO;
import com.orchidservice.dto.response.ApiResponse;
import com.orchidservice.dto.response.AuthResponse;
import com.orchidservice.exception.AppException;
import com.orchidservice.entity.Accounts;
import com.orchidservice.response.LoginRespone;

import java.util.List;

public interface AccountService {
    // Define methods related to account management here
    void createAccount(Accounts account);
    void updateAccount(String accountId, Accounts account);
    void deleteAccount(String accountId);
    String getAccountById(String accountId);
    public List<Accounts> getAllAccounts();
    public Accounts validateLogin(String username, String password) ;
    ApiResponse<LoginRespone> login(LoginDTO loginDTO) throws AppException;
}
