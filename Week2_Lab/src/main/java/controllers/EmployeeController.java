package controllers;

import Model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")  // <-- Ensure this is set correctly
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "John Doe", "IT", 60000.0));
        employees.add(new Employee(2, "Jane Smith", "HR", 55000.0));
    }

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employees);
        return "employee-list";  // <-- Ensure this matches the template name
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";  // <-- Ensure this matches the template name
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employees.add(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return "redirect:/employees"; // Redirect if employee not found
        }

        model.addAttribute("employee", employee);
        return "employee-form";
    }



    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Integer id, @ModelAttribute Employee updatedEmployee) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                e.setName(updatedEmployee.getName());
                e.setDepartment(updatedEmployee.getDepartment());
                e.setSalary(updatedEmployee.getSalary());
                break;
            }
        }
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employees.removeIf(e -> e.getId().equals(id));
        return "redirect:/employees";
    }
}
