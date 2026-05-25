package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {
	
	@Autowired
	EmployeeRepository repository;
	
	@RequestMapping("/list")
	public String showList(Model model) {
		model.addAttribute("employees", repository.findAll());
		return "list/list";
	}
	
	@RequestMapping("/list/empName")
		public String findByEmpName2(EmployeeForm employeeForm, Model model) {
			model.addAttribute("employees", repository.findByEmpNameContaining(employeeForm.getEmpName()));
			return "list/list";
		}
	
	@PostMapping("/regist/input")
	public String registemp(EmployeeForm form, Model model) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee, "empId");
		employee=repository.save(employee);
		EmployeeBean employeeBean = new EmployeeBean();
		BeanUtils.copyProperties(employee, employeeBean);
		model.addAttribute("employee", employeeBean);
		return "regist/regist_input";
	}
	
}
