package com.patipan.dev.coinrankingapp.view

import android.os.Bundle
import com.patipan.dev.coinrankingapp.R
import com.patipan.dev.coinrankingapp.base.BaseAppCompatActivity

class CoinRankingActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_ranking)

        savedInstanceState ?: run {
            replaceFragment()
        }
    }


    private fun replaceFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayoutCoinRankingFragment,
            CoinRankingFragment.newInstance(),
            CoinRankingFragment.tagFragment
        ).commit()
    }
}
