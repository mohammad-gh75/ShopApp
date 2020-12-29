package org.maktab36.finalproject.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab36.finalproject.data.model.Customer;
import org.maktab36.finalproject.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private ProductRepository mRepository;

    private LiveData<Customer> mCustomerLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository=ProductRepository.getInstance();
        mCustomerLiveData=mRepository.getCustomerLiveData();
    }

    public LiveData<Customer> getCustomerLiveData() {
        return mCustomerLiveData;
    }

    public void fetchCustomerLiveData(String email){
        mRepository.fetchCustomerLiveData(email);
    }

    public boolean isCustomerExist(Customer customer) {
        Log.d("reza3", "isEmailExist: ");
        return customer != null;
    }


    public void signUp(String username, String password) {
    }

}
