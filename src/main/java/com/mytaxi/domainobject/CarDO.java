package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class for Car
 * @author gayathri
 *
 */
@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "license_plate", columnNames = {"licensePlate"})
)
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
	
	@Column(nullable = false)
    @NotNull(message = "license Plate can not be null!")
    private String licensePlate;
	
	@Column(nullable = false)
    @NotNull(message = "Seat Count of the car can not be null!")
    private int seatCount;
	
	@Column(nullable = false)
    @NotNull(message = "Convertible Type of the car can not be null!")
    private Boolean isConvertibleType;
	
	@Column(nullable = true)
    private Float ratingStars;
	
	@Column(nullable = false)
    private String engineType;
	
	@Column(nullable = false, name = "date_created")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Builder.Default
	private ZonedDateTime dateCreated = ZonedDateTime.now();
	    
	@Column(nullable = false, name = "date_updated")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Builder.Default
	private ZonedDateTime dateUpdated = ZonedDateTime.now();
	
	@OneToOne
    @JoinColumn(name="manufacturer")
	private ManufacturerDO manufacturerDO;
	
}
