package cz.airbank.airbankapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Model for Airbank transaction.
 *
 * @author Ivo Chvojka
 */
@Parcelize
data class Transaction(val id:Long, val amountInAccountCurrency: Long, val direction: Direction) : Parcelable