package com.example.livedatatest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {

    private MutableLiveData<Integer> likedNumber;

    /*
    第一种：
    构造器初始化
     */
    /*
    ViewModelWithLiveData() {
        likedNumber = new MutableLiveData<>();
        likedNumber.setValue(0);
    }
    */

    public MutableLiveData<Integer> getLikedNumber() {
        /*
        第二种：
        get 方法内处理
         */
        if (likedNumber == null) {
            likedNumber = new MutableLiveData<Integer>();
            likedNumber.setValue(0);
        }
        return likedNumber;
    }

    public void addLikedNumber(int n) {
        likedNumber.setValue(likedNumber.getValue() + n);
    }

}
