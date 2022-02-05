package studentmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studentmanagement.dao.ClassDAOImpl;
import studentmanagement.dao.StudentDAOImpl;
import studentmanagement.dto.ClassDTO;
import studentmanagement.dto.StudentDTO;
import studentmanagement.model.SearchBean;
import studentmanagement.model.StudentBean;

@Component
@ComponentScan("studentmanagement")
@Controller
public class StudentController {

	@Autowired
	private StudentDAOImpl dao;

	@Autowired
	private ClassDAOImpl cdao;

	@RequestMapping(value = "/studentsearch", method = RequestMethod.GET)
	public ModelAndView studentSearch(@ModelAttribute("success") String success, ModelMap model) {
		model.addAttribute("success", success);
		return new ModelAndView("BUD001", "ssBean", new SearchBean());
	}

	@RequestMapping(value = "/studentResult", method = RequestMethod.GET)
	public String studentResult(@ModelAttribute("ssBean") SearchBean bean, ModelMap model) {
		StudentDTO dto = new StudentDTO();
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		dto.setStudentId(bean.getStudentId());
		dto.setStudentName(bean.getStudentName());
		dto.setClassName(bean.getClassName());

		if (dto.getStudentId().equals("") && dto.getStudentName().equals("") && dto.getClassName().equals("")) {
			list = dao.selectAll();
		} else {
			list = dao.selectOne(dto);
		}

		if (list.size() == 0) {
			model.addAttribute("error", "No Student found!");
		}
		model.addAttribute("stuList", list);
		return "BUD001";
	}

	@RequestMapping(value = "/studentRegisterModel", method = RequestMethod.GET)
	public ModelAndView studentRegisterModel(@ModelAttribute("success") String success, ModelMap model) {
		model.addAttribute("success", success);
		return new ModelAndView("BUD002", "stubean", new StudentBean());
	}

	@RequestMapping(value = "/studentRegister", method = RequestMethod.POST)
	public String studentRegister(@ModelAttribute("stubean") @Validated StudentBean bean, BindingResult br,
			ModelMap model, RedirectAttributes ra) {
		if (br.hasErrors() || bean.getYear().equals("Year") || bean.getMonth().equals("Month")
				|| bean.getDay().equals("Day")) {
			model.addAttribute("error", "Register date can't be blank!");
			return "BUD002-01";
		}

		String y = bean.getYear();
		String m = bean.getMonth();
		String d = bean.getDay();

		StudentDTO dto = new StudentDTO();
		dto.setStudentId(bean.getStudentId());
		List<StudentDTO> list = dao.selectOne(dto);
		if (list.size() != 0) {
			model.addAttribute("error", "StudentID already exist!");
			return "BUD002";
		} else {
			dto.setStudentName(bean.getStudentName());
			dto.setClassName(bean.getClassName());
			dto.setRegisterDate(y + "-" + m + "-" + d);
			dto.setStatus(bean.getStatus());
			int i = dao.insertStudent(dto);
			if (i > 0) {
				ra.addAttribute("success", "Student successfully registered");
				return "redirect:/studentRegisterModel";
			} else {
				model.addAttribute("error", "Student register fail!");
				return "BUD002";
			}
		}
	}

	@RequestMapping(value = "/studentUpdateModel/{studentId}", method = RequestMethod.GET)
	public ModelAndView studentUpdateModel(@PathVariable String studentId) {
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(studentId);
		List<StudentDTO> list = dao.selectOne(dto);
		StudentBean stuBean = new StudentBean();
		for (StudentDTO res : list) {
			stuBean.setStudentId(res.getStudentId());
			stuBean.setStudentName(res.getStudentName());
			stuBean.setClassName(res.getClassName());
			stuBean.setStatus(res.getStatus());
			String[] dt = res.getRegisterDate().toString().split("-");

			stuBean.setYear(dt[0]);
			stuBean.setMonth(dt[1]);
			stuBean.setDay(dt[2]);
		}
		return new ModelAndView("BUD002-01", "stubean", stuBean);
	}

	@RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
	public String studentUpdate(@ModelAttribute("stubean") @Validated StudentBean bean, BindingResult br,
			ModelMap model) {
//		
		if (br.hasErrors() || bean.getYear().equals("Year") || bean.getMonth().equals("Month")
				|| bean.getDay().equals("Day")) {
			model.addAttribute("error", "Register date can't be blank!");
			return "BUD002-01";
		}

		String y = bean.getYear();
		String m = bean.getMonth();
		String d = bean.getDay();

		StudentDTO dto = new StudentDTO();
		dto.setStudentId(bean.getStudentId());
		dto.setStudentName(bean.getStudentName());
		dto.setClassName(bean.getClassName());
		dto.setRegisterDate(y + "-" + m + "-" + d);
		dto.setStatus(bean.getStatus());
		int i = dao.updateStudent(dto);
		if (i == 0) {
			model.addAttribute("error", "Student update Fail");
			return "BUD002-01";
		}
		model.addAttribute("success", "Student successfully updated");
		return "BUD002-01";
	}

	@RequestMapping(value = "/studentDelete/{studentId}", method = RequestMethod.GET)
	public String studentDelete(@PathVariable String studentId, ModelMap model, RedirectAttributes ra) {
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(studentId);
		int i = dao.deleteStudent(dto);
		if (i > 0) {
			ra.addAttribute("success", "Delete successful");
		} else {
			ra.addAttribute("error", "Delete Fail!");
		}
		return "redirect:/studentsearch";
	}

	@ModelAttribute("classList")
	public List<ClassDTO> classList() {
		
		return cdao.selectAll();
	}
}
