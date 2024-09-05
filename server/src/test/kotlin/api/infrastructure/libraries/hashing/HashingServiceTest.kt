package api.infrastructure.libraries.hashing

import api.adapters.libraries.HashingService
import io.github.serpro69.kfaker.Faker
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.shouldNotBe

const val STRING_LENGTH = 71

class HashingServiceTest : FunSpec({
    val faker = Faker()

    lateinit var hashingService: HashingService
    lateinit var string: String

    fun getRandomString(): String {
        return faker.random.randomString(STRING_LENGTH, true)
    }

    beforeTest {
        string = getRandomString()
        hashingService = HashingServiceImpl()
    }

    context("Hash function") {
        test("`hashes successfully`") {
            val hash = hashingService.hash(string)
            hash shouldNotBe string
        }

        test("`gives different results with the same string`") {
            val hash1 = hashingService.hash(string)
            val hash2 = hashingService.hash(string)
            hash1 shouldNotBe hash2
        }

        test("`returned salts are not the same`") {
            val hash1 = hashingService.hash(string)
            val hash2 = hashingService.hash(string)
            val salt1 = hash1.substring(0, 29)
            val salt2 = hash2.substring(0, 29)
            salt1 shouldNotBe salt2
        }
    }

    context("Verify function") {
        lateinit var hash: String

        beforeTest {
            hash = hashingService.hash(string)
        }

        test("returns true when values are the same`") {
            hashingService.verify(string, hash).shouldBeTrue()
        }

        test("`returns false when strings are different`") {
            val random = getRandomString()
            hashingService.verify(random, hash).shouldNotBeTrue()
        }
    }
})
