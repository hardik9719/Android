package com.example.hardik.newfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_second, container, false);
        String s= this.getArguments().getString("text").toString();
        TextView textView=view.findViewById(R.id.myTxt);
        textView.setText(s);
        return view;
    }

}
