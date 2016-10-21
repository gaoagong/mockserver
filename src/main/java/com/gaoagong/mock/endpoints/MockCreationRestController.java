package com.gaoagong.mock.endpoints;

import com.gaoagong.mock.dao.api.MockResponseDatasource;
import com.gaoagong.mock.dao.entities.MockResponseEntity;
import com.gaoagong.mock.dao.repository.MockResponseRepository;
import com.gaoagong.mock.exceptions.ResourceNotFoundException;
import com.gaoagong.mock.model.MockResponse;
import com.gaoagong.mock.model.MockResponseSearchCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for creating, updating, deleting, etc mocks.
 */
@RequestMapping("/mocks")
@RestController
public class MockCreationRestController {

    private MockResponseRepository mockResponseRepository;
    private MockResponseDatasource mockResponseDatasource;

    @Autowired
    public MockCreationRestController(final MockResponseRepository mockResponseRepository,
                                      final MockResponseDatasource mockResponseDatasource) {
        this.mockResponseRepository = mockResponseRepository;
        this.mockResponseDatasource = mockResponseDatasource;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MockResponse getMockResponse(@PathVariable final long id) {
        final MockResponseEntity entity = mockResponseRepository.findOne(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Could not find resource with id=" + id);
        }
        return adaptMockResponseEntity(entity);
    }

    @RequestMapping(method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MockResponse> getMockResponses(@RequestBody final MockResponseSearchCriteria searchCriteria) {
        return mockResponseDatasource.findByCriteria(searchCriteria);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MockResponse updateMockResponse(@PathVariable final long id,
                                           @RequestBody final MockResponse request) {
        request.setId(id);
        return adaptMockResponseEntity(mockResponseRepository.save(adaptMockResponseToEntity(request)));
    }

    @RequestMapping(method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MockResponse createMockResponse(@RequestBody final MockResponse request) {
        return adaptMockResponseEntity(mockResponseRepository.save(adaptMockResponseToEntity(request)));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void createMockResponse(@PathVariable final long id) {
        mockResponseRepository.delete(id);
    }

    private MockResponse adaptMockResponseEntity(final MockResponseEntity entity) {
        return MockResponse.builder()
            .httpCode(entity.getHttpCode())
            .responsePayload(entity.getResponsePayload())
            .contentType(entity.getContentType())
            .id(entity.getId())
            .methodToMatch(entity.getMethodToMatch())
            .uriToMatch(entity.getUriToMatch())
            .status(entity.getStatus())
//            .responseHeaders(entity.getResponseHeaders())
            .build();
    }

    private MockResponseEntity adaptMockResponseToEntity(final MockResponse entity) {
        return MockResponseEntity.builder()
            .httpCode(entity.getHttpCode())
            .responsePayload(entity.getResponsePayload())
            .contentType(entity.getContentType())
            .id(entity.getId())
            .methodToMatch(entity.getMethodToMatch())
            .uriToMatch(entity.getUriToMatch())
            .status(entity.getStatus())
//            .responseHeaders(entity.getResponseHeaders())
            .build();
    }

}
