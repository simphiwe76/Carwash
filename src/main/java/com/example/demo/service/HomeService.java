package com.example.demo.service;


import com.example.demo.model.Customer;
import com.example.demo.model.Login;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class HomeService
{

    private Firestore db = FirestoreClient.getFirestore();

    public boolean createCustomer(Customer customer) throws ExecutionException, InterruptedException, FirebaseAuthException {

        Query user = db.collection("Customer").whereEqualTo("email",customer.getEmail());
        ApiFuture<QuerySnapshot> querySnapshot = user.get();
        int numRecord = querySnapshot.get().getDocuments().size();

        boolean exist = false;
        if(numRecord == 0){

            //Create a Customer auth
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(customer.getEmail())
                    .setPassword(customer.getPassword());
            
            //Save Customer on Database
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            customer.setCustomerID(userRecord.getUid()); // Set  Auth key on as Customer ID to
            // Link Tables with unique value
            ApiFuture<WriteResult> future = db.collection("Customer")
                    .document(userRecord.getUid()).set(customer);
        }
        else {
            exist = true;
        }
        return exist;

    }

    public Customer customerLogin(Login login) throws ExecutionException, InterruptedException, FirebaseAuthException {


        String email = login.getEmail();
        String password = login.getPassword();


        Customer customer = new Customer();





        Query user = db.collection("Customer").whereEqualTo("email",email);
        ApiFuture<QuerySnapshot> querySnapshot = user.get();
        int numRecord = querySnapshot.get().getDocuments().size();

        customer = null;

        if (numRecord == 1)
        {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            for (DocumentSnapshot document : documents) {

                if (email.equalsIgnoreCase(document.toObject(Customer.class).getEmail())){
                    customer = document.toObject(Customer.class);
                }
            }
        }
        else {
            customer = null;
        }

        return  customer;
    }

}
