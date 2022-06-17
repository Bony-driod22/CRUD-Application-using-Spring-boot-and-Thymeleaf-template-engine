package com.example.employeeregistration.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import com.example.employeeregistration.model.Employee;
import com.example.employeeregistration.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        
    }
    @Override
    public Employee getEmployeebyId(long id) {
        Optional<Employee> optional=employeeRepository.findById(id);
        Employee employee=null;
        if(optional.isPresent())
        {
            employee=optional.get();
        }
        else
        {
            throw new RuntimeErrorException(null, " Employee not found for id : : "+id);
        }
        return employee;
    }
    @Override
    public void deleteEmployeebyId(long id) {
        this.employeeRepository.deleteById(id);
        
    }
    @Override
 public Page<Employee> findPaginated(int pageNo, int pageSize) {
 Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
 return this.employeeRepository.findAll(pageable);
}
    
}
