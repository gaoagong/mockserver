package com.gaoagong.mock.dao.impl;

import com.gaoagong.mock.dao.api.MockResponseDatasource;
import com.gaoagong.mock.model.MockResponse;
import com.gaoagong.mock.model.MockResponseSearchCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Datasource services with an Oracle implementation. Queries, etc must support Oracle syntax.
 */
@Slf4j
@Service
public class OracleMockResponseDatasource implements MockResponseDatasource {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OracleMockResponseDatasource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public MockResponse findExactUriMatchResponseHandler(final String requestURI,
                                                         final String httpMethod) {
        final Map<String, Object> paramSource = new HashMap<>();
        paramSource.put("uri", requestURI);
        paramSource.put("method", httpMethod);
        return namedJdbcTemplate.queryForObject(
            FIND_MOCK_RESPONSE_WITH_MATCHING_URI,
            paramSource,
            (rs, rowNum) -> MockResponse.builder()
                .contentType(rs.getString("ContentType"))
                .httpCode(rs.getInt("HttpCode"))
                .responsePayload(rs.getString("ResponsePayload"))
                .build());
    }

    @Override
    public List<MockResponse> findByCriteria(MockResponseSearchCriteria searchCriteria) {
        final Map<String, Object> params = new HashMap<>();
        final String sql = buildSearchCriteriaSql(searchCriteria, params);

        return namedJdbcTemplate.query(sql, params, new RowMapper<MockResponse>() {
            @Override
            public MockResponse mapRow(ResultSet resultSet, int i) throws SQLException {
                return null;
            }
        });
    }

    private String buildSearchCriteriaSql(MockResponseSearchCriteria searchCriteria, Map<String, Object> params) {
        final StringBuilder sql = new StringBuilder("select * from MockResponse");

        boolean whereAdded = false;

        final String httpMethod = searchCriteria.getHttpMethod();
        if (httpMethod != null && !httpMethod.isEmpty()) {
            whereAdded = true;
            sql.append(" WHERE HttpMethod = :method");
            params.put("method", httpMethod);
        }

        final String uriPart = searchCriteria.getUriPart();
        if (uriPart != null && !uriPart.isEmpty()) {
            if (whereAdded) {
                sql.append(" AND UriToMatch LIKE :uri");
            } else {
                sql.append(" WHERE UriToMatch LIKE :uri");
            }
            params.put("uri", "%" + uriPart.replaceAll("%", "") + "%");
        }
        return sql.toString();
    }

    private static final String FIND_MOCK_RESPONSE_WITH_MATCHING_URI =
        "SELECT ResponsePayload, HttpCode, ContentType " +
        "FROM MockResponse " +
        "WHERE UriToMatch = :uri " +
        "   AND MethodToMatch = :method " +
        "   AND Status = 'A'";

}