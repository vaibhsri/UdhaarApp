package com.example.vaibhav.udhaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    private EditText amount;
    private EditText interest;
    private EditText number;
    private Button confirm;
    private DatabaseReference m4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        amount=(EditText)findViewById(R.id.editText4);
        interest=(EditText)findViewById(R.id.editText5);
        number=(EditText)findViewById(R.id.editText3);
        confirm=(Button)findViewById(R.id.button5);
        m4= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//        if(user!=null){
//            String email=user.getEmail();
//        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Amount=amount.getText().toString();
                String Interest=interest.getText().toString();
                String Number=number.getText().toString();
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                int Amount1=Integer.parseInt(Amount);
                int Interest1=Integer.parseInt(Interest);

                if(Amount1>=Interest1){
                    Toast.makeText(Main3Activity.this,"Pay amount should be greater than Request amount",Toast.LENGTH_LONG).show();
                }else{
                    if(Number.length()<10){
                        Toast.makeText(Main3Activity.this,"Please enter a valid phone number",Toast.LENGTH_LONG).show();
                    }else {
                        if (Amount1 > 5000 || Interest1 > 6000) {
                            Toast.makeText(Main3Activity.this, "Please keep requests below 5000 and Pay amount below 6000", Toast.LENGTH_LONG).show();
                        } else {
                            if (user != null) {
                                String email = user.getEmail();
                                email = email.replace(".", ",");
                                Toast.makeText(Main3Activity.this, "Your request has been submitted", Toast.LENGTH_LONG).show();
                                m4.child("Request").child(email).child("Amount").setValue(Amount);
                                m4.child("Request").child(email).child("Interest").setValue(Interest);
                                m4.child("Request").child(email).child("Phone").setValue(Number);
                            }
                        }
                    }
                    }
                }
        });
    }
}
