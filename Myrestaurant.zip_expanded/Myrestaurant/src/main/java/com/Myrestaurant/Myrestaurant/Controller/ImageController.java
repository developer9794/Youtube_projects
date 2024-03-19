package com.Myrestaurant.Myrestaurant.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Myrestaurant.Myrestaurant.Entity.ImageEntity;
import com.Myrestaurant.Myrestaurant.Service.ImageService;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageService.uploadImage(file);
        return ResponseEntity.ok("Image uploaded successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageData = imageService.getImageData(id);
        if (imageData != null) {
            return ResponseEntity.ok(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/ram")
    public String displayAllImages(Model model) {
        List<ImageEntity> images = imageService.getAllImages();
        System.out.println(images);
        model.addAttribute("images", images);
        return "pages/Cook"; // Thymeleaf template name
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Long>> getAllImageIds() {
        List<Long> imageIds = imageService.getAllImageIds();
        
        return ResponseEntity.ok(imageIds);
    }
    
}
