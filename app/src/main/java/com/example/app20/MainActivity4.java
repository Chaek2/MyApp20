package com.example.app20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private EditText ed;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference zametkiRef = db.collection("zametki");
    ArrayList<Zametki> zam;
    private Button btn2,btn3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle arguments = getIntent().getExtras();
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        ed = findViewById(R.id.edtext);
        ed.setText(arguments.get("txt").toString());

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListTwo data = new ListTwo(
                        arguments.get("key").toString(),
                        ed.getText().toString()
                );
                zametkiRef
                        .document(arguments.get("id").toString())
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent i = new Intent(MainActivity4.this,MainActivity3.class);
                                startActivity(i);
                                finish();
                            }
                        });
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zametkiRef
                        .document(arguments.get("id").toString())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent i = new Intent(MainActivity4.this,MainActivity3.class);
                                startActivity(i);
                                finish();
                            }
                        });
            }
        });

    }
}