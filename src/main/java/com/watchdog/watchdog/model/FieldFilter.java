package com.watchdog.watchdog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldFilter {
	private String fieldName;
	private String fieldValue;
}