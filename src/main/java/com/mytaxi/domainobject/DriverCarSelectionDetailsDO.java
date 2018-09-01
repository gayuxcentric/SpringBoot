package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "drivercarSelectionDtls"
)
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverCarSelectionDetailsDO {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false,unique = true)
    @NotNull(message = "driverId Cannot be null")
    private Long driverId;
	
	@Column(nullable = false,unique = true)
    @NotNull(message = "carId Cannot be null")
    private Long carId;
	
	@Column(nullable = false, name = "date_created")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Builder.Default
	private ZonedDateTime dateCreated = ZonedDateTime.now();
	    
	@Column(nullable = false, name = "date_updated")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Builder.Default
	private ZonedDateTime dateUpdated = ZonedDateTime.now();

}
