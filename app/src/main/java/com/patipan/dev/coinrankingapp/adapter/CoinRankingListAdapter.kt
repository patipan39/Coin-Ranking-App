package com.patipan.dev.coinrankingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.patipan.dev.coinrankingapp.R
import com.patipan.dev.model.BaseCoinRankingAdapterData
import com.patipan.dev.model.CoinRankingLeftData
import com.patipan.dev.model.CoinRankingRightData
import kotlinx.android.synthetic.main.layout_coin_ranking_left_item.view.*
import kotlinx.android.synthetic.main.layout_coin_ranking_right_item.view.*

class CoinRankingListAdapter :
    PagedListAdapter<BaseCoinRankingAdapterData, RecyclerView.ViewHolder>(CoinRankingDiffCallBack()) {

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
                val itemLeft = getItem(position) as? CoinRankingLeftData
                holder.onBind(itemLeft)
            }

            is CoinRankingRightViewHolder -> {
                val itemRight = getItem(position) as? CoinRankingRightData
                holder.onBind(itemRight)
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

    fun addAllItem(listBaseData: PagedList<BaseCoinRankingAdapterData>) {
        submitList(listBaseData)
    }

    inner class CoinRankingLeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(itemLeft: CoinRankingLeftData?) {
            itemLeft ?: return
            itemView.apply {
                coinRankingLeftTitle.text = itemLeft.name
                coinRankingLeftDescription.text = itemLeft.description
            }
        }
    }

    inner class CoinRankingRightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(itemRight: CoinRankingRightData?) {
            itemRight ?: return
            itemView.apply {
                coinRankingRightTitle.text = itemRight.name
            }
        }
    }

    class CoinRankingDiffCallBack :
        DiffUtil.ItemCallback<BaseCoinRankingAdapterData>() {
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