package com.example.demo.service;

import com.example.demo.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private Firestore db = FirestoreClient.getFirestore();


    public Company getCompany(CompanyBooking companyBooking) throws ExecutionException, InterruptedException {
        Company company = new Company();

        DocumentReference docRef = db.collection("Company").document(companyBooking.getCompanyID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            company = document.toObject(Company.class);

        } else {
            System.out.println("No such document!");
        }

        return company;
    }

    public List<Slot> getAllSlot(String slot) throws ExecutionException, InterruptedException {
        List<Slot> slots = new ArrayList<>();

        Query user = db.collection("Slot").whereEqualTo("SlotID",slot);
        ApiFuture<QuerySnapshot> querySnapshot = user.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            slots.add(document.toObject(Slot.class));
        }



        return slots.stream().sorted(Comparator.comparing(Slot::getTime)).collect(Collectors.toList());
    }

    public List<CostPrice> getPrices(String quotationID) throws ExecutionException, InterruptedException {
        List<CostPrice> costPrices = new ArrayList<>();

        Query user = db.collection("CostPrice").whereEqualTo("QuotationID",quotationID);
        ApiFuture<QuerySnapshot> querySnapshot = user.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            costPrices.add(document.toObject(CostPrice.class));
        }
        return costPrices;

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
        return cars;

    }


    public boolean createBook(Booking booking) throws ExecutionException, InterruptedException {


        boolean found = false;
        String companyID = booking.getCompanyID();
        String time = booking.getTime();
        String date = booking.getDate();

        Booking booking1 = new Booking();

        ApiFuture<QuerySnapshot> future = db.collection("Booking").get();// Retrieve
        // All Booking from Booking table
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            booking1 = document.toObject(Booking.class);
            if (date.equalsIgnoreCase(booking1.getDate()) && companyID.equalsIgnoreCase(booking1.getCompanyID())
                    &&time.equalsIgnoreCase(booking1.getTime())){
                found = true;
            }

        }

        if (found == false)
        {
            DocumentReference addedDocRef = db.collection("Booking").document();
            booking.setBookingID(addedDocRef.getId());
            booking.setBookingStatus("Active");
            ApiFuture<WriteResult> writeResult = addedDocRef.set(booking);
        }




        return  found;
    }

    public List<String> getAvailableTime(Booking booking) throws ExecutionException, InterruptedException {
        Booking booking1 = new Booking();
        Slot slot = new Slot();
        String slotID = booking.getSlotID();
        String companyID = booking.getCompanyID();
        String time = booking.getTime();
        String date = booking.getDate();
        List<String> times = new ArrayList<>();
        List<String> availableTime = new ArrayList<>();
        List<String> takenTime = new ArrayList<>();


        ApiFuture<QuerySnapshot> future = db.collection("Booking").get();// Retrieve
        // All Booking from Booking table
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            booking1 = document.toObject(Booking.class);
            if (date.equalsIgnoreCase(booking1.getDate()) &&
                    companyID.equalsIgnoreCase(booking1.getCompanyID()))
            {
                        takenTime.add(booking1.getTime());
            }
        }


        Query user = db.collection("Slot").whereEqualTo("SlotID",slotID);
        ApiFuture<QuerySnapshot> querySnapshot = user.get();
        List<QueryDocumentSnapshot> doc = querySnapshot.get().getDocuments();
        for (DocumentSnapshot document : doc) {
            slot = document.toObject(Slot.class);
            times.add(slot.getTime());
        }



        for (String t:times) {

            if (!takenTime.contains(t)){
                availableTime.add(t);
            }

        }

        Collections.sort(availableTime);
        return  availableTime;
    }

    public List<Booking> getAllMyBooking(String userId) throws ExecutionException, InterruptedException {

        List<Booking> bookingList = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = db.collection("Booking").get();// Retrieve
        // All Companies from company table
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {

            if (document.toObject(Booking.class).getCustomerID().equalsIgnoreCase(userId)){
                bookingList.add(document.toObject(Booking.class));
            }

        }

        return bookingList;
    }

    public Company getCompanyDetails(String companyID) throws ExecutionException, InterruptedException {

        Company company = new Company();

        DocumentReference docRef = db.collection("Company").document(companyID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            company = document.toObject(Company.class);

        } else {
            System.out.println("No such document!");
        }

        return company;

    }

    public Booking getBooking(String bookingID) throws ExecutionException, InterruptedException {
        Booking booking = new Booking();


        DocumentReference docRef = db.collection("Booking").document(bookingID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            booking = document.toObject(Booking.class);

        } else {
            System.out.println("No such document!");
        }


        return  booking;
    }
}
