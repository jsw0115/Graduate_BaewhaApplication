package org.tchtown.baewhaaplication.ui.mypage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mypageViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public mypageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mypage fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}