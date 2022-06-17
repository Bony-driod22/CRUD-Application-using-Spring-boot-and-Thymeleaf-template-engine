package com.example.employeeregistration.service;

import java.util.List;

import com.example.employeeregistration.model.Employee;

import org.springframework.data.domain.Page;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public Employee getEmployeebyId(long id);
    public void deleteEmployeebyId(long id);
    public Page<Employee> findPaginated(int pageNo,int pageSize);
}
