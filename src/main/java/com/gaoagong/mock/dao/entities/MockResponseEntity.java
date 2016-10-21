package com.gaoagong.mock.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MockResponse")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MockResponseEntity implements Serializable {
    @Column(name = "Id")
    @Id
    private long id;

    @Column(name = "ResponsePayload")
    private String responsePayload;

    @Column(name = "HttpCode")
    private int httpCode;

    @Column(name = "UriToMatch")
    private String uriToMatch;

    @Column(name = "ContentType")
    private String contentType;

    @Column(name = "MethodToMatch")
    private String methodToMatch;

    @Column(name = "Status")
    private String status;

}