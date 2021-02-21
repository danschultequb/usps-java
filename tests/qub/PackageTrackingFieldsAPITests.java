package qub;

public interface PackageTrackingFieldsAPITests
{
    static void test(TestRunner runner)
    {
        // https://www.usps.com/business/web-tools-apis/track-and-confirm-api_files/track-and-confirm-api.htm#_Toc41911512

        runner.testGroup("Package Tracking Fields API", runner.skip(RealUSPSClientTests.skipTests), () ->
        {
            runner.test("with empty TrackFieldRequest element", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create());
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(215L, httpResponse.getContentLength().await());
                    test.assertEqual("text/xml", httpResponse.getHeaderValue("Content-Type").await());

                    final XMLDocument responseXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(responseXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B1A")
                                .setDescription("API Authorization failure. TrackV2 is not a valid API name for this protocol.")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        responseXml);
                }
            });

            runner.test("with empty USERID attribute and no TrackID elements", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create()
                        .setUserId(""));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(215L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B19")
                                .setDescription("XML Syntax Error: Please check the XML request to see if it can be parsed.(B)")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with invalid USERID attribute and no TrackID elements", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create()
                        .setUserId("fakeuserid"));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(208L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B1A")
                                .setDescription("Authorization failure.  Perhaps username and/or password is incorrect.")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with valid USERID attribute and no TrackID elements", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create()
                        .setUserId(RealUSPSClientTests.userId));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(332L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("-2147221301")
                                .setSource("clsTrack:getTrackInfo2")
                                .setDescription("The element 'TrackFieldRequest' has incomplete content. List of possible elements expected: 'Revision, ClientIp, SourceId, SourceIdZIP, TestLanguage, TrackID'.")
                                .addHelpFile()
                                .addHelpContext()
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with valid USERID attribute and one invalid (helloworld) TrackID element", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create()
                        .setUserId(RealUSPSClientTests.userId)
                        .addTrackId("helloworld"));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(339L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(TrackResponse.createFields()
                                .addTrackInfo(TrackInfo.createFields()
                                    .setId("helloworld")
                                    .addError(Error.create()
                                        .setNumber("-2147219302")
                                        .setDescription("The tracking number may be incorrect or the status update is not yet available. Please verify your tracking number and try again later.")
                                        .addHelpFile()
                                        .addHelpContext()))
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with valid USERID attribute and one valid TrackID element with no status update", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create()
                        .setUserId(RealUSPSClientTests.userId)
                        .addTrackId("EJ958088694US"));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(483L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                        .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                        .setRoot(TrackResponse.createFields()
                            .addTrackInfo(TrackInfo.createFields()
                                .setId("EJ958088694US")
                                .addError(Error.create()
                                    .setNumber("-2147219283")
                                    .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                    .addHelpFile()
                                    .addHelpContext()))
                            .toXml()),
                        bodyXml);
                }
            });

            runner.test("with valid USERID attribute and one valid TrackID element", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackFieldRequest.create()
                        .setUserId(RealUSPSClientTests.userId)
                        .addTrackId("9405511899564158800673"));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(3554L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                        .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                        .setRoot(TrackResponse.createFields()
                            .addTrackInfo(TrackInfo.createFields()
                                .setId("9405511899564158800673")
                                .addSummary(TrackSummary.createFields()
                                    .setEventTime("6:42 pm")
                                    .setEventDate("November 24, 2020")
                                    .setEvent("Delivered, Front Desk/Reception/Mail Room")
                                    .setEventCity("ONTARIO")
                                    .setEventState("CA")
                                    .setEventZipCode("91761")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false")
                                    .setDeliveryAttributeCode("05"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("10:18 am")
                                    .setEventDate("November 24, 2020")
                                    .setEvent("Arrived at Post Office")
                                    .setEventCity("ONTARIO")
                                    .setEventState("CA")
                                    .setEventZipCode("91762")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("7:10 am")
                                    .setEventDate("November 24, 2020")
                                    .setEvent("Out for Delivery")
                                    .setEventCity("ONTARIO")
                                    .setEventState("CA")
                                    .setEventZipCode("91764")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("12:46 pm")
                                    .setEventDate("November 23, 2020")
                                    .setEvent("Arrived at USPS Facility")
                                    .setEventCity("ONTARIO")
                                    .setEventState("CA")
                                    .setEventZipCode("91761")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("11:59 am")
                                    .setEventDate("November 23, 2020")
                                    .setEvent("Departed USPS Regional Facility")
                                    .setEventCity("CITY OF INDUSTRY CA DISTRIBUTION CENTER")
                                    .setEventState("")
                                    .setEventZipCode("")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("6:01 am")
                                    .setEventDate("November 23, 2020")
                                    .setEvent("Arrived at USPS Regional Facility")
                                    .setEventCity("CITY OF INDUSTRY CA DISTRIBUTION CENTER")
                                    .setEventState("")
                                    .setEventZipCode("")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("4:07 am")
                                    .setEventDate("November 22, 2020")
                                    .setEvent("Departed USPS Facility")
                                    .setEventCity("KENT")
                                    .setEventState("WA")
                                    .setEventZipCode("98035")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("11:44 pm")
                                    .setEventDate("November 21, 2020")
                                    .setEvent("Arrived at USPS Origin Facility")
                                    .setEventCity("KENT")
                                    .setEventState("WA")
                                    .setEventZipCode("98035")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("5:56 pm")
                                    .setEventDate("November 21, 2020")
                                    .setEvent("Departed Post Office")
                                    .setEventCity("BOTHELL")
                                    .setEventState("WA")
                                    .setEventZipCode("98011")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("5:03 pm")
                                    .setEventDate("November 21, 2020")
                                    .setEvent("USPS picked up item")
                                    .setEventCity("BOTHELL")
                                    .setEventState("WA")
                                    .setEventZipCode("98011")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false"))
                                .addDetail(TrackDetail.createFields()
                                    .setEventTime("8:19 am")
                                    .setEventDate("November 19, 2020")
                                    .setEvent("Shipping Label Created, USPS Awaiting Item")
                                    .setEventCity("ONTARIO")
                                    .setEventState("CA")
                                    .setEventZipCode("91764")
                                    .setEventCountry("")
                                    .setFirmName("")
                                    .setName("")
                                    .setAuthorizedAgent("false")
                                    .setDeliveryAttributeCode("33")))
                            .toXml()),
                        bodyXml);
                }
            });
        });
    }
}
