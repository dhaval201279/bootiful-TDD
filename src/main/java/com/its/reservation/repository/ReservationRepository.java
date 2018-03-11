/**
 * 
 */
package com.its.reservation.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Dhaval
 *
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	Reservation findByFirstName(String name);
	
}
