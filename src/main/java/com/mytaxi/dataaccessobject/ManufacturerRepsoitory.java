package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.ManufacturerDO;

public interface ManufacturerRepsoitory extends CrudRepository<ManufacturerDO, Long>{
	
    ManufacturerDO findByManufacturerName(final String manufacturerName);


}
