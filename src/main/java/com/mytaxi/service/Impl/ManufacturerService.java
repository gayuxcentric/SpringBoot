package com.mytaxi.service.Impl;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

public interface ManufacturerService {
	
	ManufacturerDO find(Long manufacturerId) throws EntityNotFoundException;
	
	ManufacturerDO findCarByName(final CarDTO carDTO) throws EntityNotFoundException;


}
