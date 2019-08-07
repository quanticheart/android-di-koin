package quanticheart.com.kodein

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.generic.instance
import quanticheart.com.kodein.adapter.RecyclerAdapter
import quanticheart.com.kodein.base.BaseActivity
import quanticheart.com.kodein.repo.fakeRepo.FakeRepo
import quanticheart.com.kodein.repo.images.ImageRepo
import quanticheart.com.kodein.repo.tasks.TaskRepo

class MainActivity : BaseActivity() {

    //
    private val repo: TaskRepo by kodein.instance()
    private val repoImage: ImageRepo by kodein.instance()
    private val repoFake: FakeRepo by kodein.instance()

    //
    private lateinit var adapter: RecyclerAdapter

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        adapter = RecyclerAdapter(applicationContext, list)

        //
        img.setImageBitmap(repoImage.getImageFromGalery())

        //
        fabAdd.setOnClickListener {
            val nTasks = repoFake.getRandomString()
            if (repo.newTask(nTasks)) {
                adapter.addTask(nTasks)
                showList()
            }
        }

        //
        fabRefresh.setOnClickListener {
            verifyList()
        }

        //
        fabCleah.setOnClickListener {
            if (repo.cleanList()) {
                adapter.clearList()
                showMsg()
            }
        }

        //
        verifyList()
    }

    private fun verifyList() {
        if (repo.getTaskList().size > 0) {
            adapter.addList(repo.getTaskList())
            showList()
        } else {
            showMsg()
        }
    }

    private fun showList() {
        flipper.displayedChild = 1
    }

    private fun showMsg() {
        flipper.displayedChild = 0
    }

}
