package quanticheart.com.koin.view.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import quanticheart.com.koin.repo.images.ImageRepo

class ViewModelRepoImage(val repo: ImageRepo) : ViewModel() {
    private val image: MutableLiveData<Bitmap?> =
        MutableLiveData<Bitmap?>().apply {
            value = repo.getImageFromGalery()
        }

    fun getImage(): LiveData<Bitmap?> = image
}
