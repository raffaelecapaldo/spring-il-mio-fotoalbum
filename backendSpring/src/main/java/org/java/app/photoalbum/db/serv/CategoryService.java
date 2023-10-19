package org.java.app.photoalbum.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.photoalbum.db.pojo.Category;
import org.java.app.photoalbum.db.pojo.Photo;
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
	
	public void delete(Category category) {
		categoryRepo.delete(category);
	}
	
	public void detachPhotos(Category Category) {
		List<Photo> photos = Category.getPhotos();
		for (Photo photo : photos) {
			List<Category> allCategories = photo.getCategories();
			allCategories.remove(Category);
			photo.setCategories(allCategories);
			}
		}
}
