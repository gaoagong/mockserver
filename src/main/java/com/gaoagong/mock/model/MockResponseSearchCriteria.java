package com.gaoagong.mock.model;

import lombok.Builder;
import lombok.Data;

/**
 * Defines some possible search criteria for a mock response.
 */
@Data
@Builder
public class MockResponseSearchCriteria {

    private String httpMethod;
    private String uriPart;

}
