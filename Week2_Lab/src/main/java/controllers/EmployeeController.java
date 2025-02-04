package controllers;

import Model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employeeList = new ArrayList<>();
    private int employeeIdCounter = 1; // Simulating ID generation

    @GetMapping("/form")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }
        if (employee.getId() == 0) {
            employee.setId(employeeIdCounter++);
            employeeList.add(employee);
        } else {
            employeeList.replaceAll(emp -> emp.getId() == employee.getId() ? employee : emp);
        }
        return "redirect:/employees/list";
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeList);
        return "employee-list";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Optional<Employee> employee = employeeList.stream().filter(emp -> emp.getId() == id).findFirst();
        employee.ifPresent(value -> model.addAttribute("employee", value));
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeList.removeIf(emp -> emp.getId() == id);
        return "redirect:/employees/list";
    }
}

