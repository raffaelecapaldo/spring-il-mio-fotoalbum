package org.java.app.photoalbum.db.serv;

import java.util.List;
import java.util.Optional;


import org.java.app.photoalbum.db.pojo.Category;
import org.java.app.photoalbum.db.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}
	
	public Optional<Category> findById(int id) {
		return categoryRepo.findById(id);
		}
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
}
