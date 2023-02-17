package com.mypfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypfe.model.Check;

public interface CheckRepository extends JpaRepository<Check, Integer> {

}
