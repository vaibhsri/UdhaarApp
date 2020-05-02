package com.example.vaibhav.udhaar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class LoginActivity2 extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Register;
    private DatabaseReference m2;
    private EditText username;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Name=(EditText)findViewById(R.id.email);
        username=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Register=(Button)findViewById(R.id.email_sign_in_button);
        mAuth = FirebaseAuth.getInstance();
        m2= FirebaseDatabase.getInstance().getReference();


        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email=Name.getText().toString().trim();
                String password=Password.getText().toString();
                String Username=username.getText().toString();
//                sendtoMain(email,password,Username);
//                register_user(email,password);
                HashMap<String,String> dataMap=new HashMap<String,String>();
//                dataMap.put("Name",Username);
                dataMap.put("Email",email);
                dataMap.put("Password",password);
                m2.push().setValue(dataMap);
                m2.child("User").child(Username).setValue(Username);//uncom
//                m2.child("User").child(Username).child(Username).setValue(Username);
//                m2.child("User").child("Email").setValue(email);
//                m2.child("User").child("Password").setValue(password);
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Create intent to shift to next pge after sign up succc
                            Intent intent = new Intent(LoginActivity2.this, Main2Activity.class);
//                            intent.putExtra("Username", username.getText().toString());
                            startActivity(intent);
                            Toast.makeText(LoginActivity2.this,"DONE", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginActivity2.this,"Error!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }

//    private void register_user(String email, String password) {
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    // Create intent to shift to next pge after sign up succc
//                    Intent intent = new Intent(LoginActivity2.this, Main2Activity.class);
//                    startActivity(intent);
//                    Toast.makeText(LoginActivity2.this,"DONE", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(LoginActivity2.this,"Error!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }


    public void sendtoMain(String email, String password,String Username)
    {
        if(email.contains("@")&& password.length()>=4) {
            m2.child("User").child("Name").setValue(username);
            m2.child("User").child("Email").setValue(email);
            m2.child("User").child("Password").setValue(password);
//            HashMap<String,String> dataMap=new HashMap<String,String>();
//            dataMap.put("Name",username);
//            dataMap.put("Email",email);
//            dataMap.put("Password",password);
//            m2.push().setValue(dataMap);
            Intent intent = new Intent(LoginActivity2.this, Main2Activity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter valid E-mail and Password > 4 characters", Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
            //startActivity(intent);
        }
    }
}