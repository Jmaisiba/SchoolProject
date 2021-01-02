package com.example.creditwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {


    ImageView imageViewLogout, infoCardView, tipsCardView, scoreCardView, chatCardView;




    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageViewLogout= findViewById(R.id.logout);
        infoCardView=  findViewById(R.id.infoCard);
        tipsCardView = findViewById(R.id.tipsCard);
        scoreCardView = findViewById(R.id.scoreCard);
        chatCardView = findViewById(R.id.chatCard);

        imageViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    firebaseAuth.signOut();
                    checkUserStatus();

            }
        });

        infoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashboardActivity.this, InfoActivity.class));
            }
        });

        tipsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashboardActivity.this, TipsActivity.class));

            }
        });

        scoreCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ScoreActivity.class));

            }
        });
        chatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,ChatActivity.class));
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();


    }



    private void checkUserStatus() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null){
            //user remains signed in

        }
        else {
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();
        }


    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

}