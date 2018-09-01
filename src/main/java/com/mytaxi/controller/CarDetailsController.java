package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.Impl.CarDetailsService;
import com.mytaxi.service.Impl.ManufacturerService;

@RestController
@RequestMapping("v1/CarDetails")
public class CarDetailsController {

    private final CarDetailsService carDetailsService;
    
    private final ManufacturerService manufacturerService;
    
    @Autowired
    public CarDetailsController(final CarDetailsService carDetailsService,ManufacturerService  manufacturerService)
    {
        this.carDetailsService = carDetailsService;
        this.manufacturerService = manufacturerService;
    }
    
	/**
     * Method to find Car by ID
     * @param carId
     * @return CarDTO
     * @throws EntityNotFoundException
     */
    @GetMapping("/{carId}")
    public CarDTO getCarDetails(@Valid @PathVariable Long carId) throws EntityNotFoundException
    {
        return CarMapper.getCarDTO(carDetailsService.find(carId));
    }
    
    /**
     * Method to create new Car
     * @param carDTO
     * @return CarDTO
     * @throws ConstraintsViolationException
     * @throws EntityNotFoundException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
        CarDO carDO = CarMapper.createCarDO(carDTO);
        carDO.setManufacturerDO(manufacturerService.findCarByName(carDTO));
        return CarMapper.getCarDTO(carDetailsService.create(carDO));
    }

    /**
     * Method to fetch all CAR Details
     * @return List of CARDTO
     */
    @GetMapping("/getAllCars")
    public List<CarDTO> getAllCars()
    {
        return CarMapper.getCarDTOList(carDetailsService.findAllCars());
    }
    
    /**
     * Method to delete the given car details
     * @param delCarId
     * @throws EntityNotFoundException
     */
    @DeleteMapping("/{delCarId}")
    public void deleteCar(@Valid @PathVariable long delCarId) throws EntityNotFoundException
    {
    	carDetailsService.delete(delCarId);
    }
    
    @PutMapping("/{updateCarId}")
    public CarDO updateCarRating(
        @Valid @PathVariable long updateCarId,@RequestParam float ratingStars)
        throws ConstraintsViolationException, EntityNotFoundException
    {
    	return carDetailsService.updateRating(updateCarId,ratingStars);
    }
    
}
    
    
    
    
    	


