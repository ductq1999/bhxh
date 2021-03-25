package com.sqa.bhxh.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name="user", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_name" }) })
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name", nullable=false)
    private String username;
    
    @Column(name = "password", nullable=false)
    private String password;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "active_flg")
    private Integer activeFlg;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "is_online")
    private Integer isOnline = 0;
    
    @Column(name = "is_deleted")
    private Integer isDeleted = 0;
    
    @Column(name = "token_reset")
    private String tokenReset;
    
    @Column(name = "token_reset_expried", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenResetExpried;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "socical_insurance_id")
    private SocialInsurance socialInsurance;
}
