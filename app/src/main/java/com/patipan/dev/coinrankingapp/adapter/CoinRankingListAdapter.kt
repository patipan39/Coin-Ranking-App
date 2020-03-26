package com.patipan.dev.coinrankingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patipan.dev.coinrankingapp.R
import java.lang.IllegalStateException

class CoinRankingListAdapter :
    ListAdapter<BaseCoinRankingAdapterData, RecyclerView.ViewHolder>(CoinRankingDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            leftViewType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_coin_ranking_left_item, parent, false)
                CoinRankingLeftViewHolder(view)
            }

            rightViewType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_coin_ranking_right_item, parent, false)
                CoinRankingRightViewHolder(view)
            }

            else -> {
                throw IllegalStateException("Can't CreateViewHolder in this ${this::class.java}")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CoinRankingLeftViewHolder -> {
                holder.onBind()
            }

            is CoinRankingRightViewHolder -> {
                holder.onBind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CoinRankingLeftData -> {
                leftViewType
            }
            is CoinRankingRightData -> {
                rightViewType
            }

            else -> {
                throw IllegalStateException("Con't getItemViewType in this ${this::class.java}")
            }
        }
    }

    fun addAllItem(listBaseData: ArrayList<BaseCoinRankingAdapterData>) {
        val coinRankingList = arrayListOf<BaseCoinRankingAdapterData>()
        coinRankingList.addAll(listBaseData)
        submitList(coinRankingList)
    }

    inner class CoinRankingLeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {

        }
    }

    inner class CoinRankingRightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {

        }
    }

    class CoinRankingDiffCallBack : DiffUtil.ItemCallback<BaseCoinRankingAdapterData>() {
        override fun areItemsTheSame(
            oldItem: BaseCoinRankingAdapterData,
            newItem: BaseCoinRankingAdapterData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BaseCoinRankingAdapterData,
            newItem: BaseCoinRankingAdapterData
        ): Boolean {
            return false
        }
    }

    companion object {
        private const val leftViewType: Int = 0
        private const val rightViewType: Int = 1
    }
}