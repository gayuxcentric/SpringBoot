package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;

public class CarMapper
{
	
	public static CarDO createCarDO(CarDTO carDTO)
    {
		ManufacturerDO manufacturerDO = new ManufacturerDO();
		manufacturerDO.setManufacturerName(carDTO.getManufacturerName());
		
		return CarDO.builder()
		.carId(carDTO.getCarId())
		.engineType(carDTO.getEngineType())
		.isConvertibleType(carDTO.getIsConvertibleType())
		.licensePlate(carDTO.getLicensePlate())
		.ratingStars(carDTO.getRatingStars())
		.seatCount(carDTO.getSeatCount())
		.dateCreated(carDTO.getDateCreated())
		.dateUpdated(carDTO.getDateUpdated())
		.manufacturerDO(manufacturerDO)
		.build();
      }

	public static CarDTO getCarDTO(CarDO carDO)
    {
    	return 	CarDTO.builder()
    			.carId(carDO.getCarId())
    			.dateCreated(carDO.getDateCreated())
    			.dateUpdated(carDO.getDateUpdated())
    			.engineType(carDO.getEngineType())
    			.isConvertibleType(carDO.getIsConvertibleType())
    			.licensePlate(carDO.getLicensePlate())
    			.manufacturerName(carDO.getManufacturerDO().getManufacturerName())
    			.ratingStars(carDO.getRatingStars())
    			.seatCount(carDO.getSeatCount())
    			.build();
    }

   public static List<CarDTO> getCarDTOList(Collection<CarDO> carDOList)
    {
        return carDOList.stream()
            .map(CarMapper::getCarDTO)
            .collect(Collectors.toList());
    }

   
}
