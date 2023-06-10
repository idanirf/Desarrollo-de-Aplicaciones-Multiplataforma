package es.drodriguez.itvh2hibernatespringv2.repositories.owner

import es.drodriguez.itvh2hibernatespringv2.models.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository: JpaRepository<Owner, String> {
}