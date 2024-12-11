package com.sonify.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String login;
    private String password;
    private Boolean active;
    private Set<RoleDTO> roles;
}
