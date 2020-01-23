package quanticheart.com.koin.repo.images

import android.graphics.Bitmap

interface ImageRepo {
    fun getImageFromGalery(): Bitmap
}