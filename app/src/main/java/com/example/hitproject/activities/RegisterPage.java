package com.example.hitproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hitproject.objects.Person;
import com.example.hitproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterPage extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(View view) {

        //Getting the info from the user for Registration
        EditText emailText = findViewById(R.id.registerEmailText);
        String email = emailText.getText().toString();

        EditText passText = findViewById(R.id.registerPasswordText);
        String password = passText.getText().toString();

        EditText nameText = findViewById(R.id.registerNameText);
        String name = nameText.getText().toString();

        EditText phoneText = findViewById(R.id.registerPhoneText);
        String phone = phoneText.getText().toString();

        EditText addressText = findViewById(R.id.registerAddressText);
        String address = addressText.getText().toString();

        EditText birthDateText = findViewById(R.id.registerBirthDate);
        String birthDate = birthDateText.getText().toString();

        Intent intent = new Intent(this, Login.class);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in success, update UI with the signed-in user's information
                            // Write a person to the database
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String user_uid = user.getUid();
                            String user_email = email;
                            String user_name = name;
                            String user_phone = phone;
                            String user_address = address;
                            String user_birth_date = birthDate;

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users").child(user_uid);
                            Person person = new Person(user_email,user_name,user_phone,user_address,user_birth_date);
                            myRef.setValue(person);

                            Toast.makeText(RegisterPage.this,R.string.successful_reg , Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterPage.this, R.string.failed_reg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}