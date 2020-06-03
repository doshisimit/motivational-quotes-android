package app.simit.com.motivationalquotes.ui.quotes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.simit.com.motivationalquotes.Quote;
import app.simit.com.motivationalquotes.QuoteAdapter;
import app.simit.com.motivationalquotes.R;
import app.simit.com.motivationalquotes.Utils;

public class QuotesFragment extends Fragment {
    private static final String TAG = "QuotesFragment";
    private QuotesViewModel quotesViewModel;
    private RecyclerView recyclerView;
    private QuoteAdapter quoteAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quotesViewModel =
                ViewModelProviders.of(this).get(QuotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quotes, container, false);
        recyclerView =(RecyclerView) root.findViewById(R.id.allQuotesRecyclerView);
        quoteAdapter = new QuoteAdapter(getActivity());
        ArrayList<Quote> quotes= Utils.getQuotes();
        Log.d(TAG, "onCreateView: Quotes data: " +quotes.toString());
        if(null!=quotes){
            quoteAdapter.setQuotes(quotes);
        }
        recyclerView.setAdapter(quoteAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        return root;
    }
}
