package com.sqa.bhxh.services;

import com.sqa.bhxh.entities.Enterprise;

import java.util.List;

public interface EnterpriseService {

    List <Enterprise> getAll();

    Enterprise create(Enterprise enterprise);

    Enterprise update(Enterprise enterprise) throws Exception;
}
