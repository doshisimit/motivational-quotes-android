package app.simit.com.motivationalquotes.ui.Home_.quotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.simit.com.motivationalquotes.Api.QuoteCalls
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.QuoteAdapter
import app.simit.com.motivationalquotes.databinding.FragmentQuotesBinding
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.QuoteListDecoretor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QuotesFragment() : Fragment() {
    private var decoretor: QuoteListDecoretor? = null
    private lateinit var mViewModel: QuotesViewModel
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var binding: FragmentQuotesBinding

    @Inject
    lateinit var quoteCalls: QuoteCalls
    private var RetriveJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(QuotesViewModel::class.java)
        mViewModel.setService(quoteCalls)
        Log.i(TAG, "onCreate: ")
    }


    public fun SearchQuery(query: String) {
        Log.i(TAG, "SearchQuery: " + query)
        mViewModel.searchQuote(query)

        RetriveJob?.cancel()
        RetriveJob = lifecycleScope.launch {
            mViewModel.getQuotes()?.collectLatest {
                quoteAdapter.submitData(it)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentQuotesBinding.inflate(inflater, container, false)

        quoteAdapter = QuoteAdapter(requireContext())
        quoteAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.allQuotesRecyclerView.layoutManager = layoutManager
        binding.allQuotesRecyclerView.adapter = quoteAdapter

        if (decoretor != null) {
            binding.allQuotesRecyclerView.removeItemDecoration(decoretor!!)
        }
        decoretor = QuoteListDecoretor(requireContext())
        binding.allQuotesRecyclerView.addItemDecoration(decoretor!!)

        RetriveJob?.cancel()
        RetriveJob = lifecycleScope.launch {
            mViewModel.getQuotes()?.collectLatest {
                quoteAdapter.submitData(it)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
    }

    companion object {
        private const val TAG = "QuotesFragment"
    }
}