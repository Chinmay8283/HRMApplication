package com.man.dao;

import com.man.exception.RecordNotFoundException;
import com.man.model.Employee;
import com.man.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepository repository;

    public Employee signUp(Employee employee){

        return repository.save(employee);

    }

    public String signIn(String empEmailId, String empPassword){

        String s1 = "WELCOME SIGN IN SUCCESS";
        String s2 = "INVALID EMAIL AND PASSWORD";

        for(Employee employee: repository.findAll()){

            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){

                return s1;
            }
            }
        return s2;
        }

        public List<Employee> saveBulkOfData(List<Employee> employees){

        return repository.saveAll(employees);

        }

        public Employee getDataById(int empId) throws RecordNotFoundException {

        Employee employee = repository.findById(empId).orElseThrow(()-> new RecordNotFoundException("RECORD NOT FOUND"));

        return employee;
        }

        public Employee getDataByEmail(String empEmailId){

        return repository.findByEmpEmailId(empEmailId);
        }

        public Employee getDataByContactNumber(long empContactNumber){

        return repository.findByEmpContactNumber(empContactNumber);
        }

        public List<Employee> filterDataBySalary(double empSalary){

       List<Employee> employeeList = repository.findAll().stream().filter(emp->emp.getEmpSalary()>=50000.00).collect(Collectors.toList());

       return employeeList;
        }

        public List<Employee> getAllData(){

        return repository.findAll();
        }

        public Employee updateData(Employee employee){

        return repository.save(employee);
        }

        public void deleteDataById(int empId){

         repository.deleteById(empId);
        }

        public void deleteAllData(){

        repository.deleteAll();

        }
        }


