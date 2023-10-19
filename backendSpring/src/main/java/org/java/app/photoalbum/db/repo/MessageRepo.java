package org.java.app.photoalbum.db.repo;

import org.java.app.photoalbum.db.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {

}
