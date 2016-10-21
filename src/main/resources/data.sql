INSERT INTO MockResponse
(Id, ResponsePayload, HttpCode, UriToMatch, ContentType, MethodToMatch)
VALUES
(1, '{"value":"Here I am"}', 200, '/any/url', 'application/json', 'GET');