package org.java.app.photoalbum.db.serv;

import java.util.List;

import org.java.app.photoalbum.db.pojo.Message;
import org.java.app.photoalbum.db.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepo messageRepo;
	
	public void save(Message message) {
		messageRepo.save(message);
	}
	
	public void delete(Message message) {
		messageRepo.delete(message);
	}
	
	public Message findById(int id) {
		return messageRepo.findById(id).get();
	}
	
	public List<Message> findAll() {
		return messageRepo.findAll();
	}

}
