package com.gaoagong.mock.dao.api;

import com.gaoagong.mock.model.MockResponse;
import com.gaoagong.mock.model.MockResponseSearchCriteria;

import java.util.List;

/**
 * Defines a generic set of db methods for any datasource.
 */
public interface MockResponseDatasource {

    MockResponse findExactUriMatchResponseHandler(String requestURI, String httpMethod);

    List<MockResponse> findByCriteria(MockResponseSearchCriteria searchCriteria);
}
