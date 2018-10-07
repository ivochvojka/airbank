package cz.airbank.airbankapplication.utils

import timber.log.Timber

/**
 * Logging tree for release purpose.
 *
 * @author Ivo Chvojka
 */
class NotLoggingTree: Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    }
}