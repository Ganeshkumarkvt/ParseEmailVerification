package com.ganeshkumar.parseemailverification;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();



    }

    public void SignUptapped(View btnView){

        Toast.makeText(this, "Sign Up is pressed", Toast.LENGTH_SHORT).show();

    }

}