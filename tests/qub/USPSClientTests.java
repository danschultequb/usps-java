package qub;

public interface USPSClientTests
{
    static void test(TestRunner runner, Function1<Test,? extends USPSClient> creator)
    {
        USPSClientTests.test(runner, null, creator);
    }

    static void test(TestRunner runner, Skip skip, Function1<Test,? extends USPSClient> creator)
    {
        runner.testGroup(USPSClient.class, skip, () ->
        {
            runner.testGroup("create(HttpClient)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> USPSClient.create(null),
                        new PreConditionFailure("httpClient cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final HttpClient httpClient = HttpClient.create(test.getNetwork());
                    final RealUSPSClient client = USPSClient.create(httpClient);
                    test.assertNotNull(client);
                });
            });

            runner.testGroup("trackText(String)", () ->
            {
                final Action2<String,Throwable> trackTextErrorTest = (String trackingId, Throwable expected) ->
                {
                    runner.test("with " + trackingId, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertThrows(() -> client.trackText(trackingId).await(),
                            expected);
                    });
                };

                trackTextErrorTest.run(
                    null,
                    new PreConditionFailure("trackingId cannot be null."));

                trackTextErrorTest.run(
                    "",
                    new PreConditionFailure("trackingId cannot be empty."));

                final Action2<String,TrackResponseText> trackTextTest = (String trackingId, TrackResponseText expected) ->
                {
                    runner.test("with " + trackingId, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertEqual(expected, client.trackText(trackingId).await());
                    });
                };

                trackTextTest.run(
                    "helloworld",
                    TrackResponse.createText()
                        .addTrackInfo(TrackInfo.createText()
                            .setId("helloworld")
                            .setSummary("The Postal Service could not locate the tracking information for your request. Please verify your tracking number and try again later.")));

                trackTextTest.run(
                    "EJ958088694US",
                    TrackResponse.createText()
                        .addTrackInfo(TrackInfo.createText()
                            .setId("EJ958088694US")
                            .addError(Error.create()
                                .setNumber("-2147219283")
                                .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    "9405511899564158800673",
                    TrackResponse.createText()
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
                            .addDetail("Shipping Label Created, USPS Awaiting Item, November 19, 2020, 8:19 am, ONTARIO, CA 91764")));
            });

            runner.testGroup("trackText(Iterable<String>)", () ->
            {
                final Action2<Iterable<String>,Throwable> trackTextErrorTest = (Iterable<String> trackingIds, Throwable expected) ->
                {
                    runner.test("with " + trackingIds, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertThrows(() -> client.trackText(trackingIds).await(),
                            expected);
                    });
                };

                trackTextErrorTest.run(
                    null,
                    new PreConditionFailure("trackingIds cannot be null."));

                trackTextErrorTest.run(
                    Iterable.create(),
                    new PreConditionFailure("trackingIds cannot be empty."));

                final Action2<Iterable<String>,TrackResponseText> trackTextTest = (Iterable<String> trackingIds, TrackResponseText expected) ->
                {
                    runner.test("with " + trackingIds, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertEqual(expected, client.trackText(trackingIds).await());
                    });
                };

                trackTextTest.run(
                    Iterable.create("helloworld"),
                    TrackResponse.createText()
                        .addTrackInfo(TrackInfo.createText()
                            .setId("helloworld")
                            .setSummary("The Postal Service could not locate the tracking information for your request. Please verify your tracking number and try again later.")));

