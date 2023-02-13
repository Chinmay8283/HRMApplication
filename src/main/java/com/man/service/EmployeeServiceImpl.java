package com.man.service;

import com.man.dao.EmployeeDaoImpl;
import com.man.exception.RecordNotFoundException;
import com.man.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeDaoImpl employeeDao;

    public Employee signUp(Employee employee){

        return  employeeDao.signUp(employee);
    }

    public String signIn(String empEmailId, String empPassword){

        return employeeDao.signIn(empEmailId, empPassword);

    }

    public List<Employee> saveBulkOfData(List<Employee> employees){

        return employeeDao.saveBulkOfData(employees);
    }

    public Employee getDataById(int empId) throws RecordNotFoundException {

        return employeeDao.getDataById(empId);
    }

    public Employee getDataByEmail(String empEmailId){

        return employeeDao.getDataByEmail(empEmailId);
    }

    public Employee getDataByContactNumber(long empContactNumber){

        return employeeDao.getDataByContactNumber(empContactNumber);
    }

    public List<Employee> filterDataBySalary(double empSalary){

        return employeeDao.filterDataBySalary(empSalary);

    }

    public List<Employee> getAllData(){

        return employeeDao.getAllData();
    }

    public Employee updateData(Employee employee){

        return employeeDao.updateData(employee);
    }

    public void deleteDataById(int empId){

        employeeDao.deleteDataById(empId);
    }

    public void deleteAllData(){

        employeeDao.deleteAllData();

    }
}
