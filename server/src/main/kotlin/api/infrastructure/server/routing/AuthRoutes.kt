package api.infrastructure.server.routing

import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.LoginDto
import api.adapters.dtos.RefreshDto
import api.adapters.dtos.RegisterDto
import api.app.controllers.AuthController
import api.infrastructure.server.utils.handleError
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes(
    authController: AuthController
) {
    route("/auth") {
        post("/login") {
            handleError(call) {
                val body: LoginDto = call.receive()
                val tokens = authController.login(BaseRequestDto(null, body))
                call.respond(tokens)
            }
        }

        post("/register") {
            handleError(call) {
                val body: RegisterDto = receive()
                val tokens = authController.register(BaseRequestDto(null, body))
                call.respond(tokens)
            }
        }

        post("/refresh") {
            handleError(call) {
                val body = receive<RefreshDto>()
                val tokens = authController.refreshTokens(BaseRequestDto(null, body))
                call.respond(tokens)
            }
        }
    }
}