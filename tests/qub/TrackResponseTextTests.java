package qub;

public interface TrackResponseTextTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackResponseText.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackResponseText response = TrackResponseText.create();
                test.assertNotNull(response);
                test.assertEqual(XMLElement.create("TrackResponse"), response.toXml());
                test.assertEqual(Iterable.create(), response.getTrackInfo());
                test.assertEqual(Iterable.create(), response.getErrors());
            });

            runner.testGroup("create(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackResponseText.create(xml), expected);
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackResponse."));

                runner.test("with empty XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackResponse");
                    final TrackResponseText response = TrackResponseText.create(xml);
                    test.assertNotNull(response);
                    test.assertEqual(xml, response.toXml());
                    test.assertEqual(Iterable.create(), response.getTrackInfo());
                    test.assertEqual(Iterable.create(), response.getErrors());
                });

                runner.test("with full XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackResponse")
                        .addChild(XMLElement.create("Error")
                            .addChild(XMLElement.create("Number")
                                .addText("1")))
                        .addChild(XMLElement.create("Error")
                            .addChild(XMLElement.create("Number")
                                .addText("2")))
                        .addChild(XMLElement.create("TrackInfo")
                            .setAttribute("ID", "3"))
                        .addChild(XMLElement.create("TrackInfo")
                            .setAttribute("ID", "4"));
                    final TrackResponseText response = TrackResponseText.create(xml);
                    test.assertNotNull(response);
                    test.assertEqual(xml, response.toXml());
                    test.assertEqual(
                        Iterable.create(
                            TrackInfoText.create()
                                .setId("3"),
                            TrackInfoText.create()
                                .setId("4")),
                        response.getTrackInfo());
                    test.assertEqual(
                        Iterable.create(
                            Error.create()
                                .setNumber("1"),
                            Error.create()
                                .setNumber("2")),
                        response.getErrors());
                });
            });

            runner.testGroup("addError(Error)", () ->
            {
                final Action3<TrackResponseText,Error,Throwable> addErrorErrorTest = (TrackResponseText response, Error error, Throwable expected) ->
                {
                    runner.test("with " + English.andList(response, error), (Test test) ->
                    {
                        final Iterable<Error> originalErrors = response.getErrors().toList();
                        test.assertThrows(() -> response.addError(error), expected);
                        test.assertEqual(originalErrors, response.getErrors());
                    });
                };

                addErrorErrorTest.run(TrackResponseText.create(), null, new PreConditionFailure("error cannot be null."));

                final Action3<TrackResponseText,Error,Iterable<Error>> addErrorTest = (TrackResponseText response, Error error, Iterable<Error> expected) ->
                {
                    runner.test("with " + English.andList(response, error), (Test test) ->
                    {
                        final TrackResponseText addErrorResult = response.addError(error);
                        test.assertSame(response, addErrorResult);
                        test.assertEqual(expected, response.getErrors());
                    });
                };

                addErrorTest.run(
                    TrackResponseText.create(),
                    Error.create(),
                    Iterable.create(
                        Error.create()));
            });

            runner.testGroup("addErrors(Iterable<Error>)", () ->
            {
                final Action3<TrackResponseText,Iterable<Error>,Throwable> addErrorsErrorTest = (TrackResponseText response, Iterable<Error> errors, Throwable expected) ->
                {
                    runner.test("with " + English.andList(response, errors), (Test test) ->
                    {
                        final Iterable<Error> originalErrors = response.getErrors().toList();
                        test.assertThrows(() -> response.addErrors(errors), expected);
                        test.assertEqual(originalErrors, response.getErrors());
                    });
                };

                addErrorsErrorTest.run(TrackResponseText.create(), null, new PreConditionFailure("errors cannot be null."));

                final Action3<TrackResponseText,Iterable<Error>,Iterable<Error>> addErrorsTest = (TrackResponseText response, Iterable<Error> errors, Iterable<Error> expected) ->
                {
                    runner.test("with " + English.andList(response, errors), (Test test) ->
                    {
                        final TrackResponseText addErrorsResult = response.addErrors(errors);
                        test.assertSame(response, addErrorsResult);
                        test.assertEqual(expected, response.getErrors());
                    });
                };

                addErrorsTest.run(
                    TrackResponseText.create(),
                    Iterable.create(),
                    Iterable.create());

                addErrorsTest.run(
                    TrackResponseText.create(),
                    Iterable.create(Error.create()),
                    Iterable.create(
                        Error.create()));

                addErrorsTest.run(
                    TrackResponseText.create(),
                    Iterable.create(
                        Error.create(),
                        Error.create()),
                    Iterable.create(
                        Error.create(),
                        Error.create()));
            });

            runner.testGroup("addTrackInfo(TrackInfoText)", () ->
            {
                final Action3<TrackResponseText,TrackInfoText,Throwable> addTrackInfoErrorTest = (TrackResponseText response, TrackInfoText trackInfo, Throwable expected) ->
                {
                    runner.test("with " + English.andList(response, trackInfo), (Test test) ->
                    {
                        final Iterable<TrackInfoText> originalTrackInfo = response.getTrackInfo().toList();
                        test.assertThrows(() -> response.addTrackInfo(trackInfo), expected);
                        test.assertEqual(originalTrackInfo, response.getTrackInfo());
                    });
                };

                addTrackInfoErrorTest.run(TrackResponseText.create(), null, new PreConditionFailure("trackInfo cannot be null."));

                final Action3<TrackResponseText,TrackInfoText,Iterable<TrackInfoText>> addTrackInfoTest = (TrackResponseText response, TrackInfoText trackInfo, Iterable<TrackInfoText> expected) ->
                {
                    runner.test("with " + English.andList(response, trackInfo), (Test test) ->
                    {
                        final TrackResponseText addTrackInfoResult = response.addTrackInfo(trackInfo);
                        test.assertSame(response, addTrackInfoResult);
                        test.assertEqual(expected, response.getTrackInfo());
                    });
                };

                addTrackInfoTest.run(
                    TrackResponseText.create(),
                    TrackInfoText.create(),
                    Iterable.create(
                        TrackInfoText.create()));
            });
        });
    }
}
