package com.exam.spring.service;

import com.exam.spring.entity.Employee;
import com.exam.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee Employee){
        return employeeRepository.save(Employee);
    }

    public Employee updateEmployee(Long id, Employee employee){
        return employeeRepository.findById(id)
                .map(e-> {
                    e.setName(employee.getName());
                    e.setAge(employee.getAge());
                    e.setSalary(employee.getSalary());
                    return employeeRepository.save(e);
                })
                .orElseGet(()->{
                    employee.setId(id);
                    return employeeRepository.save(employee);
                });
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployee(String search){
        return employeeRepository.findAllByNameContaining(search);
    }
}
