/**
 * 
 */
package com.its.reservation;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.its.reservation.repository.Reservation;
import com.its.reservation.repository.ReservationRepository;

/**
 * @author Dhaval
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTest {
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Test
	public void getReservation_returnReservationDetails() {
		Reservation savedReservation = entityManager.persistAndFlush(
				new Reservation("Dhaval","Shah"));
		
		Reservation aReservation = reservationRepository.findByFirstName("Dhaval");
		
		Assertions.assertThat(aReservation.getFirstName())
			.isEqualTo(savedReservation.getFirstName());
		
		Assertions.assertThat(aReservation.getLastName())
			.isEqualTo(savedReservation.getLastName());
	}

}
