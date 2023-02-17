package repositories.ktorfit

import dto.TareaDto
import dto.UserDto

interface IKtorfitRepository : CrudRepository<TareaDto, String> {
}