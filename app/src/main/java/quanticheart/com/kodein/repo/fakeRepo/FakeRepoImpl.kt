package quanticheart.com.kodein.repo.fakeRepo

import java.util.*

class FakeRepoImpl : FakeRepo {
    override fun getRandomString(): String {
        return "New task N°".randomString()
    }

    private fun String.randomString(): String {
        val rand = Random()
        val n = rand.nextInt(200)
        return "$this $n"
    }
}