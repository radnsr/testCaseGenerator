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

import com.testcase.model.TestCase;
import com.testcase.service.TestCaseService;
 
@RestController
public class TestCaseController {
 
    @Autowired
    TestCaseService testCaseService;  //Service which will do all data retrieval/manipulation work  
    
 
    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/testCase/", method = RequestMethod.GET)
    public ResponseEntity<List<TestCase>> listAllUsers() {
        List<TestCase> testCases = testCaseService.getAllData();
        if(testCases.isEmpty()){
            return new ResponseEntity<List<TestCase>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TestCase>>(testCases, HttpStatus.OK);
    }   
    
    //-------------------Retrieve Single User--------------------------------------------------------
    @RequestMapping(value = "/testCase/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestCase> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        TestCase testCase = testCaseService.findById(id);
        if (testCase == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<TestCase>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TestCase>(testCase, HttpStatus.OK);
    }     
    //-------------------Create a User--------------------------------------------------------
    @RequestMapping(value = "/testCase/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody TestCase testCase,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Testsuite " + testCase.getId());
        testCaseService.save(testCase);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }     
    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/testCase/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TestCase> updateUser(@PathVariable("id") int id, @RequestBody TestCase testCase) {
        System.out.println("Updating TestCase " + id);
         
        TestCase current = testCaseService.findById(id);        
        if (current==null) {
            System.out.println("TestCase with id " + id + " not found");
            return new ResponseEntity<TestCase>(HttpStatus.NOT_FOUND);
        }       
        current.setFunc_require(testCase.getFunc_require());
        current.setNon_func_require(testCase.getNon_func_require());
        current.setProjetc_id(testCase.getProjetc_id());
        current.setTest_suit_id(testCase.getTest_suit_id());
        testCaseService.update(current);
        return new ResponseEntity<TestCase>(current, HttpStatus.OK);
    } 
}