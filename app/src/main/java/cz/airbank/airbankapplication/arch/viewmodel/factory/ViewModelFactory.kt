package cz.airbank.airbankapplication.arch.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Factory for ViewModels.
 *
 * @author Ivo Chvojka
 */

@Singleton
class ViewModelFactory @Inject constructor(private var creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators.entries) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }

        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}