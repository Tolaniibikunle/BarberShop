package com.ardmore.quarters.gentlemens.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "account_deletion")
public class AccountDeletetion {

    private static final int DELETE_DATE = 60 * 24 * 14; //Two Weeks

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "authId", nullable = false)
    private Long authId;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "delete_date", nullable = false)
    private Date deleteDate;

    public AccountDeletetion(Integer userId, Long authId) {
        super();
        this.userId = userId;
        this.authId = authId;
        this.deleteDate = calculateDeleteDate();
    }

    private Date calculateDeleteDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, DELETE_DATE);
        return calendar.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}
