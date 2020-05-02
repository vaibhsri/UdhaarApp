package com.example.vaibhav.udhaar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main5Activity extends AppCompatActivity {
    private TextView reqinfo;
    private Button accept;
    private DatabaseReference m4;
    private EditText accnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        reqinfo=(TextView)findViewById(R.id.textView7);
        accept=(Button)findViewById(R.id.button7);
        m4= FirebaseDatabase.getInstance().getReference();
        accnum=(EditText)findViewById(R.id.editText2);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            reqinfo.setText(bundle.getString("RequestInfo"));
        }
        String x=bundle.getString("RequestInfo");

//        char a[]=x.toCharArray();
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accnum1=accnum.getText().toString();
                Bundle bundle=getIntent().getExtras();
                String phone = bundle.getString("Phone");
                String reqmail=bundle.getString("reqmail");
                reqmail=reqmail.replace(".",",");
                m4.child("Pending").child(reqmail).setValue(accnum1);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String mail = user.getEmail();
                String TruecurrentUser = mail.replace(".", ",");
                m4.child("Lenders").child(TruecurrentUser).child("Pender").setValue(reqmail);
                m4.child("Accepted").child(reqmail).setValue(accnum1);
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("number",phone);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(Main5Activity.this,"Phone number copied to ClipBoard",Toast.LENGTH_LONG).show();
                try{
                Intent i = new Intent(Intent.ACTION_MAIN);
                PackageManager managerclock = getPackageManager();
                i = managerclock.getLaunchIntentForPackage("net.one97.paytm");
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(i);
                }catch(Exception e){
                    reqinfo.setText(bundle.getString("RequestInfo")+" \nPhone number has been copied to clipboard :)");
                    Toast.makeText(Main5Activity.this,"Please install the Paytm App",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
