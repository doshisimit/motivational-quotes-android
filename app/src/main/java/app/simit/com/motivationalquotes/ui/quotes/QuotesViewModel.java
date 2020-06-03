package app.simit.com.motivationalquotes.ui.quotes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuotesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QuotesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}