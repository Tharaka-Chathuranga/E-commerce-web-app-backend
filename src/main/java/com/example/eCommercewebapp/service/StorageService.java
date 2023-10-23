package com.example.eCommercewebapp.service;


import com.example.eCommercewebapp.model.FileData;
import com.example.eCommercewebapp.model.ImageData;
import com.example.eCommercewebapp.model.Item;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.FileDataDAO;
import com.example.eCommercewebapp.model.dao.ItemDAO;
import com.example.eCommercewebapp.model.dao.StorageDAO;
import com.example.eCommercewebapp.model.dao.UserDAO;
import com.example.eCommercewebapp.model.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageDAO storageDAO;

    @Autowired
    private FileDataDAO fileDataDAO;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ItemDAO itemDAO;

    public StorageService(StorageDAO storageDAO,
                          FileDataDAO fileDataDAO,
                          UserDAO userDAO,
                          ItemDAO itemDAO) {
        this.storageDAO = storageDAO;
        this.fileDataDAO = fileDataDAO;
        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
    }

    private final String FOLDER_PATH = System.getProperty("user.dir") + "/src/main/resources/images/";


//    public String uploadImage(MultipartFile file) throws IOException {
//        ImageData imageData = storageDAO.save(ImageData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build());
//        if (imageData != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
//        }
//        return null;
//    }
//
//
//
//    public byte[] downloadImage(String fileName) {
//        Optional<ImageData> dbImageData = storageDAO.findByName(fileName);
//        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
//        return images;
//    }


    public FileData uploadImageToFileSystem(MultipartFile file, User user,String relation) throws IOException {

        String filePath=FOLDER_PATH+file.getOriginalFilename();
        FileData fileData= fileDataDAO.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (relation.equalsIgnoreCase("user")){

            if (fileData != null) {
            User exsistingUser = userDAO.findById(user.getId()).get();
            exsistingUser.setFileData(fileData);
            userDAO.save(exsistingUser);
            return fileData;
            }
            else{
                return null;
            }

        }
        else if (relation.substring(0,4).equalsIgnoreCase("item")){

                if (fileData != null) {
                Optional<Item> optionalItem = itemDAO.findById(Long.parseLong(relation.substring(4)));


                if (optionalItem.isPresent()) {
                    Item existingItem = optionalItem.get();
                    System.out.println(existingItem);
                    existingItem.setFileData(fileData);
                    itemDAO.save(existingItem);

                    return fileData;
                }
                else {
                    return null;
                }

            }
            else{

                return null;
            }

        }


        return null;
    }



    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataDAO.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


    public String deleteImageUser(User user){
        FileData fileData = fileDataDAO.findById(user.getFileData().getId()).get();
        if (fileData != null) {
            User exsistingUser = userDAO.findById(user.getId()).get();
            exsistingUser.setFileData(null);
            userDAO.save(exsistingUser);
            fileDataDAO.deleteById(fileData.getId());

            return "Image Remove Successfully";
        }
        return "Image Remove Unsuccessfully";

    }
}
