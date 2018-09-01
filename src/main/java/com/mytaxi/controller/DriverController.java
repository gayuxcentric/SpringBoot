package com.mytaxi.controller;

import java.text.ParseException;
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

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{	

    private final DriverService driverService;
    
    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }

    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }

    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }
    
    //Newly Added Methods
    
    /**
     * Method to select car by the driver
     * @param driverId
     * @param carId
     * @return DriverDTO
     * @throws EntityNotFoundException
     * @throws CarAlreadyInUseException
     * @throws ConstraintsViolationException 
     */
    @PostMapping("/selectCar")
    public DriverDTO selectCarByDriver(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException,
        CarAlreadyInUseException, ConstraintsViolationException
    {
        return DriverMapper.makeDriverDTO(driverService.selectCarByDriver(driverId, carId));
    }
    
    
    /**
     * Method to select car by the driver
     * @param driverId
     * @param carId
     * @return DriverDTO
     * @throws EntityNotFoundException
     * @throws CarAlreadyInUseException
     * @throws ConstraintsViolationException 
     */
    @DeleteMapping("/deSelectCar")
    public DriverDTO deSelectCarByDriver(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException,
        CarAlreadyInUseException, ConstraintsViolationException
    {
        return DriverMapper.makeDriverDTO(driverService.deSelectCarByDriver(driverId, carId));
    }
    
    /**
     * Method to deSelect car by the driver Attributes
     * @param userName
     * @param onlineStatus
     * @return List of DriverDTO
     * @throws ParseException
     */
   @GetMapping("/searchDriverByDriver")
    public List<DriverDTO> searchDriverByDriverAttributes(@Valid @RequestParam (value ="userName",required=false) String userName,
    		@RequestParam (value ="onlineStatus",required=false) OnlineStatus onlineStatus) throws ParseException
    {
	   return driverService.findDriverByDriverAttributes(userName,onlineStatus);
    }

   /**
    * Method to search driver details based on CAR Attributes
    * @param engineType
    * @param seatCount
    * @param ratingStars
    * @return List of DriverDTO
    * @throws ParseException
    */
    @GetMapping("/searchDriverByCar")
    public List<DriverDTO> searchDriverByCarAttributes(@RequestParam (value ="engineType",required=false) String engineType,
    		@RequestParam (value ="seatCount",required=false) Integer seatCount,
    		@RequestParam (value ="ratingStars",required=false) Float ratingStars) throws ParseException
    {
    	CarDTO carDTO = getCarParams(engineType,seatCount,ratingStars);
        return driverService.findDriverByCarAttributes(carDTO);
    }
    
    /**
     * Method to build Car Search Params
     * @param engineType
     * @param seatCount
     * @param ratingStars
     * @return CarDTO
     * @throws ParseException
     */
    private CarDTO getCarParams(String engineType,Integer seatCount,Float ratingStars) throws ParseException
    {
    	return CarDTO.builder()
    			.engineType(engineType)
    			.seatCount(seatCount)
    			.ratingStars(ratingStars)
    			.build();
    }
    
    
    
}
