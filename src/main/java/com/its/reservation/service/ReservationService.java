/**
 * 
 */
package com.its.reservation.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.its.reservation.repository.Reservation;
import com.its.reservation.repository.ReservationRepository;
import com.its.reservation.web.ReservationNotFoundException;

/**
 * @author Dhaval
 *
 */
@Service
public class ReservationService {
	
	private ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Cacheable("reservation")
	public Reservation getReservationDetails(String name) {
		System.out.println("Entering and leaving ReservationService : getReservationDetails "
				+ "after calling reservationRepository.findByFirstName");
		Reservation aReservation = reservationRepository.findByFirstName(name);
		if (aReservation == null) {
			throw new ReservationNotFoundException();
		}
		return aReservation;
	}

}
