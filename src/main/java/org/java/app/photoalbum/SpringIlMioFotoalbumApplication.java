package org.java.app.photoalbum;

import org.java.app.photoalbum.db.pojo.Category;
import org.java.app.photoalbum.db.pojo.Photo;
import org.java.app.photoalbum.db.serv.CategoryService;
import org.java.app.photoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category("Natura");
		Category cat2 = new Category("Persone");
		Category cat3 = new Category("HD");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);

		
		Photo ph1 = new Photo("Il grande albero", "Gioco di luci con un albero",
				"https://img.freepik.com/free-photo/green-meadow-tree-sunset-beauty-generated-by-ai_188544-44226.jpg",
				true, cat1, cat3);
		
		Photo ph2 = new Photo("La bambina sull'altalena", "Foto dall'alto, studentessa gioca sull'altalena",
				"https://i1.adis.ws/i/canon/pro-issues-affecting-student-photographers-1_4961dcc710a54b35b57d7c1dc2c9a14f?$media-collection-full-dt-jpg",
				true, cat2, cat3);
		
		photoService.save(ph1);
		photoService.save(ph2);
		
		
	}

}
