/**
 * 
 */
package com.its.reservation.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dhaval
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
	
	public Reservation(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	
	private String lastName;

	/*@Autowired(required = false)
	public Reservation(Long id, String name) {
		this.id = id;
		this.name = name;
	}*/
	
}
