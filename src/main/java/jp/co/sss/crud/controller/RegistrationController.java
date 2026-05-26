package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	private EmployeeRepository repository;
	
	@RequestMapping("/regist/input")
	public String registInput(@ModelAttribute EmployeeForm form, Model model) {
		form.setGender(1);
		form.setAuthority(1);
		model.addAttribute("employee", form);
		return "regist/regist_input";
	}

	@PostMapping("/regist/check")
	public String registCheck(@Valid @ModelAttribute EmployeeForm form,  BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "regist/regist_input";
		} else {
		model.addAttribute("employee", form);
		return "regist/regist_check";
		}
	}

	@PostMapping("/regist/complete")
	public String registComplete(EmployeeForm form, Model model) {
		Employee employee = new Employee();
		Department department = new Department();
		department.setDeptId(form.getDeptId());
		employee.setDepartment(department);
		BeanUtils.copyProperties(form, employee, "empId");
		employee = repository.save(employee);
		EmployeeBean employeeBean = new EmployeeBean();
		BeanUtils.copyProperties(employee, employeeBean);
		model.addAttribute("employee", employeeBean);
		return "regist/regist_complete";
	}

}
