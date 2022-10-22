package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class gitaSaar extends Fragment {


    Button logout;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gita_saar,container,false);
        logout = view.findViewById(R.id.logout);
        mAuth= FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });
        return view;



    }
}