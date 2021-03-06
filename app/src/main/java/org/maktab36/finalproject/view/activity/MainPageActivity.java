package org.maktab36.finalproject.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.maktab36.finalproject.view.fragment.MainPageFragment;

public class MainPageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainPageActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return MainPageFragment.newInstance();
    }
}