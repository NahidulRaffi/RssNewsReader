package com.androhungers.rssnewsreader.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androhungers.rssnewsreader.model.signin.SigninRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.androhungers.rssnewsreader.model.signup.SignupRequestModel;
import com.androhungers.rssnewsreader.model.signup.SignupResponseModel;
import com.androhungers.rssnewsreader.repository.ApiRepository;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> STATE = new MutableLiveData<String>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> age = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();
    public MutableLiveData<SigninRequestModel> signinRequestModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<SignupRequestModel> signupRequestModelMutableLiveData = new MutableLiveData<>();


    public void setState(String state){
        this.STATE.postValue(state);
    }

    public boolean isValidInput(){
        if(userName.getValue().isEmpty()){
            errorMsg.postValue("Please enter your username");
            return false;
        }

        if(password.getValue().isEmpty()){

            errorMsg.postValue("Please enter your password");
            return false;
        }

        if(STATE.getValue().equalsIgnoreCase("signup")){
            if(name.getValue().isEmpty()){
                errorMsg.postValue("Please enter your name");
                return false;
            }

            if(age.getValue().isEmpty()){
                errorMsg.postValue("Please enter your age");
                return false;
            }
        }

        return true;
    }

    public SigninRequestModel makeLoginRequest(){
        return new SigninRequestModel(
                userName.getValue(),
                password.getValue()
        );
    }

    public SignupRequestModel makeSignUpRequest(){
        return new SignupRequestModel(
                userName.getValue(),
                password.getValue(),
                name.getValue(),
                age.getValue()
        );
    }

    public LiveData<SigninResponseModel> requestForSignIn(SigninRequestModel request) {
        return new ApiRepository().loginUser(request);
    }

    public LiveData<SignupResponseModel> requestForSignUp(SignupRequestModel request) {
        return new ApiRepository().signUpUser(request);
    }

}
