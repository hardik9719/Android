package com.example.hardik.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Communicate {
ListView l;
    String [] days={"mon","tue","wed","thur","fri"};
    String [] num={"10","20","30","40","50"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_);
        l= (ListView) findViewById(R.id.listview);
    /*    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView,days);
        l.setAdapter(arrayAdapter) ;
        Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_SHORT).show();
       l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView= (TextView) view;

                Toast.makeText(getApplicationContext(),textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });*/
  //   Myadapter myadapter=new Myadapter(getApplicationContext(),days,num);

     //   l.setAdapter(new base(getApplicationContext()));

    }

    @Override
    public void display(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    class  singleRow{
        String day,number;
        singleRow(String Day,String Num){
            this.day=Day;
            this.number=Num;
        }
    }
    class base extends BaseAdapter{
        Context context;
        ArrayList<singleRow> list;
        String [] days={"mon","tue","wed","thur","fri"};
        String [] num={"10","20","30","40","50"};
        base(Context c){
            this.context=c;
            list=new ArrayList<singleRow>();
            for(int i=0;i<5;i++){
               singleRow temp= new singleRow(days[i],num[i]);
                list.add(temp);
            }
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row= layoutInflater.inflate(R.layout.single_row,viewGroup,false);

         TextView day=row.findViewById(R.id.textView);
         TextView num=row.findViewById(R.id.textView2);
            singleRow temp=list.get(i);
            day.setText(temp.day);
            num.setText(temp.number);

            return row;
        }
    }
    class Myadapter extends ArrayAdapter<String>{
        Context context;
        String[] day;
        String[] number;
        Myadapter(Context c, String[] days,String[] num){
            super(c,R.layout.single_row,R.id.textView,days);
            this.context=c;
            day=days;
            number=num;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View row=layoutInflater.inflate(R.layout.single_row,parent,false);
            TextView Day=row.findViewById(R.id.textView);
            TextView no=row.findViewById(R.id.textView2);
            Day.setText(day[position]);
            no.setText(number[position]);




            return  row;
        }
    }



}
