package com.example.eCommercewebapp.model.dao;


import com.example.eCommercewebapp.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataDAO extends JpaRepository<FileData,Long> {
    Optional<FileData> findByName(String fileName);
}
