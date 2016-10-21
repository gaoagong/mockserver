package com.gaoagong.mock.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
 * Model representing a simple response. You give me a URI and I give you a response payload with an http code and
 * content type.
 */
@Data
@Builder
@JsonDeserialize(builder = MockResponse.MockResponseBuilder.class)
public class MockResponse {

    private long id;
    private int httpCode;
    private String uriToMatch;
    private String methodToMatch;
    private String status;
    private String responsePayload;
    private String contentType;
    private Map<String, String> responseHeaders;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MockResponseBuilder {

    }

}
