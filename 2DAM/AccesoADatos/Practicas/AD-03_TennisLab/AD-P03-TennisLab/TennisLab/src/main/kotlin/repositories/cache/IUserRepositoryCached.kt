package repositories.cache

import models.User
interface IUserRepositoryCached : CrudRepositoryCache<User,String> {
}