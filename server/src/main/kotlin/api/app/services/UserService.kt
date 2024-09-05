package api.app.services

import api.adapters.dtos.UserCreationDto
import api.adapters.dtos.UserEditionDto
import api.adapters.libraries.HashingService
import api.adapters.repositories.UserRepository
import api.app.abstract.CoroutineContext
import api.app.entities.User
import api.app.exceptions.ConflictException
import api.app.exceptions.NotFoundException
import kotlinx.coroutines.withContext
import java.util.*

class UserService(
    private val repository: UserRepository,
    private val hashingService: HashingService
) : CoroutineContext() {
    suspend fun create(data: UserCreationDto): User = withContext(dispatcher) {
        if (repository.exists { it.email == data.email }) throw ConflictException(User::class.simpleName!!, "email")
        val user = User(
            email = data.email,
            password = hashingService.hash(data.password),
            type = data.type
        )
        repository.create(user)
    }

    suspend fun findByCredentials(email: String, password: String): User = withContext(dispatcher) {
        val user = repository.find { it.email == email } ?: throw NotFoundException(User::class.simpleName!!)
        hashingService.verify(password, user.password)
        user
    }

    suspend fun findById(id: UUID): User = withContext(dispatcher) {
        repository.find { it.id == id } ?: throw NotFoundException(User::class.simpleName!!)
    }

    suspend fun findByEmail(email: String): User = withContext(dispatcher) {
        repository.find { it.email == email } ?: throw NotFoundException(User::class.simpleName!!)
    }

    suspend fun findAll(): List<User> = withContext(dispatcher) {
        repository.findAll()
    }

    suspend fun edit(id: UUID, data: UserEditionDto): Unit = withContext(dispatcher) {
        val user = findById(id)

        data.email?.let { user.email = it }
        data.password?.let { user.password = hashingService.hash(it) }
        data.type?.let { user.type = it }

        repository.save(user)
    }

    suspend fun delete(id: UUID) {
        findById(id)
        repository.delete(id)
    }
}
