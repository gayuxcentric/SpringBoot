package com.mytaxi.service.Impl;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.DriverCarDtlsRespository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverCarSelectionDetailsDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;

@Service
public class DriverCarSelectionServiceImpl implements DriverCarSelectionService{


private final DriverCarDtlsRespository driverCarDtlsRespository;
	
	
	public DriverCarSelectionServiceImpl(final DriverCarDtlsRespository driverCarDtlsRespository)
	    {
	        this.driverCarDtlsRespository = driverCarDtlsRespository;
	    }	
	
		
	@Override
	public DriverCarSelectionDetailsDO save(DriverCarSelectionDetailsDO driverCarSelectionDetailsDO)
			throws ConstraintsViolationException {
		return driverCarDtlsRespository.save(driverCarSelectionDetailsDO);
	}

	 @Override 
	 public void delete(DriverCarSelectionDetailsDO driverCarSelectionDetailsDO)
	    {
		 driverCarDtlsRespository.delete(driverCarSelectionDetailsDO);
	 }

	@Override
	public DriverCarSelectionDetailsDO findByDriverIdAndCarId(Long carId,Long driverId) {
		return driverCarDtlsRespository.findByDriverIdAndCarId(carId,driverId);
	}


	@Override
	public List<Object[]> findDriverByCarAttributes(CarDTO carDTO) {
		return driverCarDtlsRespository.findDriverByCarAttributes(carDTO);
	}

	@Override
	public List<DriverDO> findDriverByDriverAttributes(String username,OnlineStatus onlineStatus){
		return driverCarDtlsRespository.findDriverByDriverAttributes(username,onlineStatus);
	}


}
