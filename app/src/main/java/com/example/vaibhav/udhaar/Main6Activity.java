package com.example.vaibhav.udhaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main6Activity extends AppCompatActivity {
    private Button confirmbutton;
    private TextView reqacc;
    private DatabaseReference m3;
    private DatabaseReference m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        confirmbutton=(Button)findViewById(R.id.button8);
        reqacc=(TextView)findViewById(R.id.textView8);
        m3 = FirebaseDatabase.getInstance().getReference().child("Accepted");
        m2 = FirebaseDatabase.getInstance().getReference().child("Request");
        Bundle bundle=getIntent().getExtras();
        String phone = bundle.getString("byPhone");
        reqacc.setText("Congratulations! \n"+"Your request has been accepted by: \n"+phone);
        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();
                    email = email.replace(".", ",");
                    m3.child(email).removeValue();
                    m2.child(email).removeValue();
                }
                Intent intent = new Intent(Main6Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
