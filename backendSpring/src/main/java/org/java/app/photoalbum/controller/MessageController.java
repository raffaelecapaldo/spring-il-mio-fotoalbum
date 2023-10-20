package org.java.app.photoalbum.controller;

import java.util.List;

import org.java.app.photoalbum.db.pojo.Message;
import org.java.app.photoalbum.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	@GetMapping 
	public String getIndex(Model model) {
			
			List<Message> messages = messageService.findAll();
			model.addAttribute("messages", messages);
			return "messages/message-index";
			
		}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id, RedirectAttributes ra) {
		messageService.delete(messageService.findById(id));
		ra.addFlashAttribute("updateMessage", "Messagio con ID " + id + " cancellato");
		return "redirect:/messages";
		
	}
	}