                trackTextTest.run(
                    Iterable.create("EJ958088694US"),
                    TrackResponse.createText()
                        .addTrackInfo(TrackInfo.createText()
                            .setId("EJ958088694US")
                            .addError(Error.create()
                                .setNumber("-2147219283")
                                .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    Iterable.create("9405511899564158800673"),
                    TrackResponse.createText()
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
                            .addDetail("Shipping Label Created, USPS Awaiting Item, November 19, 2020, 8:19 am, ONTARIO, CA 91764")));
            });

            runner.testGroup("trackText(TrackRequest)", () ->
            {
                final Action2<TrackRequest,Throwable> trackTextErrorTest = (TrackRequest trackRequest, Throwable expected) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertThrows(() -> client.trackText(trackRequest).await(),
                            expected);
                    });
                };

                trackTextErrorTest.run(
                    null,
                    new PreConditionFailure("trackRequest cannot be null."));

                trackTextErrorTest.run(
                    TrackRequest.create(),
                    new PreConditionFailure("trackRequest.getTrackIds() cannot be empty."));

                final Action2<TrackRequest,TrackResponseText> trackTextTest = (TrackRequest trackRequest, TrackResponseText expected) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertEqual(expected, client.trackText(trackRequest).await());
                    });
                };

                trackTextTest.run(
                    TrackRequest.create()
                        .addTrackId("helloworld"),
                    TrackResponse.createText()
                        .addTrackInfo(TrackInfo.createText()
                            .setId("helloworld")
                            .setSummary("The Postal Service could not locate the tracking information for your request. Please verify your tracking number and try again later.")));

                trackTextTest.run(
                    TrackRequest.create()
                        .addTrackId("EJ958088694US"),
                    TrackResponse.createText()
                        .addTrackInfo(TrackInfo.createText()
                            .setId("EJ958088694US")
                            .addError(Error.create()
                                .setNumber("-2147219283")
                                .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    TrackRequest.create()
                        .addTrackId("9405511899564158800673"),
                    TrackResponse.createText()
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
                            .addDetail("Shipping Label Created, USPS Awaiting Item, November 19, 2020, 8:19 am, ONTARIO, CA 91764")));
            });

            runner.testGroup("trackFields(String)", () ->
            {
                final Action2<String,Throwable> trackFieldErrorTest = (String trackingId, Throwable expected) ->
                {
                    runner.test("with " + trackingId, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertThrows(() -> client.trackField(trackingId).await(),
                            expected);
                    });
                };

                trackFieldErrorTest.run(
                    null,
                    new PreConditionFailure("trackingId cannot be null."));

                trackFieldErrorTest.run(
                    "",
                    new PreConditionFailure("trackingId cannot be empty."));

                final Action2<String,TrackResponseFields> trackTextTest = (String trackingId, TrackResponseFields expected) ->
                {
                    runner.test("with " + trackingId, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertEqual(expected, client.trackField(trackingId).await());
                    });
                };

                trackTextTest.run(
                    "helloworld",
                    TrackResponse.createFields()
                        .addTrackInfo(TrackInfo.createFields()
                            .setId("helloworld")
                            .addError(Error.create()
                                .setNumber("-2147219302")
                                .setDescription("The tracking number may be incorrect or the status update is not yet available. Please verify your tracking number and try again later.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    "EJ958088694US",
                    TrackResponse.createFields()
                        .addTrackInfo(TrackInfo.createFields()
                            .setId("EJ958088694US")
                            .addError(Error.create()
                                .setNumber("-2147219283")
                                .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    "9405511899564158800673",
                    TrackResponse.createFields()
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
                                .setDeliveryAttributeCode("33"))));
            });

            runner.testGroup("trackFields(Iterable<String>)", () ->
            {
                final Action2<Iterable<String>,Throwable> trackFieldErrorTest = (Iterable<String> trackingIds, Throwable expected) ->
                {
                    runner.test("with " + trackingIds, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertThrows(() -> client.trackField(trackingIds).await(),
                            expected);
                    });
                };

                trackFieldErrorTest.run(
                    null,
                    new PreConditionFailure("trackingIds cannot be null."));

                trackFieldErrorTest.run(
                    Iterable.create(),
                    new PreConditionFailure("trackingIds cannot be empty."));

                final Action2<Iterable<String>,TrackResponseFields> trackTextTest = (Iterable<String> trackingIds, TrackResponseFields expected) ->
                {
                    runner.test("with " + trackingIds, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertEqual(expected, client.trackField(trackingIds).await());
                    });
                };

                trackTextTest.run(
                    Iterable.create("helloworld"),
                    TrackResponse.createFields()
                        .addTrackInfo(TrackInfo.createFields()
                            .setId("helloworld")
                            .addError(Error.create()
                                .setNumber("-2147219302")
                                .setDescription("The tracking number may be incorrect or the status update is not yet available. Please verify your tracking number and try again later.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    Iterable.create("EJ958088694US"),
                    TrackResponse.createFields()
                        .addTrackInfo(TrackInfo.createFields()
                            .setId("EJ958088694US")
                            .addError(Error.create()
                                .setNumber("-2147219283")
                                .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    Iterable.create("9405511899564158800673"),
                    TrackResponse.createFields()
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
                                .setDeliveryAttributeCode("33"))));
            });

            runner.testGroup("trackFields(TrackFieldRequest)", () ->
            {
                final Action2<TrackFieldRequest,Throwable> trackFieldErrorTest = (TrackFieldRequest trackRequest, Throwable expected) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertThrows(() -> client.trackField(trackRequest).await(),
                            expected);
                    });
                };

                trackFieldErrorTest.run(
                    null,
                    new PreConditionFailure("trackRequest cannot be null."));

                trackFieldErrorTest.run(
                    TrackFieldRequest.create(),
                    new PreConditionFailure("trackRequest.getTrackIds() cannot be empty."));

                final Action2<TrackFieldRequest,TrackResponseFields> trackTextTest = (TrackFieldRequest trackRequest, TrackResponseFields expected) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final USPSClient client = creator.run(test);
                        test.assertEqual(expected, client.trackField(trackRequest).await());
                    });
                };

                trackTextTest.run(
                    TrackFieldRequest.create()
                        .addTrackId("helloworld"),
                    TrackResponse.createFields()
                        .addTrackInfo(TrackInfo.createFields()
                            .setId("helloworld")
                            .addError(Error.create()
                                .setNumber("-2147219302")
                                .setDescription("The tracking number may be incorrect or the status update is not yet available. Please verify your tracking number and try again later.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    TrackFieldRequest.create()
                        .addTrackId("EJ958088694US"),
                    TrackResponse.createFields()
                        .addTrackInfo(TrackInfo.createFields()
                            .setId("EJ958088694US")
                            .addError(Error.create()
                                .setNumber("-2147219283")
                                .setDescription("A status update is not yet available on your package. It will be available when the shipper provides an update or the package is delivered to USPS. Check back soon. Sign up for Informed Delivery&lt;SUP>&amp;reg;&lt;/SUP> to receive notifications for packages addressed to you.")
                                .addHelpFile()
                                .addHelpContext())));

                trackTextTest.run(
                    TrackFieldRequest.create()
                        .addTrackId("9405511899564158800673"),
                    TrackResponse.createFields()
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
                                .setDeliveryAttributeCode("33"))));
            });
        });
    }
}
