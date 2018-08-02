package com.test.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.student.pojo.Student;

@RestController
public class StudentServiceController {
 
    private static Map<String, List<Student>> schooDB = new HashMap<String, List<Student>>();
 
    static {
        schooDB = new HashMap<String, List<Student>>();
 
        List<Student> lst = new ArrayList<Student>();
        lst.add(new Student("abc", "Grade A"));
        lst.add(new Student("cde", "Grade B"));
 
        schooDB.put("abcschool", lst);
 
        List<Student> lst1 = new ArrayList<Student>();
        lst1.add(new Student("xyz", "Grade 5"));
        lst1.add(new Student("def", "Grade 6"));
 
        schooDB.put("xyzschool", lst1);
        
        List<Student> lst2 = new ArrayList<Student>();
        lst2.add(new Student("kjl", "Grade 10"));
        lst2.add(new Student("acd", "Grade 9"));
 
        schooDB.put("123school", lst2);
    }
 
    @RequestMapping(value = "/getStudentDetailsForSchool/{schoolname}", method = RequestMethod.GET)
    public List<Student> getStudents(@PathVariable String schoolname) {
        System.out.println("Getting Student details for " + schoolname);
 
        List<Student> studentList = schooDB.get(schoolname);
        if (studentList == null) {
            studentList = new ArrayList<Student>();
            Student std = new Student("Not Found", "N/A");
            studentList.add(std);
        }
        return studentList;
    }
}