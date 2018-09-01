package com.mytaxi.service.driver;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarSelectionDetailsDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.Impl.CarDetailsService;
import com.mytaxi.service.Impl.DriverCarSelectionService;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    
    private final CarDetailsService carDetailsService;
    
    private final DriverCarSelectionService driverCarSelectionService;

	public DefaultDriverService(final DriverRepository driverRepository,CarDetailsService carDetailsService,DriverCarSelectionService driverCarSelectionService)
    {
        this.driverRepository = driverRepository;
        this.carDetailsService = carDetailsService;
        this.driverCarSelectionService = driverCarSelectionService;
    }
    
    /**
     * Method to select a car by the driver
     * @throws ConstraintsViolationException 
     */
    @Override
	public DriverDO selectCarByDriver(Long driverId, Long carId)
			throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException {
        DriverDO driverDO;
        CarDO carDO;
        try
        {
        	driverDO = find(driverId);
            carDO =carDetailsService.find(carId);
            if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
            {
                DriverCarSelectionDetailsDO driverCarObj = new DriverCarSelectionDetailsDO();
                driverCarObj.setDriverId(driverDO.getId());
                driverCarObj.setCarId(carDO.getCarId());
                driverCarObj.setDateCreated(ZonedDateTime.now());
                driverCarObj.setDateUpdated(ZonedDateTime.now());
                driverCarSelectionService.save(driverCarObj);
            }
            else if (null != driverDO && null != carDO && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Driver is offline");
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (DataIntegrityViolationException e)
        {
            throw new CarAlreadyInUseException("Car is already taken by driver");
        }
        return driverDO;
	}
    
        
    /**
     * Method to select a car by the driver
     * @throws ConstraintsViolationException 
     */
    @Override
	public DriverDO deSelectCarByDriver(Long driverId, Long carId)
			throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException {
        DriverDO driverDO;
        CarDO carDO;
        try
        {
        	driverDO = find(driverId);
        	carDO = carDetailsService.find(carId);
            if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
            {
            	DriverCarSelectionDetailsDO driverCarSelectionDetailsDO = driverCarSelectionService.findByDriverIdAndCarId(driverDO.getId(), carDO.getCarId());
            	if(null!=driverCarSelectionDetailsDO) {
            	driverCarSelectionService.delete(driverCarSelectionDetailsDO);
            	}
            	else {
            		throw new EntityNotFoundException("No Driver Car Details Found");
            	}
            }
            else if (null != driverDO && null != carDO && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Driver is OFFLINE");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            throw new CarAlreadyInUseException("Car is already taken by driver");
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found");
        }
        return driverDO;
	}
     
    
   

    /**
     * Selects a driver by id.
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

	/*@Override
	public List<DriverDTO> findDriverByDriverAttributes(DriverDTO driverDTO) {
        List<DriverDTO> driverDetailsList = new ArrayList<DriverDTO>();
        List<Object[]> drivers = driverCarSelectionService.findDriverByDriverAttributes(driverDTO);
        drivers.forEach(object -> driverDetailsList.add(DriverMapper.makeDriverDTOByDriver(object)));
        return  driverDetailsList;
	}*/
    
	@Override
	public List<DriverDTO> findDriverByDriverAttributes(String username,OnlineStatus onlineStatus) {
        List<DriverDO> drivers = driverCarSelectionService.findDriverByDriverAttributes(username,onlineStatus);
        return DriverMapper.makeDriverDTOList(drivers);
	}

	 @Override 
	 public List<DriverDTO> findDriverByCarAttributes(CarDTO carDTO)
	    {
	        List<DriverDTO> driverDetailsList = new ArrayList<DriverDTO>();
	        List<Object[]> drivers = driverCarSelectionService.findDriverByCarAttributes(carDTO);
	        drivers.forEach(object -> driverDetailsList.add(DriverMapper.makeDriverDTOByCar(object)));
	        return driverDetailsList;
	    } 
	

}
