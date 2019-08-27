package com.androhungers.rssnewsreader.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androhungers.rssnewsreader.MainActivity;
import com.androhungers.rssnewsreader.R;
import com.androhungers.rssnewsreader.model.signin.SignRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.androhungers.rssnewsreader.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.victor.loading.rotate.RotateLoading;

import net.cachapa.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_user_name) EditText etUserName;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_age) EditText etAge;
    @BindView(R.id.ex) ExpandableLayout expandableLayout;
    @BindView(R.id.btn_sign_in) TextView textViewSignIn;
    @BindView(R.id.tv_sign_up) TextView textViewSignUp;
    @BindView(R.id.root_layout) RelativeLayout rootLayout;
    @BindView(R.id.progress) RotateLoading rotateLoading;

    private LoginViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        ButterKnife.bind(this);

        viewModel.STATE.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equalsIgnoreCase("signin")){
                    expandableLayout.collapse();
                    textViewSignIn.setText("Sign In");
                    textViewSignUp.setText("Don't have an Account?");
                }else {
                    expandableLayout.expand();
                    textViewSignIn.setText("Register");
                    textViewSignUp.setText("Already have an Account?");
                }
            }
        });

        viewModel.errorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                rotateLoading.stop();
                Snackbar.make(rootLayout, s, Snackbar.LENGTH_LONG).show();
            }
        });

        viewModel.signinRequestModelMutableLiveData.observe(this, new Observer<SignRequestModel>() {
            @Override
            public void onChanged(SignRequestModel signRequestModel) {

            }
        });



        viewModel.STATE.setValue("signin");

        //Submit();
        //SingInUp();

    }

    @OnClick(R.id.btn_sign_in) void Submit(){
        rotateLoading.start();
        viewModel.name.setValue(etName.getText().toString());
        viewModel.age.setValue(etAge.getText().toString());
        viewModel.userName.setValue(etUserName.getText().toString());
        viewModel.password.setValue(etPassword.getText().toString());

        if(viewModel.isValidInput()){
            if(viewModel.STATE.getValue().equalsIgnoreCase("signin")){
                viewModel.signinRequestModelMutableLiveData.setValue(viewModel.makeLoginRequest());
                viewModel.makeSigninRequest(viewModel.signinRequestModelMutableLiveData.getValue()).observe(this, new Observer<SigninResponseModel>() {
                    @Override
                    public void onChanged(SigninResponseModel signinResponseModel) {
                        rotateLoading.stop();
                    }
                });
            }else if(viewModel.STATE.getValue().equalsIgnoreCase("signup")) {

            }
        }
    }

    @OnClick(R.id.tv_sign_up) void SingInUp(){
        if(viewModel.STATE.getValue().equalsIgnoreCase("signin")){
            viewModel.setState("signup");
        }else if(viewModel.STATE.getValue().equalsIgnoreCase("signup")) {
            viewModel.setState("signin");
        }
    }
}
