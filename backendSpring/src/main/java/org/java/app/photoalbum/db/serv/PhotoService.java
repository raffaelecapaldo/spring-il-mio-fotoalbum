package org.java.app.photoalbum.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.photoalbum.auth.pojo.User;
import org.java.app.photoalbum.db.pojo.Photo;
import org.java.app.photoalbum.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll() {
		return photoRepo.findAll();
	}
	
	public Optional<Photo> findById(int id) {
		return photoRepo.findById(id);
	}
	
	public void save(Photo photo) {
		photoRepo.save(photo);
	}
	
	public List<Photo> findByTitle(String title) {
		return photoRepo.findByTitleContaining(title);
	}
	
	public void delete(Photo photo) {
		photoRepo.delete(photo);
	}
	
	public List<Photo> findAllVisibles() {
		return photoRepo.findByVisibleTrue();
	}
	
	public List <Photo> findVisiblesByTitle(String title) {
		return photoRepo.findByTitleContainingAndVisibleTrue(title);
	}
	
	public List <Photo> findAllByUser(User user) {
		return photoRepo.findByUser(user);
	}
	
	public List <Photo> findAllByUserWithTitle(User user, String title) {
		return photoRepo.findByTitleContainingAndUser(title, user);
	}

}
