package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverCarSelectionDetailsDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

public interface DriverCarDtlsRespository  extends CrudRepository<DriverCarSelectionDetailsDO, Long>{
	
	DriverCarSelectionDetailsDO findByDriverIdAndCarId(Long carId,Long driverId);

	static String carAttrQueryList = "SELECT D, C FROM CarDO C, DriverDO D, DriverCarSelectionDetailsDO B " +
	           "WHERE B.carId = C.id AND D.id = B.driverId " +
	           "AND (C.seatCount = :#{#carDTO.seatCount} OR C.isConvertibleType = :#{#carDTO.isConvertibleType} " +
	           "OR C.engineType = :#{#carDTO.engineType} "+
	           "OR C.ratingStars = :#{#carDTO.ratingStars})";
	@Query(carAttrQueryList)
	List<Object[]> findDriverByCarAttributes(@Param("carDTO") final CarDTO carDTO);

	
	/*static String driverQueryLjst = "SELECT D from DriverDO D WHERE " +
	           "D.username = :#{#driverDTO.username} "+
	           "OR D.onlineStatus = :#{#driverDTO.onlineStatus}";
	@Query(driverQueryLjst)
	List<Object[]> findDriverByDriverAttributes(@Param("driverDTO") final DriverDTO driverDTO);*/
	
    @Query("SELECT d FROM DriverDO d WHERE LOWER(d.username) = LOWER(:username) "+
    		"OR d.onlineStatus = :onlineStatus")
    public List<DriverDO> findDriverByDriverAttributes(@Param("username") String username,@Param("onlineStatus") OnlineStatus onlineStatus);



}
