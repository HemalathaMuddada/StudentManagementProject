package com.project.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sms.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

}
