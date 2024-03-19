package com.Myrestaurant.Myrestaurant.Repositery;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Myrestaurant.Myrestaurant.Entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
	   @Query("SELECT i FROM ImageEntity i")
	List<Long> findAllImageIds();
}
