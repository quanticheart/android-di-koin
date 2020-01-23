package quanticheart.com.koin.view.viewModel

import androidx.lifecycle.ViewModel
import quanticheart.com.koin.repo.fakeRepo.FakeRepo

class ViewModelRepoFake(val repo: FakeRepo) : ViewModel() {
    fun getTask(): String = repo.getRandomString()
}
