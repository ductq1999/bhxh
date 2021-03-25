package com.sqa.bhxh.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="citizen")
public class Citizen {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "social_insurance_id")
    private SocialInsurance socialInsurance;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "l")
    private BigDecimal l;

    @Column(name = "pc1")
    private BigDecimal pc1;

    @Column(name = "pc2")
    private BigDecimal pc2;

    @Column(name = "pc3")
    private BigDecimal pc3;

    @Column(name = "pc4")
    private BigDecimal pc4;

    @Column(name = "pc5")
    private BigDecimal pc5;

    @Column(name = "pc6")
    private BigDecimal pc6;

    @Column(name = "pc7")
    private BigDecimal pc7;

}
