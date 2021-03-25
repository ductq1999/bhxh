package com.sqa.bhxh.entities;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Entity
@Table(name="enterprise")
public class Enterprise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	 @Column(name = "tax_code", nullable = false)
	 private String taxCode;

	 @Column(name = "name", nullable = false)
	 private String name;

	 @Column(name = "address", nullable = false)
	 private String address;
	 
	 @OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user_id")
	 private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "social_insurance")
	private SocialInsurance socialInsurance;
	 
}
