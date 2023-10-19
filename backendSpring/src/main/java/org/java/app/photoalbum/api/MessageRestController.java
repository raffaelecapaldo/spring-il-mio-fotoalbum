package org.java.app.photoalbum.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.java.app.photoalbum.db.pojo.Message;
import org.java.app.photoalbum.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/message")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping()
	@ResponseBody
	public Map<String, Object> saveMessage(@RequestBody Message message) {
		messageService.save(message);
		
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("sent", true);
		
		
		return info;
	}

}
