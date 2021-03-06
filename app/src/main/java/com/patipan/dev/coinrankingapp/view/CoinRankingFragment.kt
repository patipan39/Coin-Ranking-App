package com.patipan.dev.coinrankingapp.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patipan.dev.coinrankingapp.R
import com.patipan.dev.coinrankingapp.adapter.CoinRankingListAdapter
import com.patipan.dev.coinrankingapp.base.BaseFragment
import com.patipan.dev.coinrankingapp.view.viewmodel.CoinRankingViewModel
import com.patipan.dev.shared.mapperError
import kotlinx.android.synthetic.main.coin_ranking_fragment.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinRankingFragment : BaseFragment() {

    private val coinRankingListAdapter: CoinRankingListAdapter by inject()
    private val coinRankingViewModel: CoinRankingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coin_ranking_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        setUpRecyclerView()
        setUpSwipeRefresh()
        setUpSearchEditText()
        setUpKeyBoardListener()
    }

    private fun setUpRecyclerView() {
        coinRankingList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = coinRankingListAdapter
        }
    }

    private fun setUpSwipeRefresh() {
        coinRankingSwipeRefreshing.setOnRefreshListener {
            val isRefreshing = coinRankingSwipeRefreshing.isRefreshing
            if (isRefreshing) {
                coinRankingSearchEd.hideKeyBoard()
                coinRankingViewModel.refreshingData()
            }
        }
    }

    private fun observeLiveData() {
        coinRankingViewModel.observeMutableCoinItemList().observe(viewLifecycleOwner, Observer {
            setEnableSwipeRefreshing(coinRankingSwipeRefreshing.isRefreshing)
            coinRankingListAdapter.addAllItem(it)
        })

        coinRankingViewModel.observeError().observe(viewLifecycleOwner, Observer {
            setEnableSwipeRefreshing(coinRankingSwipeRefreshing.isRefreshing)
            val (code, errorMessage) = it.mapperError()
            Toast.makeText(context, "$code : $errorMessage", Toast.LENGTH_LONG).show()
        })
    }

    private fun setEnableSwipeRefreshing(isRefreshing: Boolean) {
        if (isRefreshing) coinRankingSwipeRefreshing.isRefreshing = !isRefreshing
    }

    private fun setUpSearchEditText() {
        val coinRankingEditText = coinRankingSearchTextInputLayout.editText ?: return
        coinRankingEditText.setOnEditorActionListener { _, actionId, _ ->
            val wording = coinRankingEditText.text.toString()
            coinRankingViewModel.searchCoinRanking(wording)
            coinRankingEditText.hideKeyBoard()

            actionId == EditorInfo.IME_ACTION_SEARCH
        }

        coinRankingSearchTextInputLayout.setEndIconOnClickListener { _ ->
            coinRankingEditText.text.clear()
            coinRankingViewModel.searchCoinRanking(coinRankingEditText.text.toString())
        }

    }

    private fun setUpKeyBoardListener() {
        KeyboardVisibilityEvent.setEventListener(activity ?: return) { isOpen ->
            if (isOpen) coinRankingSearchTextInputLayout.requestFocus() else coinRankingSearchTextInputLayout.clearFocus()
        }
    }

    companion object {
        const val tagFragment: String = "coinRankingFragment"
        fun newInstance(): CoinRankingFragment =
            CoinRankingFragment()
                .apply { arguments = Bundle() }
    }


}
