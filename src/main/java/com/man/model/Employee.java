package com.man.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    private int empId;

    private String empName;

    private String empAddress;

    @Column(name = "emp_contact_no", unique = true)
    private long empContactNumber;

    private double empSalary;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDob;

    @Column(name = "emp_email", unique = true)
    private String empEmailId;

    @Column(name = "emp_password", unique = true, nullable = false)
    private String empPassword;

    public Employee(int empId,String empName, String empAddress, long empContactNumber, double empSalary, Date empDob, String empEmailId, String empPassword) {
        this.empId=empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.empContactNumber = empContactNumber;
        this.empSalary = empSalary;
        this.empDob = empDob;
        this.empEmailId = empEmailId;
        this.empPassword = empPassword;
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public long getEmpContactNumber() {
        return empContactNumber;
    }

    public void setEmpContactNumber(long empContactNumber) {
        this.empContactNumber = empContactNumber;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public Date getEmpDob() {
        return empDob;
    }

    public void setEmpDob(Date empDob) {
        this.empDob = empDob;
    }

    public String getEmpEmailId() {
        return empEmailId;
    }

    public void setEmpEmailId(String empEmailId) {
        this.empEmailId = empEmailId;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }


}
