package app.simit.com.motivationalquotes.ui.Home_.quotes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.simit.com.motivationalquotes.Api.QuoteCalls
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.QuoteAdapter
import app.simit.com.motivationalquotes.databinding.FragmentQuotesBinding
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteDetail_.QuoteDetailActivity
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.Quote
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.QuoteClickListener
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
                quoteAdapter = QuoteAdapter(requireContext(), object : QuoteClickListener {
                    override fun getClickQuote(quote: String) {
                        instentTheQuote(quote)
                    }
                })
                binding.allQuotesRecyclerView.adapter = quoteAdapter
                setEmptyState()
                quoteAdapter.submitData(it)
            }
        }

    }

    private fun instentTheQuote(quote: String) {
        val intent = Intent(requireContext(), QuoteDetailActivity::class.java)
        intent.putExtra(getString(R.string.quote), quote)
        startActivity(intent)
    }

    public fun defaultList() {

        mViewModel.setService(quoteCalls)

        RetriveJob?.cancel()
        RetriveJob = lifecycleScope.launch {
            mViewModel.getQuotes()?.collectLatest {
                quoteAdapter.submitData(it)

            }
        }
    }

    private fun setEmptyState() {
        quoteAdapter.addLoadStateListener {

            // swipeRefreshLayout displays whether refresh is occurring
            binding.swipeRefreshLayout.isRefreshing = it.refresh is LoadState.Loading

            // show an empty state over the list when loading initially, before items are loaded
            binding.emptyState.isVisible = quoteAdapter.itemCount == 0

            Log.i(TAG, "setEmptyState: " + quoteAdapter.itemCount)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentQuotesBinding.inflate(inflater, container, false)

        quoteAdapter = QuoteAdapter(requireContext(), object : QuoteClickListener {
            override fun getClickQuote(quote: String) {
                instentTheQuote(quote)
            }
        })
        quoteAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.allQuotesRecyclerView.layoutManager = layoutManager
        binding.allQuotesRecyclerView.adapter = quoteAdapter
        setEmptyState()

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

        binding.swipeRefreshLayout.setOnRefreshListener {
            quoteAdapter.refresh()
        }
        return binding.root
    }

    companion object {
        private const val TAG = "QuotesFragment"
    }
}