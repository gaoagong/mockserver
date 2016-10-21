package com.gaoagong.mock.service.api;

import javax.servlet.http.HttpServletRequest;

/**
 * Service to look up mock handlers and find the correct or highest precedence one to handler the mock.
 */
public interface MockHandlerFinder {

    /**
     * Given a request, we need to figure out which mock response handler can best fulfill the request.
     * @param request request uri
     */
    MockHandler findMockHandler(HttpServletRequest request);
}
