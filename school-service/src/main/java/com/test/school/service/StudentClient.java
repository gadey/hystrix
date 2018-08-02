package com.test.school.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("student-service")
public interface StudentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getStudentDetailsForSchool/{schoolname}")
    public String getBookmarks(@PathVariable("schoolname") String schoolname);
}
