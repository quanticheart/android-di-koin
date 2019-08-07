package quanticheart.com.kodein.repo.tasks

import android.content.Context
import android.widget.Toast

class TaskRepoImpl(private val context: Context) : TaskRepo {

    private val list = arrayListOf("Task °1", "Task °2")

    override fun newTask(text: String): Boolean {
        val n = list.size
        list.add(text)
        Toast.makeText(context, "Added new tasks", Toast.LENGTH_SHORT).show()
        return list.size > n
    }

    override fun getTaskList(): ArrayList<String> {
        return list
    }

    override fun cleanList(): Boolean {
        list.clear()
        return list.size == 0
    }

}