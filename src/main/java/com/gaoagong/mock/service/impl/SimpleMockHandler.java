package com.gaoagong.mock.service.impl;

import com.gaoagong.mock.model.MockResponse;
import com.gaoagong.mock.service.api.MockHandler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * A simple mock handler that returns a hard coded response payload, content type, and http code.
 */
public class SimpleMockHandler implements MockHandler {

    private MockResponse mockResponse;

    public SimpleMockHandler(MockResponse mockResponse) {
        this.mockResponse = mockResponse;
    }

    @Override
    public ResponseEntity<String> getResponseEntity() {
        return ResponseEntity
            .status(mockResponse.getHttpCode())
            .contentType(MediaType.parseMediaType(mockResponse.getContentType()))
            .body(mockResponse.getResponsePayload());
    }
}
