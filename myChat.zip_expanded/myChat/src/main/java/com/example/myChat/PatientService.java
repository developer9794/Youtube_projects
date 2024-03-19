package com.example.myChat;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class PatientService {

    public static final String COL_NAME="products";

    public String savePatientDetails(Patient patient) throws InterruptedException, ExecutionException {
     
    	Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(patient.getName()).set(patient);
        System.out.println("PatientService>"+collectionsApiFuture.get().getUpdateTime().toString());
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Patient getPatientDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
       
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        
        DocumentSnapshot document = future.get();
        

        Patient patient = null;

        if(document.exists()) {
            patient = document.toObject(Patient.class);
            return patient;
        }else {
            return null;
        }
    }
    public List<Patient > getAllPatientDetails() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        
      Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
       
      Iterator<DocumentReference> itrable = documentReference.iterator();
     // ApiFuture<DocumentSnapshot> future = documentReference.get();
      List<Patient > prolist=new ArrayList<Patient>();
      Patient patient = null;
        while(itrable.hasNext()){
        	DocumentReference documentReference1 = itrable.next();
        	ApiFuture<DocumentSnapshot> future = documentReference1.get();
        	DocumentSnapshot document = future.get();
        	patient=document.toObject(Patient.class);
        	prolist.add(patient);
        }
        

      

        return prolist;
        }

    public String updatePatientDetails(Patient person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deletePatient(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Patient ID "+name+" has been deleted";
    }
    
    
//    --------------------------------------------------------//
    
    

}