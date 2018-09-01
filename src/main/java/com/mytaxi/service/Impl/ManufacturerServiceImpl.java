package com.mytaxi.service.Impl;

import org.springframework.stereotype.Service;

import com.mytaxi.dataaccessobject.ManufacturerRepsoitory;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

	private final ManufacturerRepsoitory manufacturerRepsoitory;
	
	
	public ManufacturerServiceImpl(final ManufacturerRepsoitory manufacturerRepsoitory)
    {
        this.manufacturerRepsoitory = manufacturerRepsoitory;
    }	
	
	@Override
	public ManufacturerDO find(Long ManufacturerId) throws EntityNotFoundException
	{
	    return findCarById(ManufacturerId);
	}


	private ManufacturerDO findCarById(Long ManufacturerId) throws EntityNotFoundException {
		return manufacturerRepsoitory.findById(ManufacturerId)
	            .orElseThrow(() -> new EntityNotFoundException("Invalid ID :Could not find Manufactured Details"));
	}
	
	public ManufacturerDO findCarByName(final CarDTO carDTO) throws EntityNotFoundException
    {
        String manufacturerName = carDTO.getManufacturerName();
        ManufacturerDO manufacturer = manufacturerRepsoitory.findByManufacturerName(manufacturerName);
        if (null == manufacturer)
        {
            throw new EntityNotFoundException("Manufacturer not found" + manufacturerName);
        }
        return manufacturer;
    }
	

}
