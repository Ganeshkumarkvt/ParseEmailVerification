package com.ganeshkumar.parseemailverification;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUsername,edtloginpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginUsername = findViewById(R.id.edt_login);
        edtloginpassword = findViewById(R.id.edtloginpass);
    }

    public void Loginispressed(View btnview){

        ParseUser.logInInBackground(edtLoginUsername.getText().toString(), edtloginpassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null){
                    if (user.getBoolean("emailVerified")){
                        alertDisplayer("Login Sucessful", "Welcome, " + edtLoginUsername.getText().toString()+"!",false);
                    }else
                    {
                        ParseUser.logOut();
                        alertDisplayer("Login Fail", "Please Verify Your Email First",true);
                    }
                }else{
                    ParseUser.logOut();
                    alertDisplayer("Login Fail", e.getMessage() + " Please re-try",true);
                }
            }
        });


    }
    private void alertDisplayer(String title,String message, final boolean error){

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error){
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();


        ok.show();

    }
}