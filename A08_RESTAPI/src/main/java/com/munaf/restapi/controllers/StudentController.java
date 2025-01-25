package com.munaf.restapi.controllers;

import com.munaf.restapi.entities.StudentEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/student")
public class StudentController {

    HashMap<Long, StudentEntity> student = new HashMap<>();


    @GetMapping("check")
    public String check () {
        return "CHECKKKKK";
    }

    // get
    @GetMapping
    public HashMap<Long, StudentEntity> getAllStudent(){
        return student;
    }


    // post
    @PostMapping
    public String createStudent(@RequestBody StudentEntity newStudent){
        student.put(newStudent.getId(), newStudent);
        return "Added";
    }

    // put
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentEntity newStudent){
        student.put(id, newStudent);
        return "Updated";
    }

    //delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        student.remove(id);
        return "Deleted";
    }

}
