package com.patipan.dev.coinrankingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patipan.dev.coinrankingapp.adapter.CoinRankingListAdapter
import kotlinx.android.synthetic.main.coin_ranking_fragment.*
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
    }

    private fun setUpSwipeRefresh() {
        coinRankingSwipeRefreshing.setOnRefreshListener {
            val isRefreshing = coinRankingSwipeRefreshing.isRefreshing
            setEnableSwipeRefreshing(isRefreshing)
        }
    }

    private fun observeLiveData() {
        coinRankingViewModel.observeMutableCoinItemList().observe(viewLifecycleOwner, Observer {
            val isRefreshing = coinRankingSwipeRefreshing.isRefreshing
            setEnableSwipeRefreshing(isRefreshing)

            coinRankingListAdapter.addAllItem(it)
        })

        coinRankingViewModel.observeError()?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.throwable?.message.toString(), Toast.LENGTH_LONG).show()
        })
    }

    private fun setUpRecyclerView() {
        coinRankingList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = coinRankingListAdapter
        }
    }

    private fun setEnableSwipeRefreshing(isRefreshing: Boolean) {
        if (isRefreshing) coinRankingSwipeRefreshing.isRefreshing = !isRefreshing
    }

    companion object {
        const val tagFragment: String = "coinRankingFragment"
        fun newInstance(): CoinRankingFragment =
            CoinRankingFragment().apply { arguments = Bundle() }
    }
}
