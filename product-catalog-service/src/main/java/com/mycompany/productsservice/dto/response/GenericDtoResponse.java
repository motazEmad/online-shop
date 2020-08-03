package com.mycompany.productsservice.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
public class GenericDtoResponse<T> {
	
	private String statusCode;
	private String statusDesc;
	private List<? extends T> data;

}
