package com.man.repository;

import com.man.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom Methods

    public Employee findByEmpContactNumber(long empContactNumber);

    public Employee findByEmpEmailId( String empEmailId);


}
