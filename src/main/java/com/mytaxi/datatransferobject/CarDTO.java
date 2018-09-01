package com.mytaxi.datatransferobject;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder	
public class CarDTO
{
    @JsonIgnore
    private Long carId;

    private String licensePlate;
    
    private Integer seatCount;
    
    private Boolean isConvertibleType;
	
    private Float ratingStars;
	
	private String engineType;
	
    private String manufacturerName;
    
    private ZonedDateTime dateCreated;
    
    private ZonedDateTime dateUpdated;
    
}
