package com.sqa.bhxh.services.impl;

import com.sqa.bhxh.common.error.BadRequestException;
import com.sqa.bhxh.entities.Citizen;
import com.sqa.bhxh.entities.Enterprise;
import com.sqa.bhxh.repository.CitizenRepository;
import com.sqa.bhxh.services.CitizenService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public List<Citizen> getAll() {
        return citizenRepository.findAll();
    }

    @Override
    public Citizen create(Citizen citizen) {
        Citizen check = citizenRepository.findByIdentityNumber(citizen.getIdentityNumber());
        if (check != null) {
            throw new BadRequestException("Da ton tai");
        }
        return citizenRepository.save(citizen);
    }

    @Override
    @Transactional
    public Citizen update(Citizen citizen) throws Exception {
        if (citizenRepository.findById(citizen.getId()) == null) {
            throw new NotFoundException("khong ton tai");
        }

        return citizenRepository.save(citizen);
    }
}
