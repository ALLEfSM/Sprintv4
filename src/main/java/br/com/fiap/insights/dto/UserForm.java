package br.com.fiap.insights.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserForm {
    private String username;
    private String password;
    private List<String> roles;
}
