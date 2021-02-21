package qub;

public interface TrackDetailFieldsTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackDetailFields.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackDetailFields trackDetail = TrackDetailFields.create();
                test.assertNotNull(trackDetail);
                test.assertEqual(
                    XMLElement.create(TrackDetail.elementName),
                    trackDetail.toXml());
                test.assertEqual("", trackDetail.getEventDate());
                test.assertEqual("", trackDetail.getEventTime());
                test.assertEqual("", trackDetail.getEvent());
                test.assertEqual("", trackDetail.getEventCity());
                test.assertEqual("", trackDetail.getEventState());
                test.assertEqual("", trackDetail.getEventZipCode());
                test.assertEqual("", trackDetail.getEventCountry());
                test.assertEqual("", trackDetail.getFirmName());
                test.assertEqual("", trackDetail.getName());
                test.assertEqual("", trackDetail.getAuthorizedAgent());
                test.assertEqual("", trackDetail.getDeliveryAttributeCode());
            });

            runner.testGroup("create(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackDetailFields.create(xml),
                            expected);
                    });
                };

                createErrorTest.run(
                    null,
                    new PreConditionFailure("xml cannot be null."));
                createErrorTest.run(
                    XMLElement.create("a"),
                    new PreConditionFailure("xml.getName() (a) must be TrackDetail."));

                runner.test("with empty XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackDetail");
                    final TrackDetailFields trackDetail = TrackDetailFields.create(xml);
                    test.assertNotNull(trackDetail);
                    test.assertEqual(
                        xml,
                        trackDetail.toXml());
                    test.assertEqual("", trackDetail.getEventDate());
                    test.assertEqual("", trackDetail.getEventTime());
                    test.assertEqual("", trackDetail.getEvent());
                    test.assertEqual("", trackDetail.getEventCity());
                    test.assertEqual("", trackDetail.getEventState());
                    test.assertEqual("", trackDetail.getEventZipCode());
                    test.assertEqual("", trackDetail.getEventCountry());
                    test.assertEqual("", trackDetail.getFirmName());
                    test.assertEqual("", trackDetail.getName());
                    test.assertEqual("", trackDetail.getAuthorizedAgent());
                    test.assertEqual("", trackDetail.getDeliveryAttributeCode());
                });

                runner.test("with full XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackDetail")
                        .addChild(XMLElement.create("EventDate")
                            .addText("a"))
                        .addChild(XMLElement.create("EventTime")
                            .addText("b"))
                        .addChild(XMLElement.create("Event")
                            .addText("c"))
                        .addChild(XMLElement.create("EventCity")
                            .addText("d"))
                        .addChild(XMLElement.create("EventState")
                            .addText("e"))
                        .addChild(XMLElement.create("EventZIPCode")
                            .addText("f"))
                        .addChild(XMLElement.create("EventCountry")
                            .addText("g"))
                        .addChild(XMLElement.create("FirmName")
                            .addText("h"))
                        .addChild(XMLElement.create("Name")
                            .addText("i"))
                        .addChild(XMLElement.create("AuthorizedAgent")
                            .addText("j"))
                        .addChild(XMLElement.create("DeliveryAttributeCode")
                            .addText("k"));
                    final TrackDetailFields trackDetail = TrackDetailFields.create(xml);
                    test.assertNotNull(trackDetail);
                    test.assertEqual(
                        xml,
                        trackDetail.toXml());
                    test.assertEqual("a", trackDetail.getEventDate());
                    test.assertEqual("b", trackDetail.getEventTime());
                    test.assertEqual("c", trackDetail.getEvent());
                    test.assertEqual("d", trackDetail.getEventCity());
                    test.assertEqual("e", trackDetail.getEventState());
                    test.assertEqual("f", trackDetail.getEventZipCode());
                    test.assertEqual("g", trackDetail.getEventCountry());
                    test.assertEqual("h", trackDetail.getFirmName());
                    test.assertEqual("i", trackDetail.getName());
                    test.assertEqual("j", trackDetail.getAuthorizedAgent());
                    test.assertEqual("k", trackDetail.getDeliveryAttributeCode());
                });
            });

            runner.testGroup("setEventTime(String)", () ->
            {
                final Action2<String,Throwable> setEventTimeErrorTest = (String eventTime, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventTime), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEventTime(eventTime), expected);
                        test.assertEqual("", trackDetail.getEventTime());
                    });
                };

                setEventTimeErrorTest.run(null, new PreConditionFailure("eventTime cannot be null."));

                final Action1<String> setEventTimeTest = (String eventTime) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventTime), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventTimeResult = trackDetail.setEventTime(eventTime);
                        test.assertSame(trackDetail, setEventTimeResult);
                        test.assertEqual(eventTime, trackDetail.getEventTime());
                    });
                };

                setEventTimeTest.run("");
                setEventTimeTest.run("a");
            });

            runner.testGroup("setEventDate(String)", () ->
            {
                final Action2<String,Throwable> setEventDateErrorTest = (String eventDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventDate), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEventDate(eventDate), expected);
                        test.assertEqual("", trackDetail.getEventDate());
                    });
                };

                setEventDateErrorTest.run(null, new PreConditionFailure("eventDate cannot be null."));

                final Action1<String> setEventDateTest = (String eventDate) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventDate), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventDateResult = trackDetail.setEventDate(eventDate);
                        test.assertSame(trackDetail, setEventDateResult);
                        test.assertEqual(eventDate, trackDetail.getEventDate());
                    });
                };

                setEventDateTest.run("");
                setEventDateTest.run("a");
            });

            runner.testGroup("setEvent(String)", () ->
            {
                final Action2<String,Throwable> setEventErrorTest = (String event, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(event), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEvent(event), expected);
                        test.assertEqual("", trackDetail.getEvent());
                    });
                };

                setEventErrorTest.run(null, new PreConditionFailure("event cannot be null."));

                final Action1<String> setEventTest = (String event) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(event), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventResult = trackDetail.setEvent(event);
                        test.assertSame(trackDetail, setEventResult);
                        test.assertEqual(event, trackDetail.getEvent());
                    });
                };

                setEventTest.run("");
                setEventTest.run("a");
            });

            runner.testGroup("setEventCity(String)", () ->
            {
                final Action2<String,Throwable> setEventCityErrorTest = (String eventCity, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventCity), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEventCity(eventCity), expected);
                        test.assertEqual("", trackDetail.getEventCity());
                    });
                };

                setEventCityErrorTest.run(null, new PreConditionFailure("eventCity cannot be null."));

                final Action1<String> setEventCityTest = (String eventCity) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventCity), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventCityResult = trackDetail.setEventCity(eventCity);
                        test.assertSame(trackDetail, setEventCityResult);
                        test.assertEqual(eventCity, trackDetail.getEventCity());
                    });
                };

                setEventCityTest.run("");
                setEventCityTest.run("a");
            });

            runner.testGroup("setEventState(String)", () ->
            {
                final Action2<String,Throwable> setEventStateErrorTest = (String eventState, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventState), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEventState(eventState), expected);
                        test.assertEqual("", trackDetail.getEventState());
                    });
                };

                setEventStateErrorTest.run(null, new PreConditionFailure("eventState cannot be null."));

                final Action1<String> setEventStateTest = (String eventState) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventState), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventStateResult = trackDetail.setEventState(eventState);
                        test.assertSame(trackDetail, setEventStateResult);
                        test.assertEqual(eventState, trackDetail.getEventState());
                    });
                };

                setEventStateTest.run("");
                setEventStateTest.run("a");
            });

            runner.testGroup("setEventZipCode(String)", () ->
            {
                final Action2<String,Throwable> setEventZipCodeErrorTest = (String eventZipCode, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventZipCode), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEventZipCode(eventZipCode), expected);
                        test.assertEqual("", trackDetail.getEventZipCode());
                    });
                };

                setEventZipCodeErrorTest.run(null, new PreConditionFailure("eventZipCode cannot be null."));

                final Action1<String> setEventZipCodeTest = (String eventZipCode) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventZipCode), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventZipCodeResult = trackDetail.setEventZipCode(eventZipCode);
                        test.assertSame(trackDetail, setEventZipCodeResult);
                        test.assertEqual(eventZipCode, trackDetail.getEventZipCode());
                    });
                };

                setEventZipCodeTest.run("");
                setEventZipCodeTest.run("a");
            });

            runner.testGroup("setEventCountry(String)", () ->
            {
                final Action2<String,Throwable> setEventCountryErrorTest = (String eventZipCode, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventZipCode), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setEventCountry(eventZipCode), expected);
                        test.assertEqual("", trackDetail.getEventCountry());
                    });
                };

                setEventCountryErrorTest.run(null, new PreConditionFailure("eventCountry cannot be null."));

                final Action1<String> setEventCountryTest = (String eventCountry) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(eventCountry), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setEventCountryResult = trackDetail.setEventCountry(eventCountry);
                        test.assertSame(trackDetail, setEventCountryResult);
                        test.assertEqual(eventCountry, trackDetail.getEventCountry());
                    });
                };

                setEventCountryTest.run("");
                setEventCountryTest.run("a");
            });

            runner.testGroup("setFirmName(String)", () ->
            {
                final Action2<String,Throwable> setFirmNameErrorTest = (String firmName, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(firmName), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setFirmName(firmName), expected);
                        test.assertEqual("", trackDetail.getFirmName());
                    });
                };

                setFirmNameErrorTest.run(null, new PreConditionFailure("firmName cannot be null."));

                final Action1<String> setFirmNameTest = (String firmName) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(firmName), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setFirmNameResult = trackDetail.setFirmName(firmName);
                        test.assertSame(trackDetail, setFirmNameResult);
                        test.assertEqual(firmName, trackDetail.getFirmName());
                    });
                };

                setFirmNameTest.run("");
                setFirmNameTest.run("a");
            });

            runner.testGroup("setName(String)", () ->
            {
                final Action2<String,Throwable> setNameErrorTest = (String name, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(name), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setName(name), expected);
                        test.assertEqual("", trackDetail.getName());
                    });
                };

                setNameErrorTest.run(null, new PreConditionFailure("name cannot be null."));

                final Action1<String> setNameTest = (String name) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(name), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setNameResult = trackDetail.setName(name);
                        test.assertSame(trackDetail, setNameResult);
                        test.assertEqual(name, trackDetail.getName());
                    });
                };

                setNameTest.run("");
                setNameTest.run("a");
            });

            runner.testGroup("setAuthorizedAgent(String)", () ->
            {
                final Action2<String,Throwable> setAuthorizedAgentErrorTest = (String authorizedAgent, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authorizedAgent), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setAuthorizedAgent(authorizedAgent), expected);
                        test.assertEqual("", trackDetail.getAuthorizedAgent());
                    });
                };

                setAuthorizedAgentErrorTest.run(null, new PreConditionFailure("authorizedAgent cannot be null."));

                final Action1<String> setAuthorizedAgentTest = (String authorizedAgent) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authorizedAgent), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setAuthorizedAgentResult = trackDetail.setAuthorizedAgent(authorizedAgent);
                        test.assertSame(trackDetail, setAuthorizedAgentResult);
                        test.assertEqual(authorizedAgent, trackDetail.getAuthorizedAgent());
                    });
                };

                setAuthorizedAgentTest.run("");
                setAuthorizedAgentTest.run("a");
            });

            runner.testGroup("setDeliveryAttributeCode(String)", () ->
            {
                final Action2<String,Throwable> setDeliveryAttributeCodeErrorTest = (String authorizedAgent, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(authorizedAgent), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        test.assertThrows(() -> trackDetail.setDeliveryAttributeCode(authorizedAgent), expected);
                        test.assertEqual("", trackDetail.getDeliveryAttributeCode());
                    });
                };

                setDeliveryAttributeCodeErrorTest.run(null, new PreConditionFailure("deliveryAttributeCode cannot be null."));

                final Action1<String> setDeliveryAttributeCodeTest = (String deliveryAttributeCode) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(deliveryAttributeCode), (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetailFields.create();
                        final TrackDetailFields setDeliveryAttributeCodeResult = trackDetail.setDeliveryAttributeCode(deliveryAttributeCode);
                        test.assertSame(trackDetail, setDeliveryAttributeCodeResult);
                        test.assertEqual(deliveryAttributeCode, trackDetail.getDeliveryAttributeCode());
                    });
                };

                setDeliveryAttributeCodeTest.run("");
                setDeliveryAttributeCodeTest.run("a");
            });
        });
    }
}
