package org.maktab36.finalproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import org.maktab36.finalproject.data.model.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginViewModel extends AndroidViewModel {
    public static final String PREF_KEY_LOGIN_SET="loginSet";

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean isInfoExist(String username, String password){
        List<Customer> customers=getCustomers();
        for (Customer customer : customers) {
            if(customer.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public void signUp(String username,String password){
    }

    public void createOrder(){

    }
    public void sendOrder(){
        createOrder();
    }

    private List<Customer> getCustomers(){
        List<Customer> customers=null;
        return customers ==null?new ArrayList<>() : customers;
    }
}
