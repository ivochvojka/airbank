package cz.airbank.airbankapplication.ui.transaction

import android.os.Bundle
import cz.airbank.airbankapplication.R
import cz.airbank.airbankapplication.ui.BaseActivity

/**
 * Activity for transaction list.
 *
 * @author Ivo Chvojka
 */
class TransactionListActivity : BaseActivity() {

    override val layoutRes: Int = R.layout.activity_transactions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar(R.string.transactions_title, false)
        setupFragment(TransactionListFragment())
    }
}