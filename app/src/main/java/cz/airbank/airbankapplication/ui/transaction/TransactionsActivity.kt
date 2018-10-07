package cz.airbank.airbankapplication.ui.transaction

import android.os.Bundle
import com.emu.android.ui.BaseActivity
import cz.airbank.airbankapplication.R

/**
 * Activity for transaction list.
 *
 * @author Ivo Chvojka
 */
class TransactionsActivity : BaseActivity() {

    override val layoutRes: Int = R.layout.activity_transactions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.transactions_title);
        setupFragment(TransactionsFragment())
    }
}