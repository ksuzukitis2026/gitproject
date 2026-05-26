package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {
	
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/list")
	public String showList(Model model, EmployeeForm form) {
		session.setAttribute("empName", repository.findEmpName(form.getEmpId()));
		model.addAttribute("employees", repository.findAll());
		return "list/list";
	}
	
	@RequestMapping("/list/empName")
		public String findByEmpName(EmployeeForm employeeForm, Model model) {
			model.addAttribute("employees", repository.findByEmpNameContaining(employeeForm.getEmpName()));
			return "list/list";
		}
	
}
