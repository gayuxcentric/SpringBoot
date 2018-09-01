package com.mytaxi.datatransferobject;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {

	private Long manufacturerId;
    private String manufacturerName;
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    private ZonedDateTime dateUpdated = ZonedDateTime.now();

}
