package com.example.com.spring.reactive.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
}

