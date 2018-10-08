package cz.airbank.airbankapplication.ui.transaction

import cz.airbank.airbankapplication.R
import cz.airbank.airbankapplication.arch.viewmodel.TransactionListViewModel
import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.ui.BaseRecyclerAdapter

/**
 * Adapter for transactions.
 *
 * @author Ivo Chvojka
 */
class TransactionListAdapter(viewModel: TransactionListViewModel) : BaseRecyclerAdapter<Transaction>(viewModel) {

    override fun getLayoutIdForPosition(position: Int) = R.layout.item_transaction
}