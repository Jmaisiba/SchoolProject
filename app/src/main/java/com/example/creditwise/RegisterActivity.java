package com.example.creditwise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class    RegisterActivity extends AppCompatActivity {

    EditText mFirstNameEt,mLastNameEt,mEmailEt,mPasswordEt;
    TextView mHaveAccountTv;
    Button mLoginBtn;


    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //setting title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        mFirstNameEt= findViewById(R.id.firstNameEt);
        mLastNameEt = findViewById(R.id.lastNameEt);
        mEmailEt = findViewById(R.id.emailEt);
        mPasswordEt = findViewById(R.id.passwordEt);
        mHaveAccountTv = findViewById(R.id.haveAccountTv);
        mLoginBtn=findViewById(R.id.registerButton);

        mAuth = FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = mFirstNameEt.getText().toString().trim();
                String lastName = mLastNameEt.getText().toString().trim();
                String email = mEmailEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mEmailEt.setError("Invalid Email Address");
                    mEmailEt.setFocusable(true);

                }
                else if (password.length()<6){
                    mPasswordEt.setError("Password Length atleast 6 Characters");
                    mPasswordEt.setFocusable(true);

                }
                else {

                    registerUser(email,password);
                }
            }
        });


        mHaveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser( String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                             user = mAuth.getCurrentUser();

                            //get uid and email
                            String email= user.getEmail();
                            String uid= user.getUid();




                            //store user data in rt database too using hashmap
                            HashMap<Object, String> hashMap = new HashMap<>();
                            hashMap.put("first name", "");
                            hashMap.put("last name", "");
                            hashMap.put("email", email);
                            hashMap.put("uid", uid);



                            //firebase database instance
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            //path to store data named Users
                            DatabaseReference reference = firebaseDatabase.getReference("Users");
                            //put data in hashmap within db
                            reference.child(uid).setValue(hashMap);

                            Toast.makeText(RegisterActivity.this, "Registered...\n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();//goes to previous activity
        return super.onSupportNavigateUp();
    }
}