package com.academico.webproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//used to load only the information needed for the login operation
public class UserLoginRequest {
    private String email;
    private String password;
}
