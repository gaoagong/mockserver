package com.gaoagong.mock.service.impl;

import com.gaoagong.mock.dao.api.MockResponseDatasource;
import com.gaoagong.mock.model.MockResponse;
import com.gaoagong.mock.service.api.MockHandler;
import com.gaoagong.mock.service.api.MockHandlerFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Default handler finder service. Given a request it searches for the most pertinent mock response handler.
 */
@Service
public class MockHandlerFinderService implements MockHandlerFinder {

    private MockResponseDatasource mockResponseDatasource;

    @Autowired
    public MockHandlerFinderService(MockResponseDatasource mockResponseDatasource) {
        this.mockResponseDatasource = mockResponseDatasource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MockHandler findMockHandler(HttpServletRequest request) {
        final MockResponse mockResponse =
            mockResponseDatasource.findExactUriMatchResponseHandler(request.getRequestURI(), request.getMethod());
        return new SimpleMockHandler(mockResponse);
    }
}
