package com.sqa.bhxh.services.impl;

import com.sqa.bhxh.common.error.BadRequestException;
import com.sqa.bhxh.entities.Enterprise;
import com.sqa.bhxh.repository.EnterpriseRepository;
import com.sqa.bhxh.services.EnterpriseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public List<Enterprise> getAll() {
        return enterpriseRepository.findAll();
    }

    @Override
    public Enterprise create(Enterprise enterprise) {
        Enterprise check = enterpriseRepository.findByTaxCode(enterprise.getTaxCode());
        if (check != null) {
            throw new BadRequestException("Da ton tai");
        }
        return enterpriseRepository.save(enterprise);
    }

    @Override
    @Transactional
    public Enterprise update(Enterprise enterprise) throws Exception {
        if (enterpriseRepository.findById(enterprise.getId()) == null) {
            throw new NotFoundException("khong ton tai");
        }

        return enterpriseRepository.save(enterprise);
    }
}
