package quanticheart.com.kodein.base

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.android.closestKodein
import quanticheart.com.kodein.application.MyApplication

abstract class BaseActivity : AppCompatActivity() {

    /**
     * Read more Dependency injection in
     *
     * closestKodein() inside an Android class (such as Context, Activity, Fragment, etc.)
     *
     * closestKodein(context) or closestKodein(() → context) inside another class
     *
     * The closestKodein function will only work if your Android Application class implements the KodeinAware interface.
     * The closestKodein result should be cached and not used multiple times in a row.
     *
     * @see MyApplication.kt
     * @link https://android.jlelse.eu/android-notifications-213004198f2e
     */

    //
    protected val kodein by closestKodein(MyApplication.appContext)

}