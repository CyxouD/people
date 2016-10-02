package com.example.dima.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dima.myapplication.enteties.Role;

public class EnterActivity extends AppCompatActivity {
    //public static Role myRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
    }

    public void logInAsAdmin(View v) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra("userRole", Role.ADMIN.toString());
        startActivity(mainActivity);
    }

    public void logInAsUser(View v) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra("userRole", Role.MEMBER.toString());
        startActivity(mainActivity);
    }


}
