package com.orchidservice.service.Impl;

//import com.orchidservice.config.jwt.JwtUtil;
import com.orchidservice.dto.LoginDTO;
import com.orchidservice.dto.response.ApiResponse;
import com.orchidservice.dto.response.AuthResponse;
import com.orchidservice.exception.AppException;
import com.orchidservice.exception.ErrorCode;
import com.orchidservice.entity.Accounts;
import com.orchidservice.repository.AccountRepository;
import com.orchidservice.response.LoginRespone;
import com.orchidservice.service.AccountService;
import com.orchidservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createAccount(Accounts account) {
        Optional<Accounts> ac = accountRepository.findById(account.getId());
        if (ac.isPresent()) {
            throw new IllegalArgumentException("Account with ID " + account.getId() + " already exists.");
        } else {
            accountRepository.save(ac.get());
            System.out.println("Account created successfully: " + account);
        }
    }

    @Override
    public void updateAccount(String accountId, Accounts account) {
        Optional<Accounts> ac = accountRepository.findById(accountId);
        if (ac.isPresent()) {
            Accounts existingAccount = ac.get();

            if (account.getAccountName() != null) {
                existingAccount.setAccountName(account.getAccountName());
            }
            if (account.getEmail() != null) {
                existingAccount.setEmail(account.getEmail());
            }

            accountRepository.save(existingAccount);
            System.out.println("Account updated successfully: " + existingAccount);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist.");
        }
    }

    @Override
    public void deleteAccount(String accountId) {
        Optional<Accounts> ac = accountRepository.findById(accountId);
        if (ac.isPresent()) {
            accountRepository.deleteById(accountId);
            System.out.println("Account deleted successfully: " + accountId);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist.");
        }
    }


    @Override
    public String getAccountById(String accountId) {
        Optional<Accounts> ac = accountRepository.findById(accountId);
        if (ac.isPresent()) {
            return ac.get().toString();
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist.");
        }
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Accounts validateLogin(String username, String password) {
        Accounts account = accountRepository.findByAccountName(username);
        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            return account;
        }
        return null;
    }

    @Override
    public ApiResponse<LoginRespone> login(LoginDTO loginDTO) throws AppException {
        try {
            Accounts account = validateLogin(loginDTO.getUsername(), loginDTO.getPassword());
            if (account == null) {
                throw new AppException(ErrorCode.LOGIN_FAIL);
            }
            LoginRespone loginRespone = new LoginRespone();
            loginRespone.setToken(jwtService.generateToken(account));
            loginRespone.setUserName(account.getAccountName());
            loginRespone.setRole(account.getRole());
            loginRespone.setExpiresIn(jwtService.getExpirationTime()); // Assuming token expires in 1 hour
                return new ApiResponse<>(
                    true,
                    "Login successful",
                    200,
                     loginRespone);
        } catch (AuthenticationException e) {
            throw new AppException(ErrorCode.LOGIN_FAIL);
        }

    }
}
