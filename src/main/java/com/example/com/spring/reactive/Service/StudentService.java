package com.example.com.spring.reactive.Service;

import com.example.com.spring.reactive.Dto.StudentDto;
import com.example.com.spring.reactive.Repo.StudentRepo;
import com.example.com.spring.reactive.Util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
    @Autowired
    private StudentRepo repository;

    public Flux<StudentDto> getStudents(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<StudentDto> getStudent(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<StudentDto> saveStudent(Mono<StudentDto> studentDtoMono){
        System.out.println("service method called ...");
        return  studentDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }
    public Mono<StudentDto> updateStudent(Mono<StudentDto> studentDtoMono,String id){
        return repository.findById(id)
                .flatMap(p->studentDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }
    public Mono<Void> deleteStudent(String id){
        return repository.deleteById(id);
    }
}
