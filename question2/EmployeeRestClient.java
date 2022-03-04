package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


import static com.learnwebclient.constants.EmployeeConstants.Get_employee_all;

public class EmployeeRestClient {
    private WebClient webClient;
    public EmployeeRestClient(WebClient webClient){
        this.webClient=webClient;
    }

    public List<Employee> retrieveAllEmployees(){
      return webClient.get().uri(Get_employee_all)
               .retrieve()
               .bodyToFlux(Employee.class)
               .collectList()
               .block();
    }
}
