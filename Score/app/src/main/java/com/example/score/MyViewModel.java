package com.example.score;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> aTeamScore;
    private MutableLiveData<Integer> bTeamScore;

    private int aBack, bBack;

    public MutableLiveData<Integer> getaTeamScore() {
        if (null == aTeamScore) {
            aTeamScore = new MutableLiveData<>();
            aTeamScore.setValue(0);
        }
        return aTeamScore;
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (null == bTeamScore) {
            bTeamScore = new MutableLiveData<>();
            bTeamScore.setValue(0);
        }
        return bTeamScore;
    }

    public void aTeamAdd(int s) {
        storeBack();
        aTeamScore.setValue(aTeamScore.getValue() + s);
    }

    public void bTeamAdd(int s) {
        storeBack();
        bTeamScore.setValue(bTeamScore.getValue() + s);
    }

    public void reset() {
        storeBack();
        aTeamScore.setValue(0);
        bTeamScore.setValue(0);
    }

    public void undo() {
        aTeamScore.setValue(aBack);
        bTeamScore.setValue(bBack);
    }

    private void storeBack() {
        aBack = aTeamScore.getValue();
        bBack = bTeamScore.getValue();
    }

}
