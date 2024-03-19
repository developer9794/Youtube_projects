package com.example.myChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;
    
    @GetMapping("/getall")
    public List<Patient> getAllPatientDetails( ) throws InterruptedException, ExecutionException{
      
    	return patientService.getAllPatientDetails();
    	 
    }
    @GetMapping("/getPatientDetails")
    public Patient getPatient(@RequestParam String name ) throws InterruptedException, ExecutionException{
      System.out.println(":+++++++++++++++"+name);
    	return patientService.getPatientDetails(name);
    	 
    }

    @PostMapping("/createPatient")
    public String createPatient(@RequestBody Patient patient ) throws InterruptedException, ExecutionException {
    	  System.out.println("createPatient>"+patient.getName());
    	  
    	  System.err.println(patient);
    	return patientService.savePatientDetails(patient);
    }

//    @PutMapping("/updatePatient")
//    public String updatePatient(@RequestBody Patient patient  ) throws InterruptedException, ExecutionException {
//        return patientService.updatePatientDetails(patient);
//    }
    @PostMapping("/updatePatient")
    public String updatePatient( ) throws InterruptedException, ExecutionException {
    	Patient p=new Patient("dat","dhkhd");
        return patientService.savePatientDetails(p);
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam String name){
        return patientService.deletePatient(name);
    }
}