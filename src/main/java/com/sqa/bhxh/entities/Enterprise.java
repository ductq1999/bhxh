package com.sqa.bhxh.entities;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name="enterprise", uniqueConstraints = { @UniqueConstraint(columnNames = { "tax_code" }) })
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
	 
}
