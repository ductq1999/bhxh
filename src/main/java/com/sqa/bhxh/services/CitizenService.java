package com.sqa.bhxh.services;

import com.sqa.bhxh.entities.Citizen;
import com.sqa.bhxh.entities.Enterprise;

import java.util.List;

public interface CitizenService {

    List<Citizen> getAll();

    Citizen create(Citizen citizen);

    Citizen update(Citizen citizen) throws Exception;
}
