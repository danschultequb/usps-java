package qub;

public interface TrackInfoTextTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackInfoText.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackInfoText trackInfo = TrackInfoText.create();
                test.assertNotNull(trackInfo);
                test.assertEqual(Iterable.create(), trackInfo.getErrors());
                test.assertThrows(() -> trackInfo.getSummary().await(),
                    new NotFoundException("No XML element children found with the name \"TrackSummary\"."));
                test.assertEqual(Iterable.create(), trackInfo.getDetails());
                test.assertEqual(XMLElement.create("TrackInfo"), trackInfo.toXml());
                test.assertEqual("<TrackInfo/>", trackInfo.toString());
            });

            runner.testGroup("create(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackInfoText.create(xml), expected);
                    });
                };

                createErrorTest.run(
                    null,
                    new PreConditionFailure("xml cannot be null."));
                createErrorTest.run(
                    XMLElement.create("a"),
                    new PreConditionFailure("xml.getName() (a) must be TrackInfo."));

                final Action1<XMLElement> createTest = (XMLElement xml) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackInfoText trackInfo = TrackInfoText.create(xml);
                        test.assertNotNull(trackInfo);
                        test.assertEqual(xml, trackInfo.toXml());
                    });
                };

                createTest.run(XMLElement.create("TrackInfo"));
            });

            runner.testGroup("setId(String)", () ->
            {
                final Action3<TrackInfoText,String,Throwable> setIdErrorTest = (TrackInfoText trackInfo, String id, Throwable expected) ->
                {
                    runner.test("with " + English.andList(trackInfo, Strings.escapeAndQuote(id)), (Test test) ->
                    {
                        final String originalId = trackInfo.getId();
                        test.assertThrows(() -> trackInfo.setId(id), expected);
                        test.assertEqual(originalId, trackInfo.getId());
                    });
                };

                setIdErrorTest.run(TrackInfoText.create(), null, new PreConditionFailure("id cannot be null."));

                final Action3<TrackInfoText,String,XMLElement> setIdTest = (TrackInfoText trackInfo, String id, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(trackInfo, Strings.escapeAndQuote(id)), (Test test) ->
                    {
                        final TrackInfoText setIdResult = trackInfo.setId(id);
                        test.assertSame(trackInfo, setIdResult);
                        test.assertEqual(id, trackInfo.getId());
                        test.assertEqual(expected, trackInfo.toXml());
                    });
                };

                setIdTest.run(
                    TrackInfoText.create(),
                    "",
                    XMLElement.create("TrackInfo")
                        .setAttribute("ID", ""));
                setIdTest.run(
                    TrackInfoText.create(),
                    "a",
                    XMLElement.create("TrackInfo")
                        .setAttribute("ID", "a"));
                setIdTest.run(
                    TrackInfoText.create()
                        .setId("a"),
                    "a",
                    XMLElement.create("TrackInfo")
                        .setAttribute("ID", "a"));
                setIdTest.run(
                    TrackInfoText.create()
                        .setId("a"),
                    "b",
                    XMLElement.create("TrackInfo")
                        .setAttribute("ID", "b"));
            });

            runner.testGroup("addError(Error)", () ->
            {
                final Action3<TrackInfoText,Error,Throwable> addErrorErrorTest = (TrackInfoText trackInfo, Error error, Throwable expected) ->
                {
                    runner.test("with " + English.andList(trackInfo, error), (Test test) ->
                    {
                        final Iterable<Error> originalErrors = trackInfo.getErrors();
                        test.assertThrows(() -> trackInfo.addError(error), expected);
                        test.assertEqual(originalErrors, trackInfo.getErrors());
                    });
                };

                addErrorErrorTest.run(
                    TrackInfoText.create(),
                    null,
                    new PreConditionFailure("error cannot be null."));

                final Action3<TrackInfoText,Error,XMLElement> addErrorTest = (TrackInfoText trackInfo, Error error, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(trackInfo, error), (Test test) ->
                    {
                        final TrackInfoText addErrorResult = trackInfo.addError(error);
                        test.assertSame(trackInfo, addErrorResult);
                        test.assertTrue(trackInfo.getErrors().contains(error));
                        test.assertEqual(expected, trackInfo.toXml());
                    });
                };

                addErrorTest.run(
                    TrackInfoText.create(),
                    Error.create(),
                    XMLElement.create("TrackInfo")
                        .addChild(XMLElement.create("Error")));
            });

            runner.testGroup("getErrors()", () ->
            {
                final Action2<TrackInfoText,Iterable<Error>> getErrorsTest = (TrackInfoText trackInfo, Iterable<Error> expected) ->
                {
                    runner.test("with " + trackInfo, (Test test) ->
                    {
                        test.assertEqual(expected, trackInfo.getErrors());
                    });
                };

                getErrorsTest.run(
                    TrackInfoText.create(),
                    Iterable.create());
                getErrorsTest.run(
                    TrackInfoText.create()
                        .addError(Error.create()),
                    Iterable.create(
                        Error.create()));
                getErrorsTest.run(
                    TrackInfoText.create()
                        .addError(Error.create()
                            .setNumber("hello")),
                    Iterable.create(
                        Error.create()
                            .setNumber("hello")));
                getErrorsTest.run(
                    TrackInfoText.create()
                        .addError(Error.create()
                            .setNumber("a"))
                        .addError(Error.create()
                            .setNumber("b")),
                    Iterable.create(
                        Error.create()
                            .setNumber("a"),
                        Error.create()
                            .setNumber("b")));
            });
        });
    }
}
