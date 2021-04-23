package com.springbootapp.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserMvcController {
	@Autowired
	UserService service;
	@Autowired
	private UserRepository UserRepository;
	//Pagination Sorting according to username
	@RequestMapping("/")
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "fname", "asc", model);		
	}
	//PageNumber selection
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<User> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listUsers", listUsers);
	    return "list-users";
	}
	//For Updating or editing the existing user
	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editUserById(Model model, @PathVariable("id") Optional<Integer> id) throws UserNotFoundException {
		if (id.isPresent()) {
			User entity = service.getUserById(id.get());
			model.addAttribute("user", entity);

		} else {
			model.addAttribute("user", new User());

		}
		return "add-edit-user";
	}
	//Deleting a particular user
	@RequestMapping(path = "delete/{id}")
	public String deleteUserById(Model model, @PathVariable("id") Integer id) throws UserNotFoundException {
		service.deleteUserById(id);
		return "redirect:/";
	}
//Creating or adding a particular user
	@RequestMapping(path = "/createUser", method = RequestMethod.POST)
	public String createOrUpdateEmployee(User user) {
		service.createOrUpdateUser(user);
		return "redirect:/";
	}
}
