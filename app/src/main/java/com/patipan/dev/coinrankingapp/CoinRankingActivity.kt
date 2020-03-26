package com.patipan.dev.coinrankingapp

import android.os.Bundle

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
