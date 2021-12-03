package com.project.sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sms.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

	Optional<College> findById(int id);

public	void deleteById(int id);

}
