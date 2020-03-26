package com.patipan.dev.coinrankingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun observeLiveData() {
        coinRankingViewModel.observeMutableCoinItemList().observe(viewLifecycleOwner, Observer {
            coinRankingListAdapter.addAllItem(it)
        })
    }

    private fun setUpRecyclerView() {
        coinRankingList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = coinRankingListAdapter
        }

        coinRankingViewModel.setCoinRankingItemList()
    }

    companion object {
        const val tagFragment: String = "coinRankingFragment"
        fun newInstance(): CoinRankingFragment =
            CoinRankingFragment().apply { arguments = Bundle() }
    }
}
