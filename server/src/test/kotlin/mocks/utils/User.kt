package mocks.utils

import api.app.entities.User
import api.app.entities.enums.UserType
import io.github.serpro69.kfaker.Faker

fun getRandomUser(): User {
    val faker = Faker()
    return User(
        email = faker.internet.email(),
        password = "Pa\$\$w0rd!",
        type = faker.random.nextEnum(UserType::class.java)
    )
}