package com.mytaxi.controller.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }
        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
    
    
    
    public static DriverDTO makeDriverDTOByCar(Object[] object)
    {
        DriverDO driverDO = (DriverDO) object[0];
        CarDO carDO =  (CarDO) object[1] ;
        CarDTO carDTO = CarDTO.builder()
            .carId(carDO.getCarId())
            .ratingStars(carDO.getRatingStars())
            .seatCount(carDO.getSeatCount())
            .engineType(carDO.getEngineType())
            .isConvertibleType(carDO.getIsConvertibleType())
            .licensePlate(carDO.getLicensePlate())
            .manufacturerName(carDO.getManufacturerDO().getManufacturerName())
            .build();
        DriverDTO driverDTO = makeDriverDTO(driverDO);
        driverDTO.setCarDTO(carDTO);
        return driverDTO;
    }
    
    public static DriverDTO makeDriverDTOByDriver(Object[] object)
    {
        DriverDO driverDO =  (DriverDO) object[0];
        DriverDTO driverDTO = makeDriverDTO(driverDO);
        return driverDTO;

    }
    
}
