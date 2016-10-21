package com.gaoagong.mock.service.api;

import org.springframework.http.ResponseEntity;

/**
 * Handler that knows how to fulfill a mock request with some kind of response.
 */
public interface MockHandler {

    ResponseEntity<String> getResponseEntity();
}
