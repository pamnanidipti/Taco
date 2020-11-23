package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private CustomerDAO dao;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> listCustomer = dao.list();
		model.addAttribute("listCustomer",listCustomer);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "new_form";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customer") Customer customer) {
		dao.save(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_form");
		Customer customer = dao.get(id);
		mav.addObject("customer",customer);
		return mav;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("customer") Customer customer) {
		dao.update(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		dao.delete(id);
		return "redirect:/";
	}
}
