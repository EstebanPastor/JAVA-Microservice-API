package com.microservice.course.interfaces;

import com.microservice.course.entities.Course;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();

    Course findById(Long id);

    void save(Course course);
}
