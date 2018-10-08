package cz.airbank.airbankapplication.ui.transaction

import android.os.Bundle
import com.emu.android.ui.BaseActivity
import cz.airbank.airbankapplication.R

/**
 * Activity for transaction detail.
 *
 * @author Ivo Chvojka
 */
class TransactionDetailActivity : BaseActivity() {

    companion object {
        const val TRANSACTION_KEY = "transaction"
    }

    override val layoutRes: Int = R.layout.activity_transaction_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar(R.string.transaction_detail_title, true)
        setupFragment(TransactionDetailFragment.newInstance(intent.extras))
    }

}