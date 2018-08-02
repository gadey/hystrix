package com.test.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.school.service.StudentServiceDelegate;

@RestController
public class SchoolServiceController {
     
    @Autowired
    StudentServiceDelegate studentServiceDelegate;
 
    @RequestMapping(value = "/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
    public String getStudents(@PathVariable String schoolname) {
        System.out.println("Going to call student service to get data!");
        return studentServiceDelegate.callStudentServiceAndGetData(schoolname);
    }
    
    @RequestMapping(value = "/getProductDetails/{productId}", method = RequestMethod.GET)
    public String getProdcutDetails(@PathVariable String productId) throws Exception {
        System.out.println("Going to call product service to get data!");
        return studentServiceDelegate.callProdcutData(productId);
    }
}
