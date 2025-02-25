package com.example.n01616739_nambiar_assignment2.controller;

import com.example.n01616739_nambiar_assignment2.model.Student;
import com.example.n01616739_nambiar_assignment2.model.Course;
import com.example.n01616739_nambiar_assignment2.service.StudentService;
import com.example.n01616739_nambiar_assignment2.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService StudentService;

    @Autowired
    private CourseService courseService;

    // Display student dashboard
    @GetMapping("/dashboard")
    public String studentDashboard(@RequestParam("id") Long studentId, Model model) {
        Optional<Student> studentOpt = StudentService.getStudentById(studentId);

        if (studentOpt.isEmpty()) {
            model.addAttribute("error", "Student not found!");
            return "login";
        }

        Student student = studentOpt.get();
        List<Course> availableCourses = courseService.getAllCourses();

        model.addAttribute("student", student);
        model.addAttribute("availableCourses", availableCourses);
        return "student_dashboard";
    }

    // Update student personal information
    @PostMapping("/update")
    public String updateStudentInfo(@RequestParam("id") Long studentId,
                                    @RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("address") String address,
                                    Model model) {
        Optional<Student> studentOpt = StudentService.getStudentById(studentId);
        if (studentOpt.isEmpty()) {
            model.addAttribute("error", "Student not found!");
            return "student_dashboard";
        }

        Student student = studentOpt.get();
        student.setName(name);
        student.setEmail(email);
        student.setAddress(address);
        StudentService.updateStudent(student);

        model.addAttribute("success", "Profile updated successfully!");
        return "redirect:/student/dashboard?id=" + studentId;
    }

    // Enroll in a course
    @PostMapping("/enroll")
    public String enrollInCourse(@RequestParam("studentId") Long studentId,
                                 @RequestParam("courseId") Long courseId,
                                 Model model) {
        StudentService.enrollInCourse(studentId, courseId);
        model.addAttribute("success", "Enrolled in course successfully!");
        return "redirect:/student/dashboard?id=" + studentId;
    }

    // Drop a course
    @PostMapping("/drop")
    public String dropCourse(@RequestParam("studentId") Long studentId,
                             @RequestParam("courseId") Long courseId,
                             Model model) {
        StudentService.dropCourse(studentId, courseId);
        model.addAttribute("success", "Dropped course successfully!");
        return "redirect:/student/dashboard?id=" + studentId;
    }
}
