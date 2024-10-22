package com.home.learning.rest.webservices.restful_web_services.filtering.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.home.learning.rest.webservices.restful_web_services.filtering.entity.FilteringBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue getFilteredSingleBean() {
		FilteringBean filteringBean = createFilteringBean("value1", "value2", "value3");

		Set<String> fieldsToShow = Set.of("field1", "field3");

		return applyFilters(filteringBean, fieldsToShow);
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue getFilteredBeanList() {
		List<FilteringBean> filteringBeans = createFilteringBeanList(new FilteringBean("value1", "value2", "value3"),
				new FilteringBean("value1", "value2", "value3"));

		Set<String> fieldsToShow = Set.of("field2", "field3");

		return applyFilters(filteringBeans, fieldsToShow);
	}

	private FilteringBean createFilteringBean(String value1, String value2, String value3) {
		return new FilteringBean(value1, value2, value3);
	}

	private List<FilteringBean> createFilteringBeanList(FilteringBean... beans) {
		return Arrays.asList(beans);
	}

	private MappingJacksonValue applyFilters(Object bean, Set<String> fieldsToShow) {
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToShow);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilteringBeanFilter", filter);

		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
}
