package com.locensate.androidskillstack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.locensate.androidskillstack.fragment.FirstFragment;
import com.locensate.androidskillstack.fragment.SecondFragment;
import com.locensate.androidskillstack.fragment.ThridFragment;
import com.locensate.androidskillstack.view.CustomTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentTestActivity extends AppCompatActivity implements CustomTitleBar.ClickedButtonListener {

    public int cont = 1;
    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.contain_fragment)
    FrameLayout containFragment;
    @BindView(R.id.activity_fragment_test)
    LinearLayout activityFragmentTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        titleBar.clicked(this);
        titleBar.setTitle("Fragment Test");
    }

    private void replaceFragment(Fragment secondFragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.contain_fragment, secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void clickedMenu() {
        if (cont % 3 == 1) {
            replaceFragment(new SecondFragment());
        } else if (cont % 3 == 2) {
            replaceFragment(new ThridFragment());
        } else {
            replaceFragment(new FirstFragment());
        }
        cont++;
    }

    @Override
    public void clickedBack() {

    }
}
