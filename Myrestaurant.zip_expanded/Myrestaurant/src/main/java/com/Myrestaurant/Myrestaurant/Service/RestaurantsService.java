package com.Myrestaurant.Myrestaurant.Service;

import com.Myrestaurant.Myrestaurant.Entity.RestaurantsEntity;
import com.Myrestaurant.Myrestaurant.Repositery.RestaurantsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantsService   {

    private final RestaurantsRepository restaurantsRepository;

    @Autowired
    public RestaurantsService(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    public List<RestaurantsEntity> getAllRestaurants() {
        return restaurantsRepository.findAll();
    }

    
    public RestaurantsEntity getRestaurantById(Long id) {
        Optional<RestaurantsEntity> optionalRestaurant = restaurantsRepository.findById(id);
        return optionalRestaurant.orElse(null);
    }


    public RestaurantsEntity createRestaurant(RestaurantsEntity restaurant) {
        return restaurantsRepository.save(restaurant);
    }

   
    public RestaurantsEntity updateRestaurant(Long id, RestaurantsEntity restaurant) {
        if (restaurantsRepository.existsById(id)) {
            restaurant.setRestId(id); // Ensure the correct ID is set
            return restaurantsRepository.save(restaurant);
        } else {
            return null;
        }
    }


    public boolean deleteRestaurant(Long id) {
        if (restaurantsRepository.existsById(id)) {
            restaurantsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
