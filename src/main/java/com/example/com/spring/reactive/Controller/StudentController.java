package com.example.com.spring.reactive.Controller;

import com.example.com.spring.reactive.Dto.StudentDto;
import com.example.com.spring.reactive.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;
        @GetMapping
        public Flux<StudentDto> getStudents(){
            return service.getStudents();
        }

        @GetMapping("/{id}")
        public Mono<StudentDto> getStudent(@PathVariable String id){
            return service.getStudent(id);
        }



        @PostMapping
        public Mono <StudentDto> saveStudent(@RequestBody Mono<StudentDto> studentDtoMono){
            System.out.println("controller method called ...");
            return service.saveStudent(studentDtoMono);
        }

        @PutMapping("/update/{id}")
        public Mono<StudentDto> updateProduct(@RequestBody Mono<StudentDto> studentDtoMono,@PathVariable String id){
            return service.updateStudent(studentDtoMono,id);
        }

        @DeleteMapping("/delete/{id}")
        public Mono<Void> deleteProduct(@PathVariable String id){
            return service.deleteStudent(id);
        }



    }
