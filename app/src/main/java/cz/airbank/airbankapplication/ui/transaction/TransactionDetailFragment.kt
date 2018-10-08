package cz.airbank.airbankapplication.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import cz.airbank.airbankapplication.ui.ViewModelBaseFragment
import cz.airbank.airbankapplication.arch.viewmodel.TransactionDetailViewModel
import cz.airbank.airbankapplication.databinding.FragmentTransactionDetailBinding
import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.utils.showSnack

/**
 * Fragment for transactions list.
 *
 * @author Ivo Chvojka
 */
class TransactionDetailFragment : ViewModelBaseFragment<TransactionDetailViewModel, FragmentTransactionDetailBinding>(),
        TransactionDetailView {

    companion object {
        fun newInstance(extras: Bundle): TransactionDetailFragment {
            val fragment = TransactionDetailFragment()
            fragment.arguments = extras
            return fragment
        }
    }

    override val viewModelClazz: Class<TransactionDetailViewModel> = TransactionDetailViewModel::class.java

    override fun inflateBinding(inflater: LayoutInflater): FragmentTransactionDetailBinding =
            FragmentTransactionDetailBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.observeSnackEvent(this) { it?.let { binding.root.showSnack(it) } }
        val transaction = arguments?.getParcelable<Transaction>(TransactionDetailActivity.TRANSACTION_KEY)
        transaction?.let { viewModel.loadTransactionDetail(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}