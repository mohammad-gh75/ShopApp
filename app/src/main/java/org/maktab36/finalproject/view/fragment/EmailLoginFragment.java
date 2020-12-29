package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.FragmentEmailLoginBinding;
import org.maktab36.finalproject.viewmodel.LoginViewModel;


public class EmailLoginFragment extends Fragment {

    private FragmentEmailLoginBinding mLoginBinding;
    private LoginViewModel mLoginViewModel;

    public EmailLoginFragment() {
        // Required empty public constructor
    }

    public static EmailLoginFragment newInstance() {
        EmailLoginFragment fragment = new EmailLoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoginBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_email_login, container, false);

        setListener();

        return mLoginBinding.getRoot();
    }


    private void setListener() {
        mLoginBinding.buttonLogin.setOnClickListener(view -> {
            String email = mLoginBinding.editTextEmail.getText().toString();
            if (email.isEmpty()) {
                mLoginBinding.editTextEmail.setError("Email is required");
            } else {
                if (mLoginViewModel.isEmailExist(email)) {
                    goToSendOrderPage();
                } else {
                    goToSignUpPage();
                }
            }
        });
    }

    private void goToSendOrderPage() {

    }

    private void goToSignUpPage(){

    }
}