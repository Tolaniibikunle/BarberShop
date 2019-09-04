package com.ardmore.quarters.gentlemens.dto;

import lombok.Data;

@Data
public class User {

    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String displayName;
    private String role;

    public void constructDisplayName() {
        this.displayName = firstName + " "+ lastName;
    }

}
