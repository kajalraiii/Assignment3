package com.example.com.spring.reactive.Util;

import com.example.com.spring.reactive.Dto.StudentDto;
import com.example.com.spring.reactive.Entity.Student;
import org.springframework.beans.BeanUtils;

public class AppUtils {
    public static StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;
    }

    public static Student dtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        return student;
    }
}
