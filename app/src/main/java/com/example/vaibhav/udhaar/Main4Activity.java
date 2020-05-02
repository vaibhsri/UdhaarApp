package com.example.vaibhav.udhaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    //private TextView request;
    private ListView listView;
    private DatabaseReference m3;
//    String EMAIL[]={"test1@","test2@","test3@","test4@"};
//    String AMOUNT[]={"3","2","4","1"};
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Request Request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //request=(TextView)findViewById(R.id.textView6);
        Request=new Request();
        listView=(ListView)findViewById(R.id.ListView);
        m3= FirebaseDatabase.getInstance().getReference().child("Request");
        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.listviewlayout,R.id.textView6,list);
        m3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String mail=ds.getKey();
                    mail = mail.replace(',','.');
                    Request=ds.getValue(Request.class);
                    list.add("Email: "+mail+" \n"+"Phone: "+Request.getPhone().toString()+" "+"\nAmount: "+Request.getAmount().toString()+"\nReturn :"+Request.getInterest().toString());
                    String phone1=Request.getPhone().toString();
                }
                listView.setAdapter(adapter);
//                String name=dataSnapshot.getValue().toString();
//                Toast.makeText(Main4Activity.this,name,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main4Activity.this,"OOPS",Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Main4Activity.this,Main5Activity.class);
                intent.putExtra("RequestInfo",listView.getItemAtPosition(i).toString());
                String x = listView.getItemAtPosition(i).toString();
                String[] arr = x.split(" ");
                String phone=arr[3];
                String reqmail=arr[1];
                intent.putExtra("reqmail",reqmail);
                intent.putExtra("Phone",phone);
//                Toast.makeText(Main4Activity.this,arr[2],Toast.LENGTH_LONG).show();

                startActivity(intent);
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
