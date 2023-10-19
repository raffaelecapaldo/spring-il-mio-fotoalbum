package org.java.app.photoalbum.db.serv;

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

}
