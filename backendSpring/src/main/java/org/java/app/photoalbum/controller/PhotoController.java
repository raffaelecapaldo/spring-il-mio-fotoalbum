package org.java.app.photoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.photoalbum.db.pojo.Photo;
import org.java.app.photoalbum.db.serv.CategoryService;
import org.java.app.photoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	@Autowired
	private CategoryService categoryService;
	
	private boolean isSuperAdmin(UserDetails user) {
		return user.getAuthorities().stream().anyMatch(auth -> "SUPER_ADMIN".equals(auth.getAuthority()));
		
	}
	
	private boolean sameUser(UserDetails userPhoto, UserDetails loggedUser) {
		return userPhoto.getUsername().equals(loggedUser.getUsername()) 
				&& userPhoto.getPassword().equals(loggedUser.getPassword());
	}
	
	@GetMapping public String getIndex(Model model,
			@RequestParam(required = false) String title, 
			@AuthenticationPrincipal org.java.app.photoalbum.auth.pojo.User user) {
		
		List<Photo> photos = null;
		
		if (!isSuperAdmin(user)) {
		 photos = title == null ?
							 photoService.findAllByUser(user):
							 photoService.findAllByUserWithTitle(user, title);
		
		}
		
		else {
			
			 photos = title == null ?
					 photoService.findAll():
					 photoService.findByTitle(title);
			
		}
		
		model.addAttribute("photos", photos);
		model.addAttribute("title", title);
		
		return "photos/photo-index";
		
	}
	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails user) {
		
		
		
		Optional<Photo> otpPhoto = photoService.findById(id);
		if (otpPhoto.isEmpty()) {
			return "redirect:/photos";
		}
		Photo photo = otpPhoto.get();
		model.addAttribute("photo", photo);
		
		UserDetails userPhoto = photo.getUser();

		if (!sameUser(userPhoto, user) && (!isSuperAdmin(user)))
		    throw new ResponseStatusException(HttpStatus.FORBIDDEN); 

		
		
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
			RedirectAttributes ra, @AuthenticationPrincipal UserDetails user) {
		
	
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "photos/photo-create";
		}
		else {
			ra.addFlashAttribute("updateMessage", "Foto aggiunta correttamente!");
			photo.setUser((org.java.app.photoalbum.auth.pojo.User) user);
			photoService.save(photo);
			return "redirect:/photos/" + photo.getId();
			
	}}
	
	@GetMapping("/update/{id}")
	public String photoUpdate(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails user) {
		Photo fullInfoPhoto = photoService.findById(id).get();
		org.java.app.photoalbum.auth.pojo.User userPhoto = fullInfoPhoto.getUser();
		if (!sameUser(userPhoto, user) && (!isSuperAdmin(user))) 
		    throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
		
		
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
			RedirectAttributes ra, @AuthenticationPrincipal UserDetails user) {
		
		Photo fullInfoPhoto = photoService.findById(photo.getId()).get();
		org.java.app.photoalbum.auth.pojo.User userPhoto = fullInfoPhoto.getUser();
		if (!sameUser(userPhoto, user) && (!isSuperAdmin(user))) 
		    throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "photos/photo-create";
		}
		else {
			
			ra.addFlashAttribute("updateMessage", "Foto aggiunta correttamente!"); 
			photo.setUser(userPhoto);
			photoService.save(photo);
			return "redirect:/photos/" + photo.getId();
		}}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id, RedirectAttributes ra, @AuthenticationPrincipal UserDetails user) {
		Optional<Photo> optPhoto = photoService.findById(id);
		if (optPhoto.isEmpty()) {
			return "redirect:/photos";
		}
		
		Photo photo = optPhoto.get();

		org.java.app.photoalbum.auth.pojo.User userPhoto = photo.getUser();

		if (!sameUser(userPhoto, user) && (!isSuperAdmin(user)))
		    throw new ResponseStatusException(HttpStatus.FORBIDDEN); 

		
		
		ra.addFlashAttribute("updateMessage", "Foto con ID: " + photo.getId() + " (" + photo.getTitle() + ") cancellata");
		photoService.delete(photo);
		return "redirect:/photos";
	}
	
	@PostMapping("/change-visibility/{id}")
	public String changeVisibilityPhoto(@PathVariable int id, RedirectAttributes ra) {
		
		Photo photo = photoService.findById(id).get();
		photo.setVisible(!photo.isVisible());
		photoService.save(photo);
		
		ra.addFlashAttribute("updateMessage", "Foto con ID: " + photo.getId() + " (" + photo.getTitle() + ") "
		+ (photo.isVisible() ? "resa visibile" : "resa nascosta"));
		return "redirect:/photos";
		
	}}
