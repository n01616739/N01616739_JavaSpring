package com.example.n01616739_nambiar_assignment2.controller;

import com.example.n01616739_nambiar_assignment2.model.Course;
import com.example.n01616739_nambiar_assignment2.model.Student;
import com.example.n01616739_nambiar_assignment2.service.CourseService;
import com.example.n01616739_nambiar_assignment2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    // Display Admin Dashboard with students and courses
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Course> courses = courseService.getAllCourses();

        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "admin_dashboard";
    }

    // Add a new student
    @PostMapping("/add")
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("address") String address,
                             Model model) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAddress(address);
        studentService.updateStudent(student);

        model.addAttribute("success", "Student added successfully!");
        return "redirect:/admin/dashboard";
    }

    // Add a new course
    @PostMapping("/add-course")
    public String addCourse(@RequestParam("courseName") String courseName, Model model) {
        Course course = new Course();
        course.setCourseName(courseName);
        courseService.addCourse(course);

        model.addAttribute("success", "Course added successfully!");
        return "redirect:/admin/dashboard";
    }

    // Delete a course
    @PostMapping("/delete-course")
    public String deleteCourse(@RequestParam("id") Long courseId, Model model) {
        courseService.deleteCourse(courseId);
        model.addAttribute("success", "Course deleted successfully!");
        return "redirect:/admin/dashboard";
    }
}
