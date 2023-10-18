package org.java.app.photoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.photoalbum.db.pojo.Photo;
import org.java.app.photoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@GetMapping public String getIndex(Model model,
			@RequestParam(required = false) String title) {
		
		List<Photo> photos = title == null ?
							 photoService.findAll():
							 photoService.findByTitle(title);
		
		model.addAttribute("photos", photos);
		
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
}
