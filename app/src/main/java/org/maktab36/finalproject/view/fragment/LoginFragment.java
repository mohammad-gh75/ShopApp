package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.FragmentLoginBinding;
import org.maktab36.finalproject.viewmodel.LoginViewModel;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding mLoginBinding;
    private LoginViewModel mLoginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
                .inflate(inflater, R.layout.fragment_login, container, false);

        setListener();

        return mLoginBinding.getRoot();
    }


    private void setListener() {
        mLoginBinding.buttonLogin.setOnClickListener(view -> {
            String username = mLoginBinding.editTextUsername.getText().toString();
            String password = mLoginBinding.editTextPassword.getText().toString();
            if (username.isEmpty()) {
                mLoginBinding.editTextUsername.setError("username is required");
            } else if (password.isEmpty()) {
                mLoginBinding.editTextPassword.setError("password is required");
            } else {
                if (mLoginViewModel.isInfoExist(username, password)) {
                    mLoginViewModel.sendOrder();
                } else {
                    mLoginViewModel.signUp(username, password);
                }
            }
        });
    }
}