package qub;

public interface TrackResponseTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackResponse.class, () ->
        {
            runner.test("createFields()", (Test test) ->
            {
                final TrackResponseFields response = TrackResponse.createFields();
                test.assertNotNull(response);
                test.assertEqual(XMLElement.create("TrackResponse"), response.toXml());
                test.assertEqual(Iterable.create(), response.getErrors());
                test.assertEqual(Iterable.create(), response.getTrackInfo());
            });

            runner.testGroup("createFields(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createFieldsErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackResponse.createFields(xml), expected);
                    });
                };

                createFieldsErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createFieldsErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackResponse."));

                runner.test("with empty XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackResponse");
                    final TrackResponseFields response = TrackResponse.createFields(xml);
                    test.assertNotNull(response);
                    test.assertEqual(xml, response.toXml());
                    test.assertEqual(Iterable.create(), response.getErrors());
                    test.assertEqual(Iterable.create(), response.getTrackInfo());
                });

                runner.test("with full XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackResponse")
                        .addChild(XMLElement.create("Error"))
                        .addChild(XMLElement.create("TrackInfo"));
                    final TrackResponseFields response = TrackResponse.createFields(xml);
                    test.assertNotNull(response);
                    test.assertEqual(xml, response.toXml());
                    test.assertEqual(Iterable.create(Error.create()), response.getErrors());
                    test.assertEqual(Iterable.create(TrackInfoFields.create()), response.getTrackInfo());
                });
            });

            runner.test("createText()", (Test test) ->
            {
                final TrackResponseText response = TrackResponse.createText();
                test.assertNotNull(response);
                test.assertEqual(XMLElement.create("TrackResponse"), response.toXml());
                test.assertEqual(Iterable.create(), response.getErrors());
                test.assertEqual(Iterable.create(), response.getTrackInfo());
            });

            runner.testGroup("createText(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createTextErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackResponse.createText(xml), expected);
                    });
                };

                createTextErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createTextErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackResponse."));

                runner.test("with empty XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackResponse");
                    final TrackResponseText response = TrackResponse.createText(xml);
                    test.assertNotNull(response);
                    test.assertEqual(xml, response.toXml());
                    test.assertEqual(Iterable.create(), response.getErrors());
                    test.assertEqual(Iterable.create(), response.getTrackInfo());
                });

                runner.test("with full XML element", (Test test) ->
                {
                    final XMLElement xml = XMLElement.create("TrackResponse")
                        .addChild(XMLElement.create("Error"))
                        .addChild(XMLElement.create("TrackInfo"));
                    final TrackResponseText response = TrackResponse.createText(xml);
                    test.assertNotNull(response);
                    test.assertEqual(xml, response.toXml());
                    test.assertEqual(Iterable.create(Error.create()), response.getErrors());
                    test.assertEqual(Iterable.create(TrackInfoText.create()), response.getTrackInfo());
                });
            });
        });
    }
}
