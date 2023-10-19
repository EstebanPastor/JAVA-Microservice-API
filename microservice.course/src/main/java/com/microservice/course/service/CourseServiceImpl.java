package com.microservice.course.service;

import com.microservice.course.client.IStudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistance.ICoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private IStudentClient studentClient;

    @Autowired
    private ICoursesRepository coursesRepository;

    @Override
    public List<Course> findAll() {
        return (List<Course>) coursesRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return coursesRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
    coursesRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {
        Course course = coursesRepository.findById(idCourse).orElse(new Course());

        List<StudentDTO> studentDTOlist = studentClient.findAllStudentsByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentsDTOList(studentDTOlist)
                .build();
    }
}

