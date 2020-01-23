package quanticheart.com.koin.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {

    /**
     * Read more Dependency injection in
     **
     * @see MyApplication.kt
     * @link https://start.insert-koin.io/#/quickstart/android
     */
    val viewModel: T by lazy { getViewModel(viewModelClass()) }

    override fun onResume() {
        super.onResume()
        this.thisViewModel(viewModel)
    }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<T> =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>).kotlin

    abstract fun thisViewModel(viewModel: T)
}