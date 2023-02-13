package com.man;

import com.man.dao.EmployeeDaoImpl;
import com.man.exception.RecordNotFoundException;
import com.man.model.Employee;
import com.man.repository.EmployeeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeDaoMockitoTest.class})
public class EmployeeDaoMockitoTest {

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeDaoImpl employeeDao;

    @Test
    @Order(1)
    public void signUpTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee,employeeDao.signUp(employee));

    }

    @Test
    @Order(2)
    public void signInTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));

        when(repository.findAll()).thenReturn((list));
        assertEquals("WELCOME SIGN IN SUCCESS",employeeDao.signIn("chinmay@123","123456"));


    }

    @Test
    @Order(3)
    public void saveBulkOfDataTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));


        when(repository.saveAll(list)).thenReturn(list);
        assertEquals(list, employeeDao.saveBulkOfData(list));


    }

    @Test
    @Order(4)
    public void getDataByIdTest() throws ParseException, RecordNotFoundException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00, dob ,"chinmay@123","123456");

        when(repository.findById(1)).thenReturn(java.util.Optional.of(employee));
        assertEquals(1,employeeDao.getDataById(1).getEmpId());

    }

    @Test
    @Order(5)
    public void getDataByEmailTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00, dob ,"chinmay@123","123456");

        when(repository.findByEmpEmailId("chinmay@123")).thenReturn(employee);
        assertEquals("chinmay@123",employeeDao.getDataByEmail("chinmay@123").getEmpEmailId());

    }

    @Test
    @Order(6)
    public void getDataByContactNumberTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956l,28000.00, dob ,"chinmay@123","123456");

        when(repository.findByEmpContactNumber(9922993956l)).thenReturn(employee);
        assertEquals(employee.getEmpContactNumber(),employeeDao.getDataByContactNumber(9922993956l).getEmpContactNumber());


    }

    @Test
    @Order(7)
    public void filterDataBySalaryTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));


        when(repository.findAll()).thenReturn((list));
        assertEquals(1,employeeDao.filterDataBySalary(28000.00).size()+1);


    }

    @Test
    @Order(8)
    public void getAllDataTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456"));

        when(repository.findAll()).thenReturn(list);
        assertEquals(1, employeeDao.getAllData().size());

    }

    @Test
    @Order(9)
    public void updateDataTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956l,28000.00, dob ,"chinmay@123","123456");

        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeDao.updateData(employee));


    }

    @Test
    @Order(10)
    public void deleteDataByIdTest() throws ParseException {


        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956l,28000.00, dob ,"chinmay@123","123456");

        employeeDao.deleteDataById(employee.getEmpId());
        verify(repository, times(1)).deleteById(employee.getEmpId());
    }

    @Test
    @Order(11)
    public void deleteAllDataTest() throws ParseException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956l,28000.00, dob ,"chinmay@123","123456");

        employeeDao.deleteAllData();
        verify(repository, times(1)).deleteAll();

    }


}
