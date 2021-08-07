package com.example.triviaapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.triviaapp.R;
import com.example.triviaapp.database.SharedPrefManager;

public class FragmentSummary extends Fragment {
    Context context;

//    String answer1;
//    String answer2;
//    String answer3;

    TextView summaryAnswer1, summaryAnswer2, summaryAnswer3;
    Button finish, history;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        summaryAnswer1 = view.findViewById(R.id.summaryAnswer1);
        summaryAnswer2 = view.findViewById(R.id.summaryAnswer2);
        summaryAnswer3 = view.findViewById(R.id.summaryAnswer3);
        finish = view.findViewById(R.id.finish);
        history = view.findViewById(R.id.history);

//        getting answers from sharedprefrences
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        summaryAnswer1.setText("Hello " + sharedPrefManager.getPref("answer1"));
        summaryAnswer2.setText(sharedPrefManager.getPref("answer2"));
        summaryAnswer3.setText(sharedPrefManager.getPref("answer3"));

        //finish the game and restarts it
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragmentFirstQuestion = new FirstQuestion();
                fragmentTransaction.replace(R.id.fragment_container, fragmentFirstQuestion).commit();
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragmentHistory = new FragmentHistory();

                //add current fragment to backstack
                String addToBackstack = fragmentHistory.getClass().getName();

                fragmentTransaction.replace(R.id.fragment_container, fragmentHistory).addToBackStack(addToBackstack).commit();
            }
        });
        return view;
    }
}
