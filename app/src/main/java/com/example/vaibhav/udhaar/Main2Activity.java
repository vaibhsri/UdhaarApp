package com.example.vaibhav.udhaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class Main2Activity extends AppCompatActivity {
    private Button btn6;
    private DatabaseReference m3;
    private TextView requests;
    private Button request;
    private Button invest;
    private DatabaseReference m4;

//    private TextView textView6;
//    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        username=(EditText)findViewById(R.id.username);
//        String Username=username.getText().toString();
//        Intent intent = getIntent();
//        String User= intent.getExtras().getString("Username");
        requests = (TextView) findViewById(R.id.textView5);
        m3 = FirebaseDatabase.getInstance().getReference().child("Request");
        m4 = FirebaseDatabase.getInstance().getReference().child("Pending");
        btn6 = (Button) findViewById(R.id.button6);
        request=(Button)findViewById(R.id.button3);
        invest=(Button)findViewById(R.id.button4);
        Animation animation= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.lefttoright);
        Animation animation2=AnimationUtils.loadAnimation(Main2Activity.this,R.anim.lefttoright);
        request.setAnimation(animation);
        invest.setAnimation(animation2);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();
                    email = email.replace(".", ",");
                    m3.child(email).removeValue();
                }
            }
        });
        m4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String mail = user.getEmail();
                final String TruecurrentUser = mail.replace(".", ",");
                if(dataSnapshot.child(TruecurrentUser).exists()) {
                    request.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String lenduser=dataSnapshot.child(TruecurrentUser).getValue().toString();
                            requests.setText("Please pay  "+lenduser+" back");
                            Toast.makeText(Main2Activity.this,"You have a pending payment",Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    request.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            m3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String mail = user.getEmail();
                                    String TruecurrentUser = mail.replace(".", ",");
                                    if(dataSnapshot.child(TruecurrentUser).exists()) {
                                        String currentUser = dataSnapshot.child(TruecurrentUser).getValue().toString();
                                        String[] finalname = TruecurrentUser.split("@");
                                        String finaluser = finalname[0];
                                        requests.setText("Hello, " + finaluser + ", You have a Pending Request!");
                                    }else{
                                        String[] finalname = mail.split("@");
                                        String currentUser = finalname[0];
                                        requests.setText("Hello, " + currentUser + ", You have no Pending Requests");
                                    }
                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(Main2Activity.this,"No Pending Requests",Toast.LENGTH_LONG).show();
                                }
                            });
                            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                            startActivity(intent);
                        }
                    });
//                    Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
//                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
//                startActivity(intent);
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String mail = user.getEmail();
//                String currentUser = mail.replace(",", ".");
        String[] finalname = mail.split("@");
        String currentUser = finalname[0];
        requests.setText("Hello, " + currentUser + ", You have no Pending Requests");
        m3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String mail = user.getEmail();
                String TruecurrentUser = mail.replace(".", ",");
                if(dataSnapshot.child(TruecurrentUser).exists()) {
                    String currentUser = dataSnapshot.child(TruecurrentUser).getValue().toString();
                    String[] finalname = TruecurrentUser.split("@");
                    String finaluser = finalname[0];
                    requests.setText("Hello, " + finaluser + ", You have a Pending Request!");
                }else{
                    String[] finalname = mail.split("@");
                    String currentUser = finalname[0];
                    requests.setText("Hello, " + currentUser + ", You have no Pending Requests");
                }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this,"No Pending Requests",Toast.LENGTH_LONG).show();
            }
        });

//        if(user!=null) {
//            String email = user.getEmail();
//            email=email.replace(".",",");
//            try{
//
//                String x=m3.child("Request").child(email).get;
//                Toast.makeText(Main2Activity.this,x,Toast.LENGTH_LONG).show();
//                String currentUser=email.replace(",",".");
//                String[] finalname = currentUser.split("@");
//                currentUser = finalname[0];
//                requests.setText("Hello, "+currentUser+" You have a Pending Request");
//            }catch(Exception e){
//                String currentUser=email.replace(",",".");
//                String[] finalname = currentUser.split("@");
//                currentUser = finalname[0];
//                requests.setText("Hello, "+currentUser+" You have no Pending Requests");
    }
//        textView6=(TextView)findViewById(R.id.textView6);
//
//
//        m3.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String name=dataSnapshot.getValue().toString();
//                textView6.setText("Hello, "+name);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                textView6.setText("oops");
//            }
//        });
    public void sendtoInvestements(View view){
        Intent intent = new Intent(Main2Activity.this, Main7Activity.class);
        startActivity(intent);
        finish();
    }





    public void sendtoInvest(View view){
        Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
        startActivity(intent);
    }
}
