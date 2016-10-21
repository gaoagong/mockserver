-- MAIN Database schema
SET MODE Oracle;

DROP TABLE IF EXISTS MockResponse;
CREATE TABLE MockResponse (
  Id                  NUMERIC(38,0)     NOT NULL,
  ResponsePayload     VARCHAR(4000),
  HttpCode            NUMERIC(3, 0)     NOT NULL,
  UriToMatch          VARCHAR(1000)     NOT NULL,
  ContentType         VARCHAR(64),
  MethodToMatch       VARCHAR(8)        NOT NULL,
  Status              CHAR(1)           DEFAULT 'A' NOT NULL,
  InsTime             TIMESTAMP         DEFAULT CURRENT_TIMESTAMP NOT NULL,
  UpdTime             TIMESTAMP         DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY(ID)
);
