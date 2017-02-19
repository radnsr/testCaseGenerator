package com.testcase.controller;
 
import java.util.ArrayList;
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
        project.setStatus(1);
        projectService.save(project);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }     
    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/project_update/", method = RequestMethod.POST)
    public ResponseEntity<Project> updateUser(@RequestBody Project project) {
        System.out.println("Updating Project " + project.getId());
         
        Project current = projectService.findById(project.getId());        
        if (current==null) {
            System.out.println("Project with id " + project.getId() + " not found");
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        } 
        
        //System.out.println("TESTCASE:"+ projectService.GenerateTestCase(project.getFunc_require()));
        current.setNon_func_require(project.getNon_func_require());
        current.setFunc_require(project.getFunc_require());
        projectService.update(current);
        return new ResponseEntity<Project>(current, HttpStatus.OK);
    } 
    @RequestMapping(value = "/project_testcase/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> GenerateTestCase(@PathVariable("id") int id) {
        System.out.println("Generating Testcase " + id);
         
        Project current= new Project();
        current= projectService.findById(id);        
        if (current==null) {
            System.out.println("Project with id " + id + " not found");
            return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
        } 
        List<String> testList = new ArrayList<String>();
        		testList=	projectService.GenerateTestCase(current.getFunc_require());
        		
        		String description="";
        		String prerequisite="";
        		String altenative="";
        		String expected_result="";
        		String input=current.getFunc_require();
        		String paragraph[]=input.split("\\.");
        		for(String sent : paragraph){
        			if(sent.contains("target")){
        				sent=sent.replace("target", "BBB target");
        				String [] split=sent.split("BBB");
        				description=split[1];
        				testList.add(0, description);
        			}
        			if(sent.contains("prerequisite")){
        				sent=sent.replace("prerequisite", "BBB prerequisite");
        				String [] split=sent.split("BBB");
        				prerequisite=split[1];
        				testList.add(1, prerequisite);
        			}
        			if(sent.contains("alternative")){
        				sent=sent.replace("alternative", "BBB alternative");
        				String [] split=sent.split("BBB");
        				altenative=split[1];
        				testList.add(2, altenative);
        			}
        			if(sent.contains("outcome")){
        				sent=sent.replace("outcome", "BBB outcome");
        				String [] split=sent.split("BBB");
        				expected_result=split[1];
        				testList.add(3, expected_result);
        			}
        		}
        System.out.println("TESTCASE:"+ testList);
        
        return new ResponseEntity<List<String>>(testList, HttpStatus.OK);
    }
}