package com.sqa.bhxh.repository;

import com.sqa.bhxh.entities.SocialInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialInsuranceRepository extends JpaRepository<SocialInsurance, String> {

    SocialInsurance findById(Integer id);

    SocialInsurance findByNameAndCategory(String name, String category);
}
