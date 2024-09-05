package api.infrastructure.server.routing

import api.adapters.dtos.BaseEditionRequestDto
import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.UserCreationDto
import api.adapters.dtos.UserEditionDto
import api.app.controllers.UserController
import api.infrastructure.server.utils.getBearerToken
import api.infrastructure.server.utils.handleError
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import java.util.UUID

fun Route.userRoutes(
    userController: UserController
) {
    route("/user") {
        post {
            handleError(call) {
                val token = getBearerToken(request)
                val body = receive<UserCreationDto>()
                val user = userController.create(BaseRequestDto(token, body))
                respond(HttpStatusCode.Created, user)
            }
        }

        put("/{id}") {
            handleError(call) {
                val token = getBearerToken(request)
                val id = UUID.fromString(parameters["id"])
                val body = receive<UserEditionDto>()
                userController.update(BaseRequestDto(token, BaseEditionRequestDto(id, body)))
                respond(HttpStatusCode.NoContent)
            }
        }

        delete("/{id}") {
            handleError(call) {
                val token = getBearerToken(request)
                val id = UUID.fromString(parameters["id"])
                userController.delete(BaseRequestDto(token, id))
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}