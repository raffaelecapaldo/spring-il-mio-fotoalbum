package org.java.app.photoalbum.api;

import java.util.List;

import org.java.app.photoalbum.db.pojo.Photo;
import org.java.app.photoalbum.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoRestController {

	@Autowired
	private PhotoService photoService;
	
	//Per sicurezza preferisco non restituire in JSON
	//le non visibili (magari sono foto personali)
	//e quindi gestire quest'aspetto back-end
	@GetMapping
	@ResponseBody
public ResponseEntity<List<Photo>> getVisiblePhotosOrSearchThem(@RequestParam(required = false, name = "q") String query) {
		
		List<Photo> Photo = query == null ?
				 photoService.findAllVisibles():
				 photoService.findVisiblesByTitle(query);
		
		if (Photo.size() < 1) 
			 return new ResponseEntity<List<Photo>>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(Photo, HttpStatus.OK);
		
	}
}
