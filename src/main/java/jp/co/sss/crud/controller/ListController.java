package jp.co.sss.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListController {
	
	@RequestMapping("/list")
	public String showList(Model model) {


//		EmployeeRepository repository;
//		
//		model.addAttribute("employees", repository.findAll());
		return "list/list";
	}
	
}
