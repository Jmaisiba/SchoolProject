package com.example.creditwise;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AnswerEntry {



    String userID;
    List<AnswerModel> answers;


    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser user;


    public AnswerEntry(Context context) {
    }

    public List<AnswerModel> getAllAnswers() {


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("answers");
        user = mAuth.getCurrentUser();
        userID = user.getUid();
        answers = new LinkedList<AnswerModel>();

        Query userQuery = mReference.orderByChild("uid").equalTo(userID);


        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                AnswerModel answerQue = ds.getValue(AnswerModel.class);
                answers = new LinkedList<AnswerModel>();
                AnswerModel answer = null;

                if (answers != null) {
                    do {
                        answer = new AnswerModel();
                        answer.getId();
                        answer.getUserId();
                        answer.getQ1();
                        answer.getQ2();
                        answer.getQ3();
                        answer.getQ4();
                        answer.getQ5();
                        answer.getQ6();
                        answer.getQ7();
                        answer.getQ8();

                        answers.add(answer);
                    } while (answers == null);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        return answers;
    }

    public AnswerModel getAnswerById(int id) {
        for (AnswerModel a : getAllAnswers()) {
            if (a.getId() == id) {
                return a;
            } else {
                return null;
            }
        }
        return null;
    }

    public AnswerModel getAnswerByUserId(int userId) {
        for (AnswerModel a : getAllAnswers()) {
            if (a.getUserId() == userId) {
                return a;
            } else {
                return null;
            }
        }
        return null;
    }



    public void addAnswer(AnswerModel answer) {
        {
            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance();
           // mReference = mDatabase.getReference("answers");


            user = mAuth.getCurrentUser();

            String email = user.getEmail();
            String uid = user.getUid();


            Query userQuery = mReference.orderByChild("uid").equalTo(uid);
            userQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    HashMap<Object, String> hashMap = new HashMap<>();

                    hashMap.put("uid", "");
                    hashMap.put("userId", "");
                    hashMap.put("q1", "");
                    hashMap.put("q2", "");
                    hashMap.put("q3", "");
                    hashMap.put("q4", "");
                    hashMap.put("q5", "");
                    hashMap.put("q6", "");
                    hashMap.put("q7", "");
                    hashMap.put("q8", "");

                    mReference.child("answers").push().setValue(hashMap);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }

    public void updateAnswer(AnswerModel answer){

    }

    public void deleteAnswer(int id){

    }
}