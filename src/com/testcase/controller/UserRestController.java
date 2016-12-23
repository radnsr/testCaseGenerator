package com.testcase.controller;
 
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.testcase.model.User;
import com.testcase.service.UserService;
 
@RestController
public class UserRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    
    
 // -------------------Login authentication-------------
 	@RequestMapping(value = "/login/", method = RequestMethod.POST)
 	public ModelAndView login2(@RequestParam(value = "email") String email,
 			@RequestParam(value = "password") String password,
 			HttpSession session) {

 		User au_user = new User();
 		au_user.setEmail(email);
 		au_user.setPassword(password);
 		User user = userService.authenticate(au_user);
 		System.out.println(user);
 		if (user == null) {
 			session.setAttribute("error", "Email or Password is wrong!!!");
 			return new ModelAndView("index");
 		}else if (user.getRole() == "") {
 			session.setAttribute("error", "This profile is deactivated by administrator");
 			return new ModelAndView("index");
 		}
 		session.setAttribute("user", user);
 		return new ModelAndView("dashboard");
 	}

 	// -------------------Log out-------------
 	@RequestMapping(value = "/logout/", method = RequestMethod.GET)
 	public ModelAndView logout(HttpSession session) {

 		session.removeAttribute("user");
 		session.invalidate();

 		return new ModelAndView("login");
 	}
    
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.getAllData();

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
        System.out.println("Creating User " + user.getEmail());
 user.setRole("User");
 
        userService.save(user);
     //   HttpHeaders headers = new HttpHeaders();
       // headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>( HttpStatus.CREATED);
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
 
        currentUser.setFirst_name(user.getFirst_name());
        currentUser.setLast_name(user.getLast_name());
        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());
         
        userService.update(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    
    
  
 
     
 
 
}