package com.example.eCommercewebapp.model.dao;


import com.example.eCommercewebapp.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StorageDAO extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}
