package com.mytaxi.datatransferobject;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverCarSelectionDetailsDTO {
	
	private Long id;
	private Long carId;
	private Long driverId;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
	
	
}
