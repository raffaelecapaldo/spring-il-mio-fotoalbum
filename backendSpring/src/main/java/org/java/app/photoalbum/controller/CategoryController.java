package org.java.app.photoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.photoalbum.db.pojo.Category;
import org.java.app.photoalbum.db.serv.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

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
	
	@GetMapping("/create")
	public String getCreateForm(Model model) {
		model.addAttribute("category", new Category());
		return "categories/category-create";
	}
	
	
	
	@PostMapping("/create")
	public String storeCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model, 
			RedirectAttributes ra, @AuthenticationPrincipal UserDetails user) {
		if (bindingResult.hasErrors()) {
			 return "categories/category-create";
		}
		else {
			try {
				categoryService.save(category);
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("nameNotUnique", "Categoria con nome già esistente");
				 return "categories/category-create";			}
			ra.addFlashAttribute("updateMessage", "Categoria aggiunta correttamente");
			return "redirect:/categories";
		}}
	
	@PostMapping("/delete/{id}")
	public String deleteCategory(@PathVariable int id, RedirectAttributes ra, @AuthenticationPrincipal UserDetails user) {
		
		Optional<Category> optCategory = categoryService.findById(id);
		if (optCategory.isEmpty()) {
			return "redirect:/categories";
		}
		Category category = optCategory.get();
		categoryService.detachPhotos(category);
		categoryService.delete(category);
		ra.addFlashAttribute("updateMessage", "Categoria rimossa correttamente");

		return "redirect:/categories";
	}
}
