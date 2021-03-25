package com.sqa.bhxh.services.impl;

import com.sqa.bhxh.common.error.BadRequestException;
import com.sqa.bhxh.entities.SocialInsurance;
import com.sqa.bhxh.repository.SocialInsuranceRepository;
import com.sqa.bhxh.services.SocialInsuranceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocialInsuranceServiceImpl implements SocialInsuranceService {

    @Autowired
    private SocialInsuranceRepository socialInsuranceRepository;

    @Override
    public List<SocialInsurance> getAllSocialInsurances() {
        return socialInsuranceRepository.findAll();
    }

    @Override
    public SocialInsurance createSocialInsurance(SocialInsurance socialInsurance) {
        SocialInsurance check = socialInsuranceRepository.findByNameAndCategory(socialInsurance.getName(), socialInsurance.getCategory());
        if (check != null) {
            throw new BadRequestException("Da ton tai");
        }
        return socialInsuranceRepository.save(socialInsurance);
    }

    @Override
    @Transactional
    public SocialInsurance updateSocialInsurance(SocialInsurance socialInsurance) throws Exception {
        if (socialInsuranceRepository.findById(socialInsurance.getId()) == null) {
            throw new NotFoundException("khong ton tai");
        }

        return socialInsuranceRepository.save(socialInsurance);
    }
}
