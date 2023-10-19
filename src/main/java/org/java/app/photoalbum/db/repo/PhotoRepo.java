package org.java.app.photoalbum.db.repo;

import java.util.List;

import org.java.app.photoalbum.db.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {

	public List<Photo> findByTitleContaining(String title);
	
	public List<Photo> findByVisibleTrue();
	
	public List<Photo> findByTitleContainingAndVisibleTrue(String title);

}
