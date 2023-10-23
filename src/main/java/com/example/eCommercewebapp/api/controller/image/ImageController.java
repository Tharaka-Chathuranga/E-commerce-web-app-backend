package com.example.eCommercewebapp.api.controller.image;


import com.example.eCommercewebapp.model.FileData;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private StorageService service;

//	@PostMapping
//	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
//		String uploadImage = service.uploadImage(file);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(uploadImage);
//	}
//
//	@GetMapping("/{fileName}")
//	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
//		byte[] imageData=service.downloadImage(fileName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//
//	}


	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFIleSystem(
			@AuthenticationPrincipal User user,
			@RequestParam("image")MultipartFile file,
			@RequestParam("relation")String relation) throws IOException {



		FileData uploadImage = service.uploadImageToFileSystem(file,user,relation);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}




	@PostMapping("/fileSystem/deleteUserImage")
	public ResponseEntity<?> deleteImageUser(@AuthenticationPrincipal User user) throws IOException {
		User currentUser = new User();
		currentUser = user;
		System.out.println(currentUser);
		String response = service.deleteImageUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(response);

	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData=service.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

}
