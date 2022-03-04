package com.example.com.spring.reactive.Repo;


import com.example.com.spring.reactive.Dto.StudentDto;
import com.example.com.spring.reactive.Entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface StudentRepo extends ReactiveMongoRepository<Student,String> {
    public Flux<StudentDto> findByName(String firstName);
}
