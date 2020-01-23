package quanticheart.com.koin.application

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import quanticheart.com.koin.repo.fakeRepo.FakeRepo
import quanticheart.com.koin.repo.fakeRepo.FakeRepoImpl
import quanticheart.com.koin.repo.images.ImageRepo
import quanticheart.com.koin.repo.images.ImageRepoImpl
import quanticheart.com.koin.repo.tasks.TaskRepo
import quanticheart.com.koin.repo.tasks.TaskRepoImpl
import quanticheart.com.koin.view.viewModel.ViewModelRepoImage
import quanticheart.com.koin.view.viewModel.ViewModelRepoFake
import quanticheart.com.koin.view.viewModel.ViewModelRepoTask

class MyApplication : Application() {

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

        /**
         * Agora que temos um módulo, vamos começar com Koin. Abra sua classe de aplicativo
         * ou faça uma (não se esqueça de declará-la em seu manifest.xml). Basta chamar
         * a startKoin()função:
         */

        startKoin {
            androidLogger()
            androidContext(appContext)
            modules(appModule)
        }
    }

    private val appModule = module {
        // single instance of HelloRepository
        single<FakeRepo> { FakeRepoImpl() }
        single<ImageRepo> { ImageRepoImpl(appContext) }
        single<TaskRepo> { TaskRepoImpl(appContext) }
        // MyViewModel ViewModel
        viewModel { ViewModelRepoImage(get()) }
        viewModel { ViewModelRepoFake(get()) }
        viewModel { ViewModelRepoTask(get()) }
    }

}