package me.codego.custombehavior;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.codego.custombehavior.autohide.TestAutoHideActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAutoHide(View view) {
        startActivity(new Intent(this, TestAutoHideActivity.class));
    }
}
