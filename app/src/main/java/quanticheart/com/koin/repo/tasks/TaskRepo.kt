package quanticheart.com.koin.repo.tasks

interface TaskRepo {

    fun newTask(text: String): Boolean

    fun getTaskList(): ArrayList<String>

    fun cleanList(): Boolean

}