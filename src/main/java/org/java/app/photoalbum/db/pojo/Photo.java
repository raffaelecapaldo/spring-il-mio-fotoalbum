package org.java.app.photoalbum.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 80, nullable = false)
	@Length(min = 3, max = 80, message = "La foto deve avere un titolo tra i 3 e gli 80 caratteri")
	private String title;
	
	@Length(max = 255, message = "La descrizione ha un limite massimo di 255 caratteri")
	@Nullable
	private String description;
	
	@Length(min = 3, max = 255, message = "L'URL della foto deve avere una lunghezza tra i 3 e i 255 caratteri")
	private String url;
	
	@NotNull
	private boolean visible;
	
	@ManyToMany
	private List<Category> categories;
	
	
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	public Photo () { }
	public Photo (String title, String description, 
			String url, boolean visible, Category...categories ) {
		
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setCategories(Arrays.asList(categories));
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@Override
	public String toString() {
		return "[ID]: " + getId() + "\n"
				+ "[Title]: " + getTitle() + "\n"
				+ "[Description]: " + getDescription() + "\n"
				+ "[Photo url]: " + getUrl() + "\n"
				+ "[Is visible]: " + isVisible();
	}
 }
