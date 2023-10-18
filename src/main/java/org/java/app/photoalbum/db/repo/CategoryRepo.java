package org.java.app.photoalbum.db.repo;

import org.java.app.photoalbum.db.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
