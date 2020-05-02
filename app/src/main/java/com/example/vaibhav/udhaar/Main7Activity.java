package com.example.vaibhav.udhaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseLenders;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main7Activity extends AppCompatActivity {

    //private TextView request;
    private ListView listView;
    private DatabaseReference m3;
    private DatabaseReference m4;
    private Button paidback;
    //    String EMAIL[]={"test1@","test2@","test3@","test4@"};
//    String AMOUNT[]={"3","2","4","1"};
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Lenders Lenders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        //request=(TextView)findViewById(R.id.textView6);
        Lenders=new Lenders();
        listView=(ListView)findViewById(R.id.ListView2);
        m3= FirebaseDatabase.getInstance().getReference().child("Lenders");
        m4= FirebaseDatabase.getInstance().getReference().child("Pending");
        paidback=(Button)findViewById(R.id.paidback);
        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.listviewlayout,R.id.textView6,list);
        m3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String mail = user.getEmail();
                String TruecurrentUser = mail.replace(".", ",");
                if (dataSnapshot.child(TruecurrentUser).exists()) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String email = dataSnapshot.getKey();
                    email = mail.replace(',', '.');
                    Lenders = ds.getValue(Lenders.class);
                    String lend=ds.getKey().toString();
//                    Toast.makeText(Main7Activity.this,lend,Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Main7Activity.this,TruecurrentUser,Toast.LENGTH_SHORT).show();
                    if(lend.equals(TruecurrentUser)) {
                        String pendermail=Lenders.getPender().toString();
                        list.add("Email: " + Lenders.getPender().toString());
//                        String phone1 = Lenders.getPender().toString();
                    }
                }
                }
                    else {
                        Toast.makeText(Main7Activity.this,"You have no pending Investments",Toast.LENGTH_SHORT).show();
                    }

                    listView.setAdapter(adapter);
//                String name=dataSnapshot.getValue().toString();
//                Toast.makeText(Main4Activity.this,name,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main7Activity.this,"OOPS",Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();
                    email = email.replace(".", ",");
                    String x = listView.getItemAtPosition(i).toString();
                    String[] arr = x.split(" ");
                    String reqmail=arr[1];
                    reqmail.replace(".",",");
                    m4.child(reqmail).removeValue();
                    m3.child(email).child("Pender").removeValue();
                    Toast.makeText(Main7Activity.this,"Investment Removed",Toast.LENGTH_LONG).show();
                }
//                intent.putExtra("LendersInfo",listView.getItemAtPosition(i).toString());
//                String x = listView.getItemAtPosition(i).toString();
//                String[] arr = x.split(" ");
//                String phone=arr[3];
//                String reqmail=arr[1];
//                intent.putExtra("reqmail",reqmail);
//                intent.putExtra("Phone",phone);
//                Toast.makeText(Main4Activity.this,arr[2],Toast.LENGTH_LONG).show();
            }
        });
        paidback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                try{
                if (user != null) {
                    String email = user.getEmail();
                    email = email.replace(".", ",");
                    String x = listView.getItemAtPosition(0).toString();
                    String[] arr = x.split(" ");
                    String reqmail = arr[1];
                    reqmail.replace(".", ",");
                    m4.child(reqmail).removeValue();
                    m3.child(email).child("Pender").removeValue();
                    Toast.makeText(Main7Activity.this, "Investment Removed", Toast.LENGTH_LONG).show();
                }}catch(Exception e){
                    Toast.makeText(Main7Activity.this,"No investments",Toast.LENGTH_SHORT).show();
                }
            }
        });


//        CustomAdapter customAdapter=new CustomAdapter();
//        listView.setAdapter(customAdapter);
    }

//    class CustomAdapter extends BaseAdapter{
//
//        @Override
//        public int getCount() {
//            return EMAIL.length;
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            view = getLayoutInflater().inflate(R.layout.listviewlayout,null);
//            TextView email = (TextView)view.findViewById(R.id.textView6);
//            TextView amount = (TextView)view.findViewById(R.id.textView7);
//
//            email.setText(EMAIL[i]);
//            amount.setText(AMOUNT[i]);
//            return view;
//        }
//    }
}
