package com.hrs.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.test.model.VatAvailability;

@Repository
public interface VatAvailabilityRepository extends JpaRepository<VatAvailability, String> {
	
}
