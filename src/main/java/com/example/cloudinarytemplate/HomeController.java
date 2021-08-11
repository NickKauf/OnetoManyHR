package com.example.cloudinarytemplate;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CloudinaryConfig cloudc;


    @RequestMapping("/")
    public String listDepartments(Model model){
        model.addAttribute("departments", departmentRepository.findAll());

         //findall returns a collection, can be converted to arraylist
        return "deptslist";
    }

    @RequestMapping("/listEmployees")
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "empslist";
    }

    @GetMapping("/addDepartment")
    public String newDept(Model model){
        model.addAttribute("department", new Department());
        return "deptform";
    }

    @PostMapping("/processDepartment")
    public String processDept(@ModelAttribute Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
       model.addAttribute("employee", new Employee());
       model.addAttribute("departments", departmentRepository.findAll());
       return "empform";
    }

    @PostMapping("/processEmployee")
    public String processEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping("/details")
    public String details(){
        return "empdetails";
    }

    @RequestMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "empform";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "deptform";
    }

    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") long id){
        departmentRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/employeeDetails/{id}")
    public String employeeDetails(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "empdetails";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }


}