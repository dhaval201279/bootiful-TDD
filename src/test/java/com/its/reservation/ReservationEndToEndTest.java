/**
 * 
 */
package com.its.reservation;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.its.reservation.repository.Reservation;
import com.its.reservation.repository.ReservationRepository;

/**
 * @author Dhaval
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReservationEndToEndTest {
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void getReservation_shouldReturnReservationDetails() {
		// Arrange
		
		// Act
		ResponseEntity<Reservation> response = testRestTemplate.getForEntity("/reservation/{name}", 
																		Reservation.class, "Dhaval");
		
		// Assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("Dhaval");
		Assertions.assertThat(response.getBody().getLastName()).isEqualTo("Shah");
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}

}

@Component
class SampleDataCLR implements CommandLineRunner {

	private ReservationRepository reservationRepository;
	
	public SampleDataCLR(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("@@@@@@@@@@@@@@ Entering SampleDataCLR : run");
		
		reservationRepository.save(new Reservation("Dhaval","Shah"));
		reservationRepository.save(new Reservation("Yatharth","Shah"));
		reservationRepository.findAll().forEach(System.out :: println);
		
		System.out.println("@@@@@@@@@@@@@@ Leaving SampleDataCLR : run");
		
	}
	
}
