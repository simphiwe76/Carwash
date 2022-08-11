package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.model.Company;
import com.example.demo.model.Customer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CustomerService {


    private Firestore db = FirestoreClient.getFirestore();

    public List<Company> getAllCompanies() throws ExecutionException, InterruptedException {
        List<Company> companyList = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("Company").get();// Retrieve
        // All Companies from company table
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            companyList.add(document.toObject(Company.class));
        }
        return  companyList;
    }

    public boolean addCar(Car car) throws ExecutionException, InterruptedException {

        boolean exist = false;

        ApiFuture<QuerySnapshot> future = db.collection("Car").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.toObject(Car.class).getRegistration().equals(car.getRegistration()))
            {
                    exist = true;

            }
        }

        if (!exist){

            DocumentReference addedDocRef = db.collection("Car").document();
            car.setCarID(addedDocRef.getId());
            ApiFuture<WriteResult> writeResult = addedDocRef.set(car);

        }

        return  exist;
    }


    public List<Car> getAllCars(String userID) throws ExecutionException, InterruptedException {
        List<Car> cars = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = db.collection("Car").get();// Retrieve
        // All Companies from company table
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {

            if (document.toObject(Car.class).getCustomerID().equalsIgnoreCase(userID)){
                cars.add(document.toObject(Car.class));
            }

        }

        return  cars;
    }

    public Customer getCustomerInfo(String userID) throws ExecutionException, InterruptedException {

        Customer customer = new Customer();
        DocumentReference docRef = db.collection("Customer").document(userID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            customer = document.toObject(Customer.class);
        } else {
            System.out.println("No such document!");
        }
        return  customer;
    }
}
