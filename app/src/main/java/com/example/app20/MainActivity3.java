package com.example.app20;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity  implements RecyclerViewInterface{
    private TextView txt;
    private EditText ed;
    private RecyclerView list;
    private Button btn4;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference zametkiRef = db.collection("zametki");
    private FirebaseAuth auth= FirebaseAuth.getInstance();
    ArrayList<Zametki> zam;

    MainActivity main;
    String uid= main.uid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt = findViewById(R.id.uid);
        btn4 = findViewById(R.id.btn4);
        list = findViewById(R.id.list);
        ed = findViewById(R.id.zamet);
        txt.setText(main.uid);

        Search();
        RecycletAdapter adapter = new RecycletAdapter(MainActivity3.this,MainActivity3.this,zam);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        list.setHasFixedSize(true);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListTwo data = new ListTwo(uid,ed.getText().toString());
                zametkiRef
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity3.this, "Nice", Toast.LENGTH_SHORT).show();
                                Search();
                                RecycletAdapter adapter = new RecycletAdapter(MainActivity3.this,MainActivity3.this,zam);
                                list.setAdapter(adapter);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity3.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }
    void Search(){
        zam = new ArrayList<>();
        zametkiRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getString("users_Key").equals(uid)) {
                                    Zametki newZam = new Zametki(
                                            document.getId(),
                                            document.getString("users_Key"),
                                            document.getString("zametki_Text"));

                                    zam.add(newZam);
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(MainActivity3.this, MainActivity4.class);
        i.putExtra("id", zam.get(position).getID());
        i.putExtra("key", zam.get(position).getUsers_Key());
        i.putExtra("txt", zam.get(position).getZametki_Text());
        startActivity(i);

    }
}