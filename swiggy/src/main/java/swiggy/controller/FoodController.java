package swiggy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swiggy.domain.Food;
import swiggy.services.FoodService;

@Controller
@RequestMapping("/food")
public class FoodController {


    @Autowired
    FoodService foodService;

    @PostMapping("/create")
    public ResponseEntity createFood(@RequestBody Food food){

        return new ResponseEntity(foodService.createFood(food), HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity updateFood(@RequestBody Food food) {

        return new ResponseEntity(foodService.updateFood(food),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteFood(@RequestBody Food food) {

        return new ResponseEntity(foodService.deleteFood(food),HttpStatus.OK);
    }

    @GetMapping("/displayCategory/{restaurantIdentifier}")
    public ResponseEntity displayCategories( @PathVariable("restaurantIdentifier") Integer restaurantIdentifier) {

        return new ResponseEntity(foodService.displayByCategory(restaurantIdentifier),HttpStatus.OK);
    }

    @GetMapping("/displayFoodByCategory/{restaurantIdentifier}/{foodCategory}")
    public ResponseEntity displayFoodByCategory(@PathVariable("restaurantIdentifier") Integer restaurantIdentifier ,@PathVariable("foodCategory") String foodCategory ) {

        return new ResponseEntity(foodService.displayListByCategory(restaurantIdentifier,foodCategory),HttpStatus.OK);
    }

}
