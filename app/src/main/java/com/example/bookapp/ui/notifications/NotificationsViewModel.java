package com.example.bookapp.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("WELCOME TO THE BOOK ZONE!\n ONLY BOOKS IN ANIME MOLOCH");
    }

    public LiveData<String> getText() {
        return mText;
    }
}