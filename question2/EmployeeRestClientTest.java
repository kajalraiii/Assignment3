package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeRestClientTest {
    private static final String baseUrl="http://dummy.restapiexample.com";
 private WebClient webClient=WebClient.create();
 EmployeeRestClient employeeRestClient=new EmployeeRestClient(webClient);

 @Test
    void retrieveAllEmployee(){
     List<Employee> employeeList=employeeRestClient.retrieveAllEmployees();
     System.out.println("employeeList: " + employeeList);
     assertTrue(employeeList.size()>0);
 }
}
