package com.mytaxi.service.Impl;

import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverCarSelectionDetailsDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;

public interface DriverCarSelectionService {

	
    DriverCarSelectionDetailsDO save(DriverCarSelectionDetailsDO driverCarSelectionDetailsDO) throws ConstraintsViolationException;

    DriverCarSelectionDetailsDO findByDriverIdAndCarId(final Long driverId, final Long carId);
    
    void delete(DriverCarSelectionDetailsDO driverCarSelectionDetailsDO);
    
    List<Object[]> findDriverByCarAttributes(final CarDTO carDTO);

    List<DriverDO> findDriverByDriverAttributes(String username,OnlineStatus onlineStatus);
    
}
