package com.man.controller;

import com.man.exception.RecordNotFoundException;
import com.man.model.Employee;
import com.man.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/start")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee){

     Employee employee1 =  employeeService.signUp(employee);


     return new ResponseEntity<>(employee1,HttpStatus.CREATED);

    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<String> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){

        try {
            String emp = employeeService.signIn(empEmailId, empPassword);

            return new ResponseEntity<>(emp, HttpStatus.FOUND);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/savebulkofdata/List<Employee>")
    public ResponseEntity<List<Employee>> saveBulkOfData(@RequestBody List<Employee> employees){

        List<Employee> employees1 = employeeService.saveBulkOfData(employees);

        return new ResponseEntity<>(employees1, HttpStatus.CREATED);
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId) throws RecordNotFoundException {

        Employee employee = employeeService.getDataById(empId);

        return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
    }

    @GetMapping("/getdatabyemail/{empEmailId}")
    public ResponseEntity<Employee> getDataByEmail(@PathVariable String empEmailId){

        Employee employee = employeeService.getDataByEmail(empEmailId);

        return new ResponseEntity<>(employee,HttpStatus.FOUND);
    }

    @GetMapping("/getbycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber){

        Employee employee = employeeService.getDataByContactNumber(empContactNumber);

        return new ResponseEntity<>(employee, HttpStatus.FOUND);

    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary){

        List<Employee> employees = employeeService.filterDataBySalary(empSalary);

        return new ResponseEntity<>(employees, HttpStatus.PROCESSING);
    }


    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData(){

        List<Employee> employees = employeeService.getAllData();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee) throws RecordNotFoundException {

        Employee employee1 = employeeService.getDataById(empId);

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDob(employee.getEmpDob());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee.setEmpPassword(employee.getEmpPassword());

        Employee employee2 = employeeService.updateData(employee1);

        return new ResponseEntity<>(employee2, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId){

        employeeService.deleteDataById(empId);

        return new ResponseEntity<>("Data Deleted SuccessFully...", HttpStatus.OK);

    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData(){

        employeeService.deleteAllData();

        return new ResponseEntity<>("All Data Deleted SuccessFully...", HttpStatus.OK);
    }






}
