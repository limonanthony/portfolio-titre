package api.infrastructure.libraries.hashing

import api.adapters.libraries.HashingService
import com.toxicbakery.bcrypt.Bcrypt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HashingServiceImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : HashingService {

    companion object {
        // TODO Put this in .env
        const val SALT_ROUND = 10
    }

    override suspend fun verify(plain: String, hashed: String): Boolean = withContext(dispatcher) {
        Bcrypt.verify(plain, hashed.toByteArray(Charsets.UTF_8))
    }

    override suspend fun hash(plain: String): String = withContext(dispatcher) {
        String(Bcrypt.hash(plain, SALT_ROUND), Charsets.UTF_8)
    }
}