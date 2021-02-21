package qub;

public interface PackageTrackAPITests
{
    static void test(TestRunner runner)
    {
        // https://www.usps.com/business/web-tools-apis/track-and-confirm-api_files/track-and-confirm-api.htm#_Toc41911505

        runner.testGroup("Package Track API", runner.skip(RealUSPSClientTests.skipTests), () ->
        {
            runner.test("with no path", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = URL.create()
                    .setScheme("https")
                    .setHost("secure.shippingapis.com");
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(212L, httpResponse.getContentLength().await());
                    test.assertEqual("text/xml", httpResponse.getHeaderValue("Content-Type").await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B19")
                                .setDescription("XML Syntax Error: Please check the XML request to see if it can be parsed.")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with no API or XML query parameter", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = URL.create()
                    .setScheme("https")
                    .setHost("secure.shippingapis.com")
                    .setPath("/ShippingAPI.dll");
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(212L, httpResponse.getContentLength().await());
                    test.assertEqual("text/xml", httpResponse.getHeaderValue("Content-Type").await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B19")
                                .setDescription("XML Syntax Error: Please check the XML request to see if it can be parsed.")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with no XML query parameter", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = URL.create()
                    .setScheme("https")
                    .setHost("secure.shippingapis.com")
                    .setPath("/ShippingAPI.dll")
                    .setQueryParameter("API", "TrackV2");
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(212L, httpResponse.getContentLength().await());
                    test.assertEqual("text/xml", httpResponse.getHeaderValue("Content-Type").await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B19")
                                .setDescription("XML Syntax Error: Please check the XML request to see if it can be parsed.")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with empty string XML query parameter", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = URL.create()
                    .setScheme("https")
                    .setHost("secure.shippingapis.com")
                    .setPath("/ShippingAPI.dll")
                    .setQueryParameter("API", "TrackV2")
                    .setQueryParameter("XML", "");
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(212L, httpResponse.getContentLength().await());
                    test.assertEqual("text/xml", httpResponse.getHeaderValue("Content-Type").await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("80040B19")
                                .setDescription("XML Syntax Error: Please check the XML request to see if it can be parsed.")
                                .setSource("USPSCOM::DoAuth")
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with empty TrackRequest element", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create());
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
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create()
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
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create()
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
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create()
                            .setUserId(RealUSPSClientTests.userId));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(270L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(Error.create()
                                .setNumber("-2147221301")
                                .setSource("clsTrack:getTrackInfo2")
                                .setDescription("The element 'TrackRequest' has incomplete content. List of possible elements expected: 'TrackID'.")
                                .addHelpFile()
                                .addHelpContext()
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with valid USERID attribute and one invalid (helloworld) TrackID element", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create()
                        .setUserId(RealUSPSClientTests.userId)
                        .addTrackId("helloworld"));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(272L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(TrackResponse.createText()
                                .addTrackInfo(TrackInfo.createText()
                                    .setId("helloworld")
                                    .setSummary("The Postal Service could not locate the tracking information for your request. Please verify your tracking number and try again later."))
                                .toXml()),
                        bodyXml);
                }
            });

            runner.test("with valid USERID attribute and one valid TrackID element with no status update", (Test test) ->
            {
                final HttpClient httpClient = HttpClient.create(test.getNetwork());
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create()
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
                            .setRoot(TrackResponse.createText()
                                .addTrackInfo(TrackInfo.createText()
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
                final URL requestUrl = TrackRequestURL.create(TrackRequest.create()
                        .setUserId(RealUSPSClientTests.userId)
                        .addTrackId("9405511899564158800673"));
                try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
                {
                    test.assertNotNull(httpResponse);
                    test.assertEqual(200, httpResponse.getStatusCode());
                    test.assertEqual("OK", httpResponse.getReasonPhrase());
                    test.assertEqual("HTTP/1.0", httpResponse.getHttpVersion());
                    test.assertEqual(1282L, httpResponse.getContentLength().await());

                    final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                    test.assertNotNull(bodyXml);
                    test.assertEqual(
                        XMLDocument.create()
                            .setDeclaration(XMLDeclaration.create().setVersion("1.0").setEncoding("UTF-8"))
                            .setRoot(TrackResponse.createText()
                                .addTrackInfo(TrackInfo.createText()
                                    .setId("9405511899564158800673")
                                    .setSummary("Your item was delivered to the front desk, reception area, or mail room at 6:42 pm on November 24, 2020 in ONTARIO, CA 91761.")
                                    .addDetail("Arrived at Post Office, 11/24/2020, 10:18 am, ONTARIO, CA 91762")
                                    .addDetail("Out for Delivery, 11/24/2020, 7:10 am, ONTARIO, CA 91764")
                                    .addDetail("Arrived at USPS Facility, November 23, 2020, 12:46 pm, ONTARIO, CA 91761")
                                    .addDetail("Departed USPS Regional Facility, 11/23/2020, 11:59 am, CITY OF INDUSTRY CA DISTRIBUTION CENTER")
                                    .addDetail("Arrived at USPS Regional Facility, November 23, 2020, 6:01 am, CITY OF INDUSTRY CA DISTRIBUTION CENTER")
                                    .addDetail("Departed USPS Facility, 11/22/2020, 4:07 am, KENT, WA 98035")
                                    .addDetail("Arrived at USPS Origin Facility, 11/21/2020, 11:44 pm, KENT, WA 98035")
                                    .addDetail("Departed Post Office, November 21, 2020, 5:56 pm, BOTHELL, WA 98011")
                                    .addDetail("USPS picked up item, November 21, 2020, 5:03 pm, BOTHELL, WA 98011")
                                    .addDetail("Shipping Label Created, USPS Awaiting Item, November 19, 2020, 8:19 am, ONTARIO, CA 91764"))
                                .toXml()),
                        bodyXml);
                }
            });
        });
    }
}
