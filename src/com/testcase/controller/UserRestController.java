package com.testcase.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.testcase.model.User;
import com.testcase.service.UserService;
 
@RestController
public class UserRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
      //  List<User> users = userService.getAllData();
        List<User> users = new ArrayList<User>();
        User user=new User();
        user.setEmail("sdsd");
        user.setId(1);
        user.setUsername("dsds");
        user.setPassword("asda");
        users.add(user);
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());
 
 
        userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());
         
        userService.update(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    
    
  
 
     
 
 
}