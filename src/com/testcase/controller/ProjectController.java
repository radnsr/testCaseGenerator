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

import com.testcase.model.Project;
import com.testcase.service.ProjectService;
 
@RestController
public class ProjectController {
 
    @Autowired
    ProjectService projectService;  //Service which will do all data retrieval/manipulation work  
    
 
    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/project/", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> listAllUsers() {
        List<Project> projects = projectService.getAllData();
        if(projects.isEmpty()){
            return new ResponseEntity<List<Project>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
    }   
    
    //-------------------Retrieve Single User--------------------------------------------------------
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Project project = projectService.findById(id);
        if (project == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }     
    //-------------------Create a User--------------------------------------------------------
    @RequestMapping(value = "/project/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Project project,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + project.getProjectName());
        projectService.save(project);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }     
    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/project/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateUser(@PathVariable("id") int id, @RequestBody Project project) {
        System.out.println("Updating Project " + id);
         
        Project current = projectService.findById(id);        
        if (current==null) {
            System.out.println("Project with id " + id + " not found");
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }       
        current.setProjectName(project.getProjectName());      
        projectService.update(current);
        return new ResponseEntity<Project>(current, HttpStatus.OK);
    } 
}