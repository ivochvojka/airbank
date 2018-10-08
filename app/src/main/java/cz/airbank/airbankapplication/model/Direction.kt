package cz.airbank.airbankapplication.model

import cz.airbank.airbankapplication.AirbankApplication
import cz.airbank.airbankapplication.R

/**
 * Enumeration for direction.
 *
 * @author Ivo Chvojka
 */
enum class Direction(strResId: Int) {
    ALL(R.string.transaction_type_all),
    INCOMING(R.string.transaction_type_incomming),
    OUTGOING(R.string.transaction_type_outgoing);

    private var label: String = AirbankApplication.instance.getString(strResId)

    override fun toString(): String {
        return label
    }
}