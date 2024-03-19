package com.Myrestaurant.Myrestaurant.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Myrestaurant.Myrestaurant.Entity.ImageEntity;
import com.Myrestaurant.Myrestaurant.Repositery.ImageRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void uploadImage(MultipartFile file) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageData(file.getBytes());
        imageRepository.save(imageEntity);
    }
    @Transactional
    public List<Long> getAllImageIds() {
        return imageRepository.findAllImageIds();
    }
    public byte[] getImageData(Long id) {
        return imageRepository.findById(id)
            .map(ImageEntity::getImageData)
            .orElse(null);
    }
    
    public List<ImageEntity> getAllImages() {
        return imageRepository.findAll();
    }
}
