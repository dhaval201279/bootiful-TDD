/**
 * 
 */
package com.its.reservation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.its.reservation.repository.Reservation;
import com.its.reservation.service.ReservationService;
import com.its.reservation.web.ReservationController;
import com.its.reservation.web.ReservationNotFoundException;

/**
 * @author Dhaval
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ReservationService reservationService;
	
	@Test
	public void getReservation_shouldReturnReservationInfo() {
		BDDMockito.given(reservationService.getReservationDetails(ArgumentMatchers.anyString()))
					.willReturn(new Reservation(Long.valueOf(1), "Dhaval", "Shah"));
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/reservation/Dhaval"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("firstName").value("Dhaval"))
				.andExpect(MockMvcResultMatchers.jsonPath("lastName").value("Shah"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getReservation_NotFound() throws Exception {
		BDDMockito.given(reservationService.getReservationDetails(ArgumentMatchers.anyString()))
					.willThrow(new ReservationNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/reservation/Dhaval"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
