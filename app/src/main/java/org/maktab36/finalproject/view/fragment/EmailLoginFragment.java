package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.databinding.FragmentEmailLoginBinding;
import org.maktab36.finalproject.viewmodel.LoginViewModel;


public class EmailLoginFragment extends Fragment {

    private FragmentEmailLoginBinding mLoginBinding;
    private LoginViewModel mLoginViewModel;
    private String mEmail;

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
        mLoginViewModel.getCustomerLiveData().observe(this,customer -> {
            if (mLoginViewModel.isCustomerExist(customer)) {
                goToSendOrderPage();
            } else {
                goToSignUpPage();
            }
        });

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
            mEmail = mLoginBinding.editTextEmail.getText().toString();
            Log.d("reza3", "setListener: "+mEmail);
            if (mEmail.isEmpty()) {
                mLoginBinding.editTextEmail.setError("Email is required");
            } else {
                mLoginViewModel.fetchCustomerLiveData(mEmail);
            }
        });
    }

    private void goToSendOrderPage() {
        Toast.makeText(getActivity(), "customer exist", Toast.LENGTH_SHORT).show();
    }

    private void goToSignUpPage(){
        Toast.makeText(getActivity(), "customer not exist", Toast.LENGTH_SHORT).show();
        SignUpFragment fragment =SignUpFragment.newInstance(mEmail);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack("SignUpFragment")
                .commit();
    }
}