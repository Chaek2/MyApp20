package com.example.app20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private EditText ed1,ed2;
    private Button btn1,btn2;
    public static String uid;
    private FirebaseAuth auth= FirebaseAuth.getInstance();;
    private FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "activity_main", Toast.LENGTH_SHORT).show();
        db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("users");
        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean VV = Valid(ed1.getText().toString(),ed2.getText().toString());
                if(VV) {
                    auth.signInWithEmailAndPassword(ed1.getText().toString(), ed2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = task.getResult().getUser();
                                uid = user.getUid();
                                Intent i = new Intent(MainActivity.this, MainActivity3.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "User logged in unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
    private  boolean Valid(String email,String pass){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(MainActivity.this, "The mail is incorrect", Toast.LENGTH_LONG).show();
            return false;
        }else if (TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity.this, "Password is empty", Toast.LENGTH_LONG).show();
            return false;
        }else if(pass.length()<4) {
            Toast.makeText(MainActivity.this, "Password less than 4 characters ", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
}