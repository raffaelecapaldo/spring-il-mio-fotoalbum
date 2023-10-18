package org.java.app.photoalbum.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 89, nullable = false, unique = true)
	@Length(min = 2, max = 80, message = "La categoria deve avere un nome tra i 2 e gli 80 caratteri")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private List<Photo> photos;

	
	public Category() { }
	public Category(String name) {
		setName(name);
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "[ID]: " + getId() + "\n"
				+ "[Name]: " + getName();
	}
	

}
