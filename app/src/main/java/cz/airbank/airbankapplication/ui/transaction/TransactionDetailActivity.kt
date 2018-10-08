package cz.airbank.airbankapplication.ui.transaction

import android.os.Bundle
import cz.airbank.airbankapplication.R
import cz.airbank.airbankapplication.ui.BaseActivity

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