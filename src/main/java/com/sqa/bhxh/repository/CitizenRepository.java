package com.sqa.bhxh.repository;

import com.sqa.bhxh.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, String> {

    Citizen findById(Integer id);

    Citizen findByIdentityNumber(String identityNumber);
}
