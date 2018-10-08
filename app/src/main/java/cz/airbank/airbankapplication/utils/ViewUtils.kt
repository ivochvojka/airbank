package cz.airbank.airbankapplication.utils

import android.databinding.BindingAdapter
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cz.airbank.airbankapplication.R
import cz.airbank.airbankapplication.model.Direction
import kotlinx.android.synthetic.main.abc_search_view.view.*

/**
 * Utilities for views.
 *
 * @author Ivo Chvojka
 */


fun View.showSnack(@StringRes stringResId: Int, duration: Int = Snackbar.LENGTH_LONG): Snackbar {
    val snack = this.createSnack(stringResId, duration)
    snack.show()
    return snack
}

fun View.createSnack(@StringRes stringResId: Int, duration: Int = Snackbar.LENGTH_LONG): Snackbar {
    return Snackbar.make(this, stringResId, duration)
}

@BindingAdapter("amountInAccountCurrency")
fun TextView.setAmountInAccountCurrency(amountInAccountCurrency: Long?) {
    text = "${amountInAccountCurrency} " + context.getString(R.string.account_currency)
}

@BindingAdapter("direction")
fun ImageView.setDirection(direction: Direction?) {
    if (direction == Direction.INCOMING) {
        setImageResource(R.drawable.ic_arrow_incomming)
    } else {
        setImageResource(R.drawable.ic_arrow_outgoing)
    }
}