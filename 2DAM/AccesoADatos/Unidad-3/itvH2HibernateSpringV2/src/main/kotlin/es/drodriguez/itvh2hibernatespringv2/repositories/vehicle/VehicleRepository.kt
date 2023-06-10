package es.drodriguez.itvh2hibernatespringv2.repositories.vehicle

import es.drodriguez.itvh2hibernatespringv2.models.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository:JpaRepository<Vehicle, String> {
}