package quanticheart.com.kodein.application

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.Kodein.Module
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import quanticheart.com.kodein.repo.fakeRepo.FakeRepo
import quanticheart.com.kodein.repo.fakeRepo.FakeRepoImpl
import quanticheart.com.kodein.repo.images.ImageRepo
import quanticheart.com.kodein.repo.images.ImageRepoImpl
import quanticheart.com.kodein.repo.tasks.TaskRepo
import quanticheart.com.kodein.repo.tasks.TaskRepoImpl

class MyApplication : Application(), KodeinAware {

    /**
     * For create context for all modules
     */
    companion object {
        lateinit var appContext: Context
    }

    /**
     * this, on App onCreate, create and init Context for modules
     */
    override fun onCreate() {
        super.onCreate()

        /**
         * init context
         */
        appContext = applicationContext
    }

    /**
     * in implementations KodeinAware, override val kodein for init DI
     */
    override val kodein by Kodein.lazy {

        /**
         * import one module with all modules app
         */
        import(appDiModule)
    }

    /**
     * create one module for import all modelus
     */
    private val appDiModule = Module {
        import(diTasks)
        import(diImages)
        import(diFake)
    }

    private val diTasks = Module {
        bind<TaskRepo>() with singleton {
            TaskRepoImpl(appContext)
        }
    }

    private val diImages = Module {
        bind<ImageRepo>() with singleton {
            ImageRepoImpl(appContext)
        }
    }

    private val diFake = Module {
        bind<FakeRepo>() with singleton {
            FakeRepoImpl()
        }
    }

    // ** Docs
    //==============================================================================================

//    /**
//     * Modules
//     */
//
//    /**
//     * Singleton Modules
//     *
//     * With Singleton binding, a target bean is instantiated lazily on the first access and re-used on all further requests:
//     *
//     * @sample val service1: TaskRepo = kodein.instance()
//     */
//    private val diSingTon = Kodein.Module {
//        bind<TaskRepo>() with singleton {
//            TaskRepoImpl()
//        }
//    }
//
//    /**
//     * Scoped Modules
//     *
//     * Kodein provides a standard scope for any component (Android or not). The WeakContextScope will keep singleton and
//     * multiton instances as long as the context (= component) lives.
//     *
//     * @sample val service1: TaskRepo = kodein.instance()
//     */
//    val diScoped = Kodein {
//        bind<TaskRepo>() with scoped(WeakContextScope.of<Activity>()).singleton {
//            TaskRepoImpl()
//        }
//    }
//
//    /**
//     * Provider Modules
//     *
//     * With Factory binding, the initialization block receives an argument, and a new object is returned from it every time
//     *
//     * @sample val service1: TaskRepo = kodein.with("myTag").instance()
//     */
//    private val diProvider = Kodein.Module {
//        bind<TaskRepo>() with provider {
//            TaskRepoImpl()
//        }
//    }
//
//    /**
//     * Factory Modules
//     *
//     * With Factory binding, the initialization block receives an argument, and a new object is returned from it every time
//     *
//     * @sample val service1: TaskRepo = kodein.with("myTag").instance()
//     */
//    private val diFactory = Kodein.Module {
//        bind<TaskRepo>() with factory { app: String ->
//            TaskRepoImpl()
//        }
//    }
//
//    /**
//     * Multiton Modules
//     *
//     * With Factory binding, the initialization block receives an argument, and a new object is returned from it every time
//     *
//     * @sample val service1: TaskRepo = kodein.with("myTag").instance()
//     * @sample val service2: TaskRepo = kodein.with("myTag").instance() // same service1
//     */
//    private val diMultiton = Kodein.Module {
//        bind<TaskRepo>() with multiton { app: String ->
//            TaskRepoImpl()
//        }
//    }

}