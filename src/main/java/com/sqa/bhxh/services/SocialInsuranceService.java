package com.sqa.bhxh.services;

import com.sqa.bhxh.entities.SocialInsurance;

import java.util.List;

public interface SocialInsuranceService {

    List<SocialInsurance> getAllSocialInsurances();

    SocialInsurance createSocialInsurance(SocialInsurance socialInsurance);

    SocialInsurance updateSocialInsurance(SocialInsurance socialInsurance) throws Exception;
}
