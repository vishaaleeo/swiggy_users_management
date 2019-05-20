package swiggy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import swiggy.domain.User;
import swiggy.services.UserDAOImplementaion;


/*
 *To control and regulate the paths
 */
@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserDAOImplementaion userDAOImplementaion;



    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody User user) {

        String result=userDAOImplementaion.createUser(user);
        if(result.equals("success")) {

            return new ResponseEntity("User created successfully", HttpStatus.CREATED);
        }

        else {
            return new ResponseEntity(result,HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        User loginUser = userDAOImplementaion.loginUser(user);
        if(loginUser!=null) {
            return new ResponseEntity<String>(loginUser.toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("invalid user login",HttpStatus.OK);
        }
    }

    @PostMapping ("/update")
    public ResponseEntity updateUser(@RequestBody User user) {

    String result = userDAOImplementaion.updateUser(user);
    return new ResponseEntity(result,HttpStatus.OK);
    }




    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody User user) {

       String result = userDAOImplementaion.deleteUser(user);

       return new ResponseEntity(result,HttpStatus.OK);
    }



}
