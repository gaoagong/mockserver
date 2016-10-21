package com.gaoagong.mock.endpoints;

import com.gaoagong.mock.service.api.MockHandler;
import com.gaoagong.mock.service.api.MockHandlerFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller that catches all requests and then delegates to a mock. There are admin endpoints for creating new mocks
 * that will NOT go through this endpoint.
 */
@RequestMapping("/**")
@RestController
public class CatchAllRestController {

    private MockHandlerFinder mockHandlerFinder;

    @Autowired
    public CatchAllRestController(MockHandlerFinder mockHandlerFinder) {
        this.mockHandlerFinder = mockHandlerFinder;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity handleRequest(final HttpServletRequest request) {
        final MockHandler mockHandler = mockHandlerFinder.findMockHandler(request);
        return mockHandler.getResponseEntity();
    }

}
