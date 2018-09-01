package com.mytaxi.service.Impl;

import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarDetailsService
{

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;
    
    CarDO find(Long carId) throws EntityNotFoundException;
    
    List<CarDO> findAllCars();
    
    CarDO updateRating(Long carId,float ratingStars) throws EntityNotFoundException;
  
}
