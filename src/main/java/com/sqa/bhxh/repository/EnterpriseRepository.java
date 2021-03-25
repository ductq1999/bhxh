package com.sqa.bhxh.repository;

import com.sqa.bhxh.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, String> {

    Enterprise findById(Integer id);

    Enterprise findByTaxCode(String taxCode);
}
