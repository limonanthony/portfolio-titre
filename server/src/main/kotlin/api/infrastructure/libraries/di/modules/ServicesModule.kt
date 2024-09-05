package api.infrastructure.libraries.di.modules

import api.adapters.libraries.HashingService
import api.adapters.libraries.JwtService
import api.adapters.libraries.S3Service
import api.app.services.*
import api.infrastructure.libraries.hashing.HashingServiceImpl
import api.infrastructure.libraries.jwt.JwtServiceImpl
import api.infrastructure.s3.S3ServiceImpl
import org.koin.dsl.module

val servicesModule = module {
    factory { AuthService(get(), get()) }
    factory { ImageService(get(), get(), get()) }
    factory { ProjectService(get(), get(), get()) }
    factory { ReviewService(get(), get()) }
    factory { UserService(get(), get()) }

    factory<HashingService> { HashingServiceImpl() }
    factory<JwtService> { JwtServiceImpl(get()) }
    factory<S3Service> { S3ServiceImpl(get()) }
}