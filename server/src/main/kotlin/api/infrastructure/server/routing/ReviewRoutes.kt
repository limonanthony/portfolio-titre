package api.infrastructure.server.routing

import api.adapters.dtos.BaseEditionRequestDto
import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.ReviewCreationDto
import api.adapters.dtos.ReviewEditionDto
import api.app.controllers.ReviewController
import api.infrastructure.server.utils.getBearerToken
import api.infrastructure.server.utils.handleError
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.reviewRoutes(
    reviewController: ReviewController
) {
    route("/review") {
        post {
            handleError(call) {
                val token = getBearerToken(request)
                val body: ReviewCreationDto = receive()
                val review = reviewController.create(BaseRequestDto(token, body))
                call.respond(review)
            }
        }

        put("/{id}") {
            handleError(call) {
                val token = getBearerToken(request)
                val id = UUID.fromString(parameters["id"])
                val body = receive<ReviewEditionDto>()
                reviewController.edit(BaseRequestDto(token, BaseEditionRequestDto(id, body)))
                respond(HttpStatusCode.NoContent)
            }
        }

        delete("/{id}") {
            handleError(call) {
                val token = getBearerToken(request)
                val id = UUID.fromString(parameters["id"])
                reviewController.delete(BaseRequestDto(token, id))
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }

    route("/reviews") {
        get {
            handleError(call) {
                respond(reviewController.findAll())
            }
        }
    }
}
