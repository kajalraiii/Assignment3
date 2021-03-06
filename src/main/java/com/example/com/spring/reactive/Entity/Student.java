package com.example.com.spring.reactive.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "students")
public class Student {
        @Id
        private String id;
        private String firstName;
        private String lastName;
        private int age;

}
