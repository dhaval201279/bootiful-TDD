/**
 * 
 */
package com.its.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.its.reservation.repository.Reservation;
import com.its.reservation.repository.ReservationRepository;
import com.its.reservation.service.ReservationService;
import com.its.reservation.web.ReservationNotFoundException;

/**
 * @author Dhaval
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
	
	ReservationService reservationService;
	
	@Mock
	ReservationRepository reservationRepository;
	
	@Before
	public void setUp() throws Exception {
		reservationService = new ReservationService(reservationRepository);
	}
	
	@Test
	public void getReservationDetails_returnsReservationInfo() {
		BDDMockito.given(reservationRepository.findByFirstName("Dhaval"))
					.willReturn(new Reservation(Long.valueOf(1), "Dhaval", "Shah"));
		
		Reservation aReservation = reservationService.getReservationDetails("Dhaval");
		
		assertThat(aReservation.getFirstName()).isEqualTo("Dhaval");
		assertThat(aReservation.getLastName()).isEqualTo("Shah");
	}
	
	@Test(expected = ReservationNotFoundException.class)
	public void getReservationDetails_whenNotFound() {
		BDDMockito.given(reservationRepository.findByFirstName("Dhaval")).willReturn(null);
		Reservation aReservation = reservationService.getReservationDetails("Dhaval");
	}

}
