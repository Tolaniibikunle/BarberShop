package com.ardmore.quarters.gentlemens.entity;

import javax.persistence.*;

@Table(name = "auth_identifier")
@Entity(name = "auth_identifier")
public class AuthenticationIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "generated_salt")
    private String generatedSalt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getGeneratedSalt() {
        return generatedSalt;
    }

    public void setGeneratedSalt(String generatedSalt) {
        this.generatedSalt = generatedSalt;
    }
}
