package com.example.hardik.newfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    Communicator communicator;
    String res;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View view=inflater.inflate(R.layout.fragment_first, container, false);
    communicator= (Communicator) getActivity();
        Button btn=view.findViewById(R.id.btnClick);
        EditText editText=view.findViewById(R.id.etMsg);
        res=editText.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Response:Fragment A","CLICKED");
                communicator.respond("CLICKED");

            }
        });
        return view;
    }

}
