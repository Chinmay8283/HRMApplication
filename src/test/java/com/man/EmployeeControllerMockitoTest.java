package com.man;

import com.man.controller.EmployeeController;
import com.man.exception.RecordNotFoundException;
import com.man.model.Employee;
import com.man.service.EmployeeServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeControllerMockitoTest.class})
public class EmployeeControllerMockitoTest {

    @Mock
    EmployeeServiceImpl service;

    @InjectMocks
    EmployeeController controller;


    @Test
    @Order(1)
    public void signUpTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.signUp(employee)).thenReturn(employee);
        ResponseEntity<Employee> emp = controller.signUp(employee);

        assertEquals(HttpStatus.CREATED, emp.getStatusCode());
        assertEquals(employee, emp.getBody());

    }

    @Test
    @Order(2)
    public void signInTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.signIn(employee.getEmpEmailId(),employee.getEmpPassword())).thenReturn(String.valueOf(employee));
        ResponseEntity<String> emp = controller.signIn(employee.getEmpEmailId(), employee.getEmpPassword());

        assertEquals(HttpStatus.FOUND, emp.getStatusCode());


    }

    @Test
    @Order(3)
    public void saveBulkOfDataTest() throws ParseException {


        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));

        when(service.saveBulkOfData(list)).thenReturn(list);
        ResponseEntity<List<Employee>> emp = controller.saveBulkOfData(list);

        assertEquals(HttpStatus.CREATED, emp.getStatusCode());
        assertEquals(list, emp.getBody());

    }

    @Test
    @Order(4)
    public void getDataByIdTest() throws ParseException, RecordNotFoundException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.getDataById(employee.getEmpId())).thenReturn(employee);
        ResponseEntity<Employee> emp = controller.getDataById(employee.getEmpId());

        assertEquals(HttpStatus.FOUND, emp.getStatusCode());
        assertEquals(1, emp.getBody().getEmpId());

    }

    @Test
    @Order(5)
    public void getDataByEmailTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.getDataByEmail(employee.getEmpEmailId())).thenReturn(employee);
        ResponseEntity<Employee> emp = controller.getDataByEmail(employee.getEmpEmailId());

        assertEquals(HttpStatus.FOUND, emp.getStatusCode());
        assertEquals("chinmay@123", emp.getBody().getEmpEmailId());
    }

    @Test
    @Order(6)
    public void getDataByContactNumberTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.getDataByContactNumber(employee.getEmpContactNumber())).thenReturn(employee);
        ResponseEntity<Employee> emp = controller.getDataByContactNumber(employee.getEmpContactNumber());

        assertEquals(HttpStatus.FOUND, emp.getStatusCode());
        assertEquals(employee.getEmpContactNumber(), emp.getBody().getEmpContactNumber());

    }

    @Test
    @Order(7)
    public void filterDataBySalaryTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));

        when(service.filterDataBySalary(28000.00)).thenReturn((list));
        ResponseEntity<List<Employee>> emp = controller.filterDataBySalary(28000.00);

        assertEquals(HttpStatus.PROCESSING, emp.getStatusCode());
        assertEquals(1, emp.getBody().size());

    }

    @Test
    @Order(8)
    public void getAllDataTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));

        when(service.getAllData()).thenReturn(list);
        ResponseEntity<List<Employee>> emp = controller.getAllData();

        assertEquals(HttpStatus.OK, emp.getStatusCode());
        assertEquals(1, emp.getBody().size());

    }

    @Test
    @Order(9)
    public void updateDataTest() throws ParseException, RecordNotFoundException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.getDataById(1)).thenReturn(employee);
        when(service.updateData(employee)).thenReturn(employee);

        ResponseEntity<Employee> emp = controller.updateData(1,employee);

        assertEquals(HttpStatus.ACCEPTED, emp.getStatusCode());
        assertEquals(1, emp.getBody().getEmpId());
        assertEquals("Chinmay", emp.getBody().getEmpName());
        assertEquals("Chakan",emp.getBody().getEmpAddress());
        assertEquals(9922993956l, emp.getBody().getEmpContactNumber());
        assertEquals(28000.00, emp.getBody().getEmpSalary());
        assertEquals(dob, emp.getBody().getEmpDob());
        assertEquals("chinmay@123", emp.getBody().getEmpEmailId());
        assertEquals("123456", emp.getBody().getEmpPassword());

    }

    @Test
    @Order(10)
    public void deleteDataByIdTest() throws ParseException, RecordNotFoundException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(service.getDataById(1)).thenReturn(employee);

        ResponseEntity<String> emp = controller.deleteDataById(1);

        assertEquals(HttpStatus.OK, emp.getStatusCode());

    }

    @Test
    @Order(11)
    public void deleteAllDataTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        controller.deleteAllData();

        ResponseEntity<String> emp = controller.deleteAllData();

        assertEquals(HttpStatus.OK, emp.getStatusCode());

    }



}
