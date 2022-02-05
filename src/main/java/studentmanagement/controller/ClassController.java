package studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import studentmanagement.dao.ClassDAOImpl;
import studentmanagement.dto.ClassDTO;
import studentmanagement.model.ClassBean;

@Component
@ComponentScan("studentmanagement")
@Controller
public class ClassController {

	@Autowired
	private ClassDAOImpl dao;
	
	@RequestMapping(value = "/classRegisterModel", method = RequestMethod.GET)
	public ModelAndView calssRegisterModel() {
		return new ModelAndView("BUD003", "cbean", new ClassBean());
	}
	
	@RequestMapping(value = "/classRegister", method = RequestMethod.POST)
	public String classRegister(@ModelAttribute("cbean") @Validated ClassBean bean, BindingResult br, ModelMap model) {
		if(br.hasErrors()) {
			return "BUD003";
		}
		
		ClassDTO dto = new ClassDTO();
		dto.setId(bean.getId());
		
		List<ClassDTO> list = dao.selectOne(dto);
		if(list.size()!=0) {
			model.addAttribute("error","ClassID already exist!");
		} else {
			dto.setName(bean.getName());
			int i = dao.insertClass(dto);
			if(i>0) {
				model.addAttribute("success", "Class Successfully Registered");
			} else {
				model.addAttribute("error", "Class Register Fail!");
			}
		}
		return "BUD003";
	}
}
