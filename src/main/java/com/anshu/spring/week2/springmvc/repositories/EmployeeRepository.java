package com.anshu.spring.week2.springmvc.repositories;

import com.anshu.spring.week2.springmvc.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
