package com.androhungers.rssnewsreader.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.androhungers.rssnewsreader.model.addRss.AddRssRequestModel;
import com.androhungers.rssnewsreader.model.addRss.AddRssResponseModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssRequestModel;
import com.androhungers.rssnewsreader.model.deleteRss.DeleteRssResponseModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssRequestModel;
import com.androhungers.rssnewsreader.model.editRss.EditRssResponseModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssRequestModel;
import com.androhungers.rssnewsreader.model.getRss.GetRssResponseModel;
import com.androhungers.rssnewsreader.model.signin.SigninRequestModel;
import com.androhungers.rssnewsreader.model.signin.SigninResponseModel;
import com.androhungers.rssnewsreader.model.signup.SignupRequestModel;
import com.androhungers.rssnewsreader.model.signup.SignupResponseModel;
import com.androhungers.rssnewsreader.repository.ApiRepository;

public class RssFeedViewModel extends ViewModel {


    public MutableLiveData<Integer> tabPosition = new MutableLiveData<>();
    public MutableLiveData<String> feedNameLiveData = new MutableLiveData<>();
    public MutableLiveData<String> linkLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public MutableLiveData<AddRssRequestModel> addRssRequestModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AddRssResponseModel> addRssResponseModelMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<EditRssRequestModel> editRssRequestModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<EditRssResponseModel> editRssResponseModelMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<GetRssRequestModel> getRssRequestModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<GetRssResponseModel> getRssResponseModelMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<DeleteRssRequestModel> deleteRssRequestModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<DeleteRssResponseModel> deleteRssResponseModelMutableLiveData = new MutableLiveData<>();


    public boolean isValidInput(){
        if(feedNameLiveData.getValue().isEmpty()){
            errorMsg.postValue("Please enter Feed Name");
            return false;
        }

        if(linkLiveData.getValue().isEmpty()){

            errorMsg.postValue("Please enter Feed Link");
            return false;
        }

        return true;
    }


    public LiveData<AddRssResponseModel> requestForAddRss(AddRssRequestModel request) {
        return new ApiRepository().addRss(request);
    }

    public LiveData<GetRssResponseModel> getGetResponse = Transformations.switchMap(getRssRequestModelMutableLiveData,
            getRssRequestModelMutableLiveData -> new ApiRepository().getRss(getRssRequestModelMutableLiveData));

    public LiveData<GetRssResponseModel> requestForGetRss(GetRssRequestModel request) {
        return new ApiRepository().getRss(request);
    }

    public LiveData<EditRssResponseModel> requestForEditRss(EditRssRequestModel request) {
        return new ApiRepository().editRss(request);
    }

    public LiveData<DeleteRssResponseModel> requestForDeleteRss(DeleteRssRequestModel request) {
        return new ApiRepository().deleteRss(request);
    }

}
