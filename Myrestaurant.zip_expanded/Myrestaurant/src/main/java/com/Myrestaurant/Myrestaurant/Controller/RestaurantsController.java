package com.Myrestaurant.Myrestaurant.Controller;

import com.Myrestaurant.Myrestaurant.Constant;
import com.Myrestaurant.Myrestaurant.Entity.RestaurantsEntity;
import com.Myrestaurant.Myrestaurant.Service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin
public class RestaurantsController {
	Map<String, Long> ConstantMap=Constant.mapdata;
    private final RestaurantsService restaurantsService;

    @Autowired
    public RestaurantsController(RestaurantsService restaurantsService) {
        this.restaurantsService = restaurantsService;
    }
    @GetMapping("/session")
    public Map<String, Long> session() {
    
        return Constant.getmapdata(); // Return the name of the HTML template (without the ".html" extension)
    }
    @GetMapping
    public ResponseEntity<List<RestaurantsEntity>> getAllRestaurants() {
        List<RestaurantsEntity> restaurants = restaurantsService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantsEntity> getRestaurantById(@PathVariable Long id) {
        RestaurantsEntity restaurant = restaurantsService.getRestaurantById(id);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RestaurantsEntity> createRestaurant(@RequestBody RestaurantsEntity restaurant) {
        RestaurantsEntity createdRestaurant = restaurantsService.createRestaurant(restaurant);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantsEntity> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantsEntity restaurant) {
        RestaurantsEntity updatedRestaurant = restaurantsService.updateRestaurant(id, restaurant);
        if (updatedRestaurant != null) {
            return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        boolean deleted = restaurantsService.deleteRestaurant(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
