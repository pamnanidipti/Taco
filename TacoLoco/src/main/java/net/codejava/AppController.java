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
	/**
	* This method renders the home page
	* 
	*/
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> listCustomer = dao.list();
		model.addAttribute("listCustomer",listCustomer);
		return "index";
	}
	
	/**
	* This method opens a new form
	* to help create new customer
	*/
	@RequestMapping("/new")
	public String showNewForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "new_form";
	}
	
	/**
	* This method initiates the save request, and redirects
	* to home page
	*/
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customer") Customer customer) {
		dao.save(customer);
		return "redirect:/";
	}
	
	/**
	* This method renders the edit form, with the customer details
	* populated
	*/
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_form");
		Customer customer = dao.get(id);
		mav.addObject("customer",customer);
		return mav;
	}
	
	/**
	* This method initiates the update request, and redirects
	* to home page
	*/
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("customer") Customer customer) {
		dao.update(customer);
		return "redirect:/";
	}
	
	/**
	* This method initiates the delete record request
	*/
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		dao.delete(id);
		return "redirect:/";
	}
}
