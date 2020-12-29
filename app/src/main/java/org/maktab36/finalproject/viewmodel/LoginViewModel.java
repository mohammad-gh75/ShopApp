package org.maktab36.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.maktab36.finalproject.data.model.Customer;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private ProductRepository mRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository=ProductRepository.getInstance();
    }

    public boolean isEmailExist(String email) {
        Customer customer =mRepository.getCustomerSync(email);
        return customer != null;
    }

    public void signUp(String username, String password) {
    }

}
