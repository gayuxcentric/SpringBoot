package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.DriverCarSelectionDetailsDTO;
import com.mytaxi.domainobject.DriverCarSelectionDetailsDO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class CarSelectedDetailsMapper {
	
	 public static DriverCarSelectionDetailsDTO populate(DriverCarSelectionDetailsDO driverCarSelectionDetailsDO)
	    {
				return DriverCarSelectionDetailsDTO.builder()
						.id(driverCarSelectionDetailsDO.getId())
						.carId(driverCarSelectionDetailsDO.getCarId())
						.driverId(driverCarSelectionDetailsDO.getDriverId())
						.dateCreated(driverCarSelectionDetailsDO.getDateCreated())
						.dateUpdated(driverCarSelectionDetailsDO.getDateUpdated())
						.build();
	    }


	
}
