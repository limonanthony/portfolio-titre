package api.infrastructure.libraries.di.modules

import api.app.controllers.AuthController
import api.app.controllers.ReviewController
import api.app.controllers.UserController
import org.koin.dsl.module

val controllerModule = module {
    factory { AuthController(get()) }
    factory { ReviewController(get(), get()) }
    factory { UserController(get(), get()) }
}