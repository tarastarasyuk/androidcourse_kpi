package com.tarastarasiuk.androidcourse.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.tarastarasiuk.androidcourse.activity.listener.OnFragmentInteractionListener;
import com.tarastarasiuk.androidcourse.R;
import com.tarastarasiuk.androidcourse.fragment.FormFragment;
import com.tarastarasiuk.androidcourse.util.FragmentUtil;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        FragmentUtil.replaceFragmentFor(getSupportFragmentManager(), new FormFragment(), getFragmentPlaceholderViewId());
    }

    @Override
    public int getFragmentPlaceholderViewId() {
        return R.id.fragmentPlaceholder;
    }

    public void switchOnFormFragment(View view) {
        FragmentUtil.replaceFragmentFor(getSupportFragmentManager(), new FormFragment(), getFragmentPlaceholderViewId());
    }
}
