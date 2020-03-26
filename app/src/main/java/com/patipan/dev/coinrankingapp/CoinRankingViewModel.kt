package com.patipan.dev.coinrankingapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patipan.dev.coinrankingapp.adapter.BaseCoinRankingAdapterData
import com.patipan.dev.coinrankingapp.adapter.CoinRankingLeftData
import com.patipan.dev.coinrankingapp.adapter.CoinRankingRightData

class CoinRankingViewModel : ViewModel() {
    private val listBaseCoinItemAdapter = arrayListOf<BaseCoinRankingAdapterData>()
    private val mutableCoinLiveData = MutableLiveData<ArrayList<BaseCoinRankingAdapterData>>()

    fun setCoinRankingItemList() {
        listBaseCoinItemAdapter.add(CoinRankingLeftData(2))
        listBaseCoinItemAdapter.add(CoinRankingRightData(1))

        mutableCoinLiveData.value = listBaseCoinItemAdapter
    }

    fun observeMutableCoinItemList() = mutableCoinLiveData
}