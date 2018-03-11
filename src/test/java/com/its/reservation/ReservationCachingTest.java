/**
 * 
 */
package com.its.reservation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.its.reservation.repository.Reservation;
import com.its.reservation.repository.ReservationRepository;
import com.its.reservation.service.ReservationService;

/**
 * @author Dhaval
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class ReservationCachingTest {

	@Autowired
	ReservationService reservationService;
	
	@MockBean
	ReservationRepository reservationRepository;
	
	@Test
	public void caching_reducesDBCall() {
		BDDMockito.given(reservationRepository.findByFirstName(ArgumentMatchers.anyString()))
								.willReturn(new Reservation(Long.valueOf(1),"Dhaval","Shah"));
		
		reservationService.getReservationDetails("Dhaval");
		reservationService.getReservationDetails("Dhaval");
		
		Mockito.verify(reservationRepository, Mockito.times(1)).findByFirstName("Dhaval");
	}
}
