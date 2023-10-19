package org.java.app.photoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.photoalbum.db.pojo.Photo;
import org.java.app.photoalbum.db.serv.CategoryService;
import org.java.app.photoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	@Autowired
	private CategoryService categoryService;
	
	private String savePhoto(Photo photo, BindingResult bindingResult, Model model, 
			RedirectAttributes ra, boolean isNew) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "photos/photo-create";
		}
		else {
			ra.addFlashAttribute("updateMessage", "Foto " + (isNew ? 
															"aggiunta " :
															"modificata ")
														+ "correttamente!");
			photoService.save(photo);
			return "redirect:/photos/" + photo.getId();

		}}
	
	@GetMapping public String getIndex(Model model,
			@RequestParam(required = false) String title) {
		
		List<Photo> photos = title == null ?
							 photoService.findAll():
							 photoService.findByTitle(title);
		
		model.addAttribute("photos", photos);
		model.addAttribute("title", title);
		
		return "photos/photo-index";
		
	}
	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		Optional<Photo> otpPhoto = photoService.findById(id);
		if (otpPhoto.isEmpty()) {
			return "redirect:/photos";
		}
		Photo photo = otpPhoto.get();
		model.addAttribute("photo", photo);
		
		return "photos/photo-show";
	}
	
	@GetMapping("/create")
	public String getCreateForm(Model model) {
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("photo", new Photo());
		
		return "photos/photo-create";
	}
	
	@PostMapping("/create")
	public String storePhoto(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model,
			RedirectAttributes ra) {
		return savePhoto(photo, bindingResult, model, ra, true);
	}
	
	@GetMapping("/update/{id}")
	public String photoUpdate(@PathVariable int id, Model model) {
		Optional<Photo> optPhoto = photoService.findById(id);
		if (optPhoto.isEmpty()) {
			return "redirect:/photos";
		}
		Photo photo = optPhoto.get();
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categoryService.findAll());

		
		return "photos/photo-create";
	}
	
	@PostMapping("/update/{id}")
	public String updatePhoto(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model,
			RedirectAttributes ra) {
		return savePhoto(photo, bindingResult, model, ra, false);		
	}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id, RedirectAttributes ra) {
		Optional<Photo> optPhoto = photoService.findById(id);
		if (optPhoto.isEmpty()) {
			return "redirect:/photos";
		}
		Photo photo = optPhoto.get();
		ra.addFlashAttribute("updateMessage", "Foto con ID: " + photo.getId() + " (" + photo.getTitle() + ") cancellata");
		photoService.delete(photo);
		return "redirect:/photos";
	}
}
