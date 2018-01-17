package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.model.Employee;
import com.springboot.service.EmployeeService;
import com.springboot.util.JSONUtil;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

//	@Autowired
//	private EmployeeService employeeService;
//	
//	@RequestMapping("/findAll")
//	public String findAll(Model model){
//		List<Employee> employees = employeeService.findAll();
//		model.addAttribute("employees", employees);
//		System.out.println(JSONUtil.objectToJson(employees));
//		return "emp/list";
//	}
}
