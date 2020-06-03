package app.simit.com.motivationalquotes.ui.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.QuoteAdapter
import app.simit.com.motivationalquotes.databinding.FragmentQuotesBinding

class QuotesFragment : Fragment() {
    private lateinit var mViewModel: QuotesViewModel
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var binding: FragmentQuotesBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this).get(QuotesViewModel::class.java)

        quoteAdapter = QuoteAdapter(requireContext())
        binding.allQuotesRecyclerView.adapter = quoteAdapter
        binding.allQuotesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        mViewModel.quotes.observe(viewLifecycleOwner, Observer { list ->
            if (list.isNotEmpty()) {
                quoteAdapter.setQuotes(list)
            } else {
                // TODO: 6/3/2020 empty state
            }
        })

        return binding.root
    }

    companion object {
        private const val TAG = "QuotesFragment"
    }
}