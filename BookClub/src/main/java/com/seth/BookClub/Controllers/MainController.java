package com.seth.BookClub.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.seth.BookClub.Models.Book;
import com.seth.BookClub.Models.LoginUser;
import com.seth.BookClub.Models.User;
import com.seth.BookClub.Services.BookService;
import com.seth.BookClub.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	UserService uServ;
	@Autowired
	BookService bServ;
	
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@GetMapping("/home")
	public String home(@ModelAttribute("user") User user, @ModelAttribute("newBook") Book book
			, Model model, HttpSession session) {
		String cUser = (String) session.getAttribute("email");
		List<User> users = uServ.allUsers();
		List<Book> books = bServ.findAll();
		User currentUser = uServ.findUserByEmail(cUser);
		// Checks if user is logged in to stop users that aren't logged in from accessing page
		if(session.getAttribute("isLogged_in") == null) {
			return "redirect:/";
		}
		model.addAttribute("cUser", currentUser);
		model.addAttribute("books", books);
		model.addAttribute("users", users);
		return "home.jsp";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("newBook") Book book, Model model, HttpSession session) {
		String cUser = (String) session.getAttribute("email");
		User currentUser = uServ.findUserByEmail(cUser);
		// Checks if user is logged in to stop users that aren't logged in from accessing page
		if(session.getAttribute("isLogged_in") == null) {
			return "redirect:/";
		}
		model.addAttribute("cUser", currentUser);
		return "create.jsp";
	}
	
	@GetMapping("/view/{id}")
	public String view(Model model, @PathVariable("id") Long id, HttpSession session) {
		Book thisbook = bServ.findById(id);
		User postUser = thisbook.getUser();
		// Checks if user is logged in to stop users that aren't logged in from accessing page
		if(session.getAttribute("isLogged_in") == null) {
			return "redirect:/";
		}
		model.addAttribute("pUser", postUser);
		model.addAttribute("book", thisbook);
		return "view.jsp";
	}
	
	@PostMapping("/addBook")
	public String addBook(@Valid @ModelAttribute("newBook") Book book,
			BindingResult result, Model model, HttpSession session) {
		
		if(result.hasErrors()) {
			model.addAttribute("newBook", new Book());
			return "index.jsp";
		}
		
		bServ.createBook(book);
		return "redirect:/home";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model, HttpSession session) {
		
		uServ.register(newUser, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		// Adds isLogged_in to session
		session.setAttribute("isLogged_in", true);
		return "redirect:/home";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result, Model model, HttpSession session) {
		
		uServ.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		// Adds isLogged_in to session
		session.setAttribute("isLogged_in", true);
		session.setAttribute("email", newLogin.getEmail());
		return "redirect:/home";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
