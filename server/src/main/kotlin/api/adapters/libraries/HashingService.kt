package api.adapters.libraries

interface HashingService {
    suspend fun verify(plain: String, hashed: String): Boolean
    suspend fun hash(plain: String): String
}