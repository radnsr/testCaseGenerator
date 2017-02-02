package com.testcase.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.testcase.model.TestSuite;
import com.testcase.service.TestSuiteService;
 
@RestController
public class TestSuiteController {
 
    @Autowired
    TestSuiteService testSuiteService;  //Service which will do all data retrieval/manipulation work  
    
 
    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/testSuite/", method = RequestMethod.GET)
    public ResponseEntity<List<TestSuite>> listAllUsers() {
        List<TestSuite> testSuites = testSuiteService.getAllData();
        System.out.println(testSuites.iterator().next().getProject().getId());
        if(testSuites.isEmpty()){
            return new ResponseEntity<List<TestSuite>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TestSuite>>(testSuites, HttpStatus.OK);
    }   
    
    //-------------------Retrieve Single User--------------------------------------------------------
    @RequestMapping(value = "/testSuite/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestSuite> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        TestSuite testSuite = testSuiteService.findById(id);
        if (testSuite == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<TestSuite>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TestSuite>(testSuite, HttpStatus.OK);
    }     
    //-------------------Create a User--------------------------------------------------------
    @RequestMapping(value = "/testSuite/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody TestSuite testSuite,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Testsuite " + testSuite.getTest_suit_name());
        testSuiteService.save(testSuite);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }     
    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/testSuite/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TestSuite> updateUser(@PathVariable("id") int id, @RequestBody TestSuite testSuite) {
        System.out.println("Updating TestSuite " + id);
         
        TestSuite current = testSuiteService.findById(id);        
        if (current==null) {
            System.out.println("TestSuite with id " + id + " not found");
            return new ResponseEntity<TestSuite>(HttpStatus.NOT_FOUND);
        }       
        current.setTest_suit_name(testSuite.getTest_suit_name());      
        testSuiteService.update(current);
        return new ResponseEntity<TestSuite>(current, HttpStatus.OK);
    } 
}