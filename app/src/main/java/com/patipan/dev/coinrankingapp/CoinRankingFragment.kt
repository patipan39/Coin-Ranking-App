package com.patipan.dev.coinrankingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coin_ranking_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinRankingFragment : BaseFragment() {
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
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        coinRankingList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    companion object {
        const val tagFragment: String = "coinRankingFragment"
        fun newInstance(): CoinRankingFragment =
            CoinRankingFragment().apply { arguments = Bundle() }
    }
}
