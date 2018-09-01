package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "manufacturer")
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerDO {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long manufacturerId;

	    @Column(nullable = false, name = "manufacturer_name")
	    private String manufacturerName;
	    
	    @Column(nullable = false, name = "date_created")
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	    @Builder.Default
	    private ZonedDateTime dateCreated = ZonedDateTime.now();
	    
	    @Column(nullable = false, name = "date_updated")
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	    @Builder.Default
	    private ZonedDateTime dateUpdated = ZonedDateTime.now();
	    
}
