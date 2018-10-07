package cz.airbank.airbankapplication.model

/**
 * Model for Airbank transaction.
 *
 * @author Ivo Chvojka
 */
data class Transaction(val id:Long, val amountInAccountCurrency: Long, val direction: Direction)