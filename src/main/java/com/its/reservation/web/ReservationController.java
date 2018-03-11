/**
 * 
 */
package com.its.reservation.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.its.reservation.repository.Reservation;
import com.its.reservation.service.ReservationService;

/**
 * @author Dhaval
 *
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {
	
	private ReservationService reservationService;
	
	
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}



	@RequestMapping(method = RequestMethod.GET, value = "/{name}")
	private Reservation getReservation(@PathVariable String name) {
		System.out.println("Entering and leaving ReservationController : getReservation after fetching service");
		return reservationService.getReservationDetails(name);
	}
	
	@ExceptionHandler()
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void userNotFoundHandler(ReservationNotFoundException rnfe) {
		System.out.println("Entering and leaving ReservationController : userNotFoundHandler");
	}
	

}
