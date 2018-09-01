package com.mytaxi.service.Impl;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class CarDetailsServiceImpl implements CarDetailsService{
	
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(CarDetailsService.class);

	
	private final CarRepository carRepository;
	
	
	public CarDetailsServiceImpl(final CarRepository carRepository)
	    {
	        this.carRepository = carRepository;
	    }	
	

	@Override
	public CarDO find(Long carId) throws EntityNotFoundException
	{
	    return findCarById(carId);
	}

	private CarDO findCarById(Long carId) throws EntityNotFoundException {
		return carRepository.findById(carId)
	            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
	}

	/**
     * Creates a new car.
     * @param CarDO
     * @return
     * @throws ConstraintsViolationException
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
    	CarDO carDOEntity;
        try
        {
        	carDOEntity = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to CAR creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return carDOEntity;
    }

	@Override
	public void delete(Long carId) throws EntityNotFoundException {
		carRepository.deleteById(carId);
	}


	@Override
	public List<CarDO> findAllCars() {
		return  (List<CarDO>) carRepository.findAll();
	}


	@Override
	@Transactional
	public CarDO updateRating(Long carId, float ratingStars) throws EntityNotFoundException {
		 CarDO carDO= findCarById(carId);
		 carDO.setRatingStars(ratingStars);
		 return carDO;
	}
	

	
	

}
