package org.java.app.photoalbum.controller;

import java.util.List;

import org.java.app.photoalbum.db.pojo.Category;
import org.java.app.photoalbum.db.serv.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping public String getIndex(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		return "categories/category-index";
		
	}
}
