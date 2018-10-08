package cz.airbank.airbankapplication.ui.transaction

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import cz.airbank.airbankapplication.ui.ViewModelBaseFragment
import com.jakewharton.rxbinding2.widget.RxAdapterView
import cz.airbank.airbankapplication.R
import cz.airbank.airbankapplication.arch.viewmodel.TransactionListViewModel
import cz.airbank.airbankapplication.databinding.FragmentTransactionsBinding
import cz.airbank.airbankapplication.model.Direction
import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.utils.showSnack
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_transactions.view.*


/**
 * Fragment for transactions list.
 *
 * @author Ivo Chvojka
 */
class TransactionListFragment : ViewModelBaseFragment<TransactionListViewModel, FragmentTransactionsBinding>(),
        TransactionListView {

    private lateinit var recyclerAdapter: TransactionListAdapter

    override val viewModelClazz: Class<TransactionListViewModel> = TransactionListViewModel::class.java

    override fun inflateBinding(inflater: LayoutInflater): FragmentTransactionsBinding =
            FragmentTransactionsBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.observeSnackEvent(this) { it?.let { binding.root.showSnack(it) } }
        viewModel.observeTransactions(this) { it?.let { recyclerAdapter.setData(it) } }
        viewModel.observeTransaction(this) { it?.let { onTransactionClick(it) } }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // filter Spinner
        binding.root.spinner_filter.adapter = ArrayAdapter<Direction>(context, R.layout.item_filter, Direction.values())
        RxAdapterView.itemSelections(binding.root.spinner_filter)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { position -> viewModel.getTransactions(Direction.values()[position]) }

        // transactions RecyclerView
        recyclerAdapter = TransactionListAdapter(viewModel)
        with(binding.root.recycler_transactions) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(context!!, R.drawable.divider_transactions)!!)
            }
            addItemDecoration(divider)
            adapter = recyclerAdapter
        }
    }

    private fun onTransactionClick(transaction: Transaction) {
        val intent = Intent(activity, TransactionDetailActivity::class.java)
                .apply { putExtra(TransactionDetailActivity.TRANSACTION_KEY, transaction) }
        startActivity(intent)
    }
}