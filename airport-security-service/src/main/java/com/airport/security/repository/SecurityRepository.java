/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airport.security.entity.SecurityEntity;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Long> {

	@Query("SELECT s FROM SecurityEntity s WHERE s.bookingId = ?1")
	Optional<SecurityEntity> findByBookingId(Long bookingId);
}