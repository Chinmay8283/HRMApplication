package com.man;

import com.man.model.Employee;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeControllerIntegrationTest.class})
public class EmployeeControllerIntegrationTest {

    @Test
    @Order(1)
    public void signUpTest() throws ParseException, JSONException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        String excepted = "{\n" +
                "    \"empId\":1,\n" +
                "    \"empName\":\"Chinmay\",\n" +
                "    \"empAddress\":\"Chakan\",\n" +
                "    \"empContactNumber\":9922993956,\n" +
                "    \"empSalary\":28000.00,\n" +
                "    \"empDob\":\"26-09-1999\",\n" +
                "    \"empEmailId\":\"chinmay@123\",\n" +
                "    \"empPassword\":\"123456\"\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/start/signup",request,String.class);

        System.out.println(response.getBody());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        JSONAssert.assertEquals(excepted, response.getBody(), false);


    }

    @Test
    @Order(2)
    public void signIn() throws ParseException, JSONException {

        Date dob = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dob = simpleDateFormat.parse("26-09-1999");

        Employee employee = new Employee(1,"Chinmay","Chakan",9922993956L,28000.00,dob,"chinmay@123","123456");

        String excepted = "{\n" +
                "    \"empId\":1,\n" +
                "    \"empName\":\"Chinmay\",\n" +
                "    \"empAddress\":\"Chakan\",\n" +
                "    \"empContactNumber\":9922993956,\n" +
                "    \"empSalary\":28000.00,\n" +
                "    \"empDob\":\"26-09-1999\",\n" +
                "    \"empEmailId\":\"chinmay@123\",\n" +
                "    \"empPassword\":\"123456\"\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/start/signin/chinmay@123/123456", String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

      assertEquals(HttpStatus.FOUND, response.getStatusCode());
      JSONAssert.assertEquals(excepted, response.getBody(), false);


    }

}
