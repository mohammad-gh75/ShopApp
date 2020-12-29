package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.FragmentSignUpBinding;
import org.maktab36.finalproject.viewmodel.LoginViewModel;

public class SignUpFragment extends Fragment {
    public static final String ARG_EMAIL = "email";
    private LoginViewModel mLoginViewModel;
    private FragmentSignUpBinding mSignUpBinding;
    private String mEmail;


    public SignUpFragment() {
        // Required empty public constructor
    }


    public static SignUpFragment newInstance(String email) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMAIL,email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mEmail=getArguments().getString(ARG_EMAIL);
        }
        mLoginViewModel=new ViewModelProvider(this).get(LoginViewModel.class);
        mLoginViewModel.getNewCustomerLiveData().observe(this,customer -> {
            Log.d("reza3", customer.getEmail()+"\n"+customer.getId()+"\n"+customer.getUsername());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSignUpBinding = DataBindingUtil
                .inflate(inflater,R.layout.fragment_sign_up,container,false);

        initUI();
        setListener();

        return mSignUpBinding.getRoot();
    }

    private void initUI() {
        mSignUpBinding.editTextSignUpEmail.setText(mEmail);
    }

    private void setListener(){
        mSignUpBinding.buttonSignUp.setOnClickListener(view -> {

            String firstName=mSignUpBinding.editTextSignUpFirstName.getText().toString();
            String lastName=mSignUpBinding.editTextSignUpLastName.getText().toString();
            String username=mSignUpBinding.editTextSignUpUsername.getText().toString();
            if(!checkFieldsIsEmpty(firstName,lastName,username)){
                mLoginViewModel.signUp(mEmail,firstName,lastName,username);
            }
        });

        mSignUpBinding.buttonCancel.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });
    }

    private boolean checkFieldsIsEmpty(String firstName, String lastName, String username){
        boolean isEmpty = false;
        if (firstName.isEmpty()) {
            isEmpty=true;
            mSignUpBinding.editTextSignUpFirstName.setError("first name is required");
        }
        if (lastName.isEmpty()) {
            isEmpty=true;
            mSignUpBinding.editTextSignUpLastName.setError("last name is required");
        }
        if (username.isEmpty()) {
            isEmpty=true;
            mSignUpBinding.editTextSignUpUsername.setError("username is required");
        }
        return isEmpty;
    }
}