package cz.airbank.airbankapplication.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.emu.android.ui.ViewModelBaseFragment
import cz.airbank.airbankapplication.arch.viewmodel.TransactionsViewModel
import cz.airbank.airbankapplication.databinding.FragmentTransactionsBinding

/**
 * Fragment for transactions list.
 *
 * @author Ivo Chvojka
 */
class TransactionsFragment : ViewModelBaseFragment<TransactionsViewModel, FragmentTransactionsBinding>(), TransactionsView {
    override val viewModelClazz: Class<TransactionsViewModel> = TransactionsViewModel::class.java

    override fun inflateBinding(inflater: LayoutInflater): FragmentTransactionsBinding =
            FragmentTransactionsBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.observeSnackEvent(this) { it?.let { binding.root.showSnack(it) } }

//        viewModel.logoutComplete.observe(this, Observer {
//            startActivity<LoginActivity> {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            }
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val appVersion = resources.getString(R.string.app_version)
//        binding.appVersion.text = String.format(appVersion, BuildConfig.VERSION_NAME)
    }

    override fun onTransactionClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}