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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private FirebaseAuth mAuth;
    private DatabaseReference m3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name=(EditText)findViewById(R.id.email);
        Password=(EditText)findViewById(R.id.password);
        Login=(Button)findViewById(R.id.email_sign_in_button);
        mAuth = FirebaseAuth.getInstance();
        m3 = FirebaseDatabase.getInstance().getReference().child("Accepted");


        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email= Name.getText().toString();
                String password =Password.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            m3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String mail = user.getEmail();
                                    String TruecurrentUser = mail.replace(".", ",");
                                    if(dataSnapshot.child(TruecurrentUser).exists()) {
                                        String acceptby = dataSnapshot.child(TruecurrentUser).getValue().toString();
                                        Intent intent = new Intent(LoginActivity.this, Main6Activity.class);
                                        intent.putExtra("byPhone",acceptby);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });


                        }else{
                            Toast.makeText(getApplicationContext(),"Please enter valid Username and Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //sendtoMain(Name.getText().toString(),Password.getText().toString());
            }
        });
    }


    public void sendtoMain(String Username, String Password)
    {
        if(Username.equals("GloriaBorger")&&Password.equals("pewpew")){
        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
        startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter valid Username and Password",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}