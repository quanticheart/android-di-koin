package quanticheart.com.koin.view.viewModel

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import quanticheart.com.koin.R
import quanticheart.com.koin.adapter.RecyclerAdapter
import quanticheart.com.koin.base.BaseActivity

class ViewModelActivity : BaseActivity<ViewModelRepoTask>() {

    private val viewModelRepoImage: ViewModelRepoImage by viewModel()
    private val viewModelRepoFake: ViewModelRepoFake by viewModel()

    private lateinit var adapter: RecyclerAdapter

    override fun thisViewModel(viewModel: ViewModelRepoTask) {
        //
        viewModelRepoImage.getImage().observe(this, Observer { image ->
            image?.let {
                img.setImageBitmap(image)
            }
        })

        //
        viewModel.getStatusList().observe(this, Observer { status ->
            if (status) showList() else showMsg()
        })

        //
        viewModel.getTaskList().observe(this, Observer {
            adapter.addList(it)
        })

        //
        fabAdd.setOnClickListener {
            val nTasks = viewModelRepoFake.getTask()
            if (viewModel.addNewTask(nTasks)) {
                showList()
            }
        }

        //
        fabRefresh.setOnClickListener {
            verifyList()
        }

        //
        fabCleah.setOnClickListener {
            if (viewModel.clearTaskList()) {
                adapter.clearList()
                showMsg()
            }
        }
    }

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        adapter = RecyclerAdapter(list)
    }

    private fun verifyList() = viewModel.refreshList()

    private fun showList() {
        flipper.displayedChild = 1
    }

    private fun showMsg() {
        flipper.displayedChild = 0
    }
}
