package com.example.employeeregistration.Controller;

//import java.net.http.WebSocket.Listener;
import java.util.List;

import com.example.employeeregistration.model.Employee;
import com.example.employeeregistration.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    //display list of employees
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return findPaginated(1,model);
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model)
    {
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";

    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value="id") long id,Model model)
    {
        Employee employee=employeeService.getEmployeebyId(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value="id") long id)
    {
        //call delete employee method
        this.employeeService.deleteEmployeebyId(id);
        return "redirect:/";
    } 
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value="pageNo") int pageNo,Model model)
    {
        int pageSize=5;
        Page<Employee> page=employeeService.findPaginated(pageNo, pageSize);
        List<Employee> listEmployee=page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listEmployees",listEmployee);
        

        return "index";
    }
}