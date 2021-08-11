package com.example.cloudinarytemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository  departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public void run(String...args){
        User admin = new User("admin", "Dave@domain.com", "admin", "Dave","Wolf", true);
        Role adminRole = new Role("admin", "ROLE_USER");
        Role adminRole2 = new Role("admin", "ROLE_ADMIN");

        userRepository.save(admin);
        roleRepository.save(adminRole);
        roleRepository.save(adminRole2);

        User user = new User("user", "user@domain.com", "user", "User", "User", true);
        Role userRole = new Role("user", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);

        Department department = new Department();
        department.setDeptName("HQ");

        Employee employee = new Employee();
        employee.setName("Big Boss");
        employee.setTitle("CEO");
        employee.setDepartment(department);

        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        department.setEmployees(employees);

        departmentRepository.save(department);

    }


}
