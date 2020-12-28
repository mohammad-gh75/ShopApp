package org.maktab36.finalproject.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab36.finalproject.data.model.Product;
import org.maktab36.finalproject.view.fragment.NewProductFragment;

import java.util.ArrayList;
import java.util.List;

public class NotificationNewProductsActivity extends SingleFragmentActivity {

    public static final String EXTRA_NEW_PRODUCTS="newProductsExtra";

    public static Intent newIntent(Context context,ArrayList<Product> products) {
        Intent intent = new Intent(context, NotificationNewProductsActivity.class);
        intent.putExtra(EXTRA_NEW_PRODUCTS,products);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        ArrayList<Product> products = (ArrayList<Product>)
                getIntent().getSerializableExtra(EXTRA_NEW_PRODUCTS);
        return NewProductFragment.newInstance(products);
    }

}