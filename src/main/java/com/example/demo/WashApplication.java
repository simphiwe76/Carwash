package com.example.demo;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class WashApplication {
	public static void main(String[] args) throws IOException, FirebaseAuthException {

		ClassLoader classLoader = WashApplication.class.getClassLoader();

		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://online-car-wash-booking-default-rtdb.firebaseio.com")
				.build();
		FirebaseApp.initializeApp(options);
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();

		SpringApplication.run(WashApplication.class, args);
	}
}
