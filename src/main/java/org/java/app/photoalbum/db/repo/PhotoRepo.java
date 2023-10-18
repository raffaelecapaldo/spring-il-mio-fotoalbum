package org.java.app.photoalbum.db.repo;

import org.java.app.photoalbum.db.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {

}
