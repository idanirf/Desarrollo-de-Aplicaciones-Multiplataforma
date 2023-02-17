package mapper

import dto.UserDto
import models.*
import utils.Contraseñas


fun toModel(user: UserDto): User {
        return User(
            id = user.id,
            name = user.name,
            username = user.username,
            email = user.email,
            password = Contraseñas.encriptar("secreto"),
            tipoUser = User.TipoUsuario.CLIENTE,
            phone = user.phone,
            website = user.website
        )
}

fun databaseToModel(entity: database.User) : User {
    if (entity.tipoUser == "ADMIN") {
        return User(
            _id = entity._id,
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            password = entity.password,
            tipoUser = User.TipoUsuario.ADMIN,
            phone = entity.phone,
            website = entity.website
        )
    } else if (entity.tipoUser == "CLIENTE") {
        return User(
            _id = entity.id,
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            password = entity.password,
            tipoUser = User.TipoUsuario.CLIENTE,
            phone = entity.phone,
            website = entity.website
        )
    } else {
        return User(
            _id = entity.id,
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            password = entity.password,
            tipoUser = User.TipoUsuario.TRABAJADOR,
            phone = entity.phone,
            website = entity.website
        )
    }
}











