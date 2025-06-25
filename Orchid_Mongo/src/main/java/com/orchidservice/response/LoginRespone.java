package com.orchidservice.response;

import com.orchidservice.entity.Roles;
import lombok.Data;

@Data
public class LoginRespone {
    private String token;
    private long expiresIn;
    private String userName;
    private Roles role;
}
