package quanticheart.com.kodein.repo.images

import android.graphics.Bitmap

interface ImageRepo {
    fun getImageFromGalery(): Bitmap
}