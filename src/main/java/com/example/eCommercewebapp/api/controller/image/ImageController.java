package com.example.eCommercewebapp.api.controller.image;


import com.example.eCommercewebapp.api.model.DeleteFileBody;
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
@CrossOrigin( "http://localhost:5173")
public class ImageController {

	@Autowired
	private StorageService service;


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
		System.out.println(user);
		String response = service.deleteImageUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(response);

	}

	@GetMapping("/fileSystem/{fileId}")
	public ResponseEntity<?> downloadUserImageFromFileSystem(@AuthenticationPrincipal User user, @PathVariable Long fileId) throws IOException {
		byte[] imageData=service.downloadImageFromFileSystem(fileId);

		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	@GetMapping("/productFileSystem/{fileId}")
	public ResponseEntity<?> downloadProductImageFromFileSystem( @PathVariable Long fileId) throws IOException {
		byte[] imageData=service.downloadImageFromFileSystem(fileId);

		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	@PutMapping("/deleteImage")
	public ResponseEntity<?> deleteImage(@AuthenticationPrincipal User user, @RequestBody DeleteFileBody deleteFileBody){
		 if ("admin".equals(user.getRole())) {
			 String response = service.deleteImage(deleteFileBody);
			 return ResponseEntity.status(HttpStatus.OK)
				.body(response);
		 }
		return ResponseEntity.status(HttpStatus.OK)
				.body("Can not process request");

	}

}
