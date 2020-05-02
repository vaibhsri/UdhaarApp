package com.example.vaibhav.udhaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //private Button button6;

    private DatabaseReference mDatabase;
    private ImageView logo;
    private TextView forgot;
    private Button signup;
    private Button login;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button6=(Button)findViewById(R.id.button6);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        logo=(ImageView)findViewById(R.id.imageView3);
        forgot=(TextView)findViewById(R.id.textView2);
        signup=(Button)findViewById(R.id.button);
        login=(Button)findViewById(R.id.button2);
        welcome=(TextView)findViewById(R.id.textView);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Should've remembered it",Toast.LENGTH_SHORT).show();
            }
        });
        Animation animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
        welcome.startAnimation(animation);
        logo.startAnimation(animation);
        signup.startAnimation(animation);
        login.startAnimation(animation);
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void sendtoRegister(View view)
    {
        Intent intent = new Intent(MainActivity.this, LoginActivity2.class);
        startActivity(intent);
    }
}
