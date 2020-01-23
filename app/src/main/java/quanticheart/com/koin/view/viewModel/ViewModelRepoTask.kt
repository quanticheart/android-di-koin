package quanticheart.com.koin.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import quanticheart.com.koin.repo.tasks.TaskRepo

class ViewModelRepoTask(private val repo: TaskRepo) : ViewModel() {

    private val listStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun getStatusList(): LiveData<Boolean> = listStatus

    private val list: MutableLiveData<ArrayList<String>> = MutableLiveData()

    init {
        refreshList()
    }

    fun getTaskList(): LiveData<ArrayList<String>> = list

    fun addNewTask(task: String): Boolean {
        val s = repo.newTask(task)
        refreshList()
        return s
    }

    fun clearTaskList(): Boolean {
        val s = repo.cleanList()
        refreshList()
        return s
    }

    fun refreshList() {
        list.apply {
            value = repo.getTaskList()
        }
        listStatus.value = list.value?.size ?: 0 > 0
    }
}
