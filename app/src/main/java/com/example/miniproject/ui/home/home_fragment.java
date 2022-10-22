package com.example.miniproject.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.miniproject.R;


public class home_fragment extends Fragment {


    ViewFlipper flipper;
    private ImageView map_iskon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_fragment, container, false);

        int []imgarray ={R.drawable.gita_arj,R.drawable.gita_chariot,R.drawable.gita_wheel,R.drawable.krish_arjun};
        flipper= view .findViewById(R.id.flipper);

        for (int i=0;i<imgarray.length;i++){
            showImage(imgarray[i]);
        }


        map_iskon = view.findViewById(R.id.map_iskon);
        map_iskon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });
        return view;

    }

    private void openMap(){
        //initialize uri
        Uri uri = Uri.parse("geo:0,0?q=International Society for Krishna Consciousness ,Juhu");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    public void showImage(int img){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(img);
        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }
}