package cn.edu.gdut.dictationdemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {

    private final SavedStateHandle handle;
    private static final String HIGH_SCORE = "high_score";
    private static final String SHP_DATA_NAME = "shp_data_name";
    private static final String CURRENT_SCORE = "current_score";
    private static final String LEFT_NUMBER = "left_number";
    private static final String RIGHT_NUMBER = "right_number";
    private static final String OPERATOR = "operator";
    private static final String ANSWER = "answer";
    boolean winflag = false;

    public MyViewModel(Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(HIGH_SCORE)) {
            SharedPreferences shp = getApplication().getSharedPreferences(SHP_DATA_NAME, Context.MODE_PRIVATE);
            handle.set(HIGH_SCORE, shp.getInt(HIGH_SCORE, 0));
            handle.set(CURRENT_SCORE, 0);
            handle.set(LEFT_NUMBER, 0);
            handle.set(RIGHT_NUMBER, 0);
            handle.set(OPERATOR, "+");
            handle.set(ANSWER, 0);
        }
        generate();
    }

    public MutableLiveData<Integer> getHighScore() {
        return handle.getLiveData(HIGH_SCORE);
    }

    public MutableLiveData<Integer> getCurrentScore() {
        return handle.getLiveData(CURRENT_SCORE);
    }

    public MutableLiveData<Integer> getLeftNumber() {
        return handle.getLiveData(LEFT_NUMBER);
    }

    public MutableLiveData<Integer> getRightNumber() {
        return handle.getLiveData(RIGHT_NUMBER);
    }

    public MutableLiveData<String> getOpr() {
        return handle.getLiveData(OPERATOR);
    }

    public MutableLiveData<Integer> getAnswer() {
        return handle.getLiveData(ANSWER);
    }

    public void generate() {
        int RANGE = 20;
        Random random = new Random();
        int x,y;
        x = random.nextInt(RANGE) + 1;
        y = random.nextInt(RANGE) + 1;
        if (x % 2 == 0) {
            getOpr().setValue("+");
            if (x > y) {
                getAnswer().setValue(x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x - y);
            } else {
                getAnswer().setValue(y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y - x);
            }
        } else {
            getOpr().setValue("-");
            if (x > y) {
                getAnswer().setValue(x - y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
            } else {
                getAnswer().setValue(y - x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
            }
        }
    }

    public void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(SHP_DATA_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(HIGH_SCORE, getHighScore().getValue());
        editor.apply();
    }

    public void answerCorrect() {
        getCurrentScore().setValue(getCurrentScore().getValue() + 1);
        if (getCurrentScore().getValue() > getHighScore().getValue()) {
            getHighScore().setValue(getCurrentScore().getValue());
            winflag = true;
        }
        generate();
    }
}
