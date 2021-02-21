package qub;

public interface TrackInfoTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackInfo.class, () ->
        {
            runner.test("createText()", (Test test) ->
            {
                final TrackInfoText trackInfo = TrackInfo.createText();
                test.assertNotNull(trackInfo);
                test.assertNull(trackInfo.getId());
                test.assertEqual(Iterable.create(), trackInfo.getErrors());
                test.assertThrows(() -> trackInfo.getSummary().await(),
                    new NotFoundException("No XML element children found with the name \"TrackSummary\"."));
                test.assertEqual(Iterable.create(), trackInfo.getDetails());
                test.assertEqual(XMLElement.create("TrackInfo"), trackInfo.toXml());
                test.assertEqual("<TrackInfo/>", trackInfo.toString());
            });

            runner.testGroup("createText(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createTextErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackInfo.createText(xml), expected);
                    });
                };

                createTextErrorTest.run(
                    null,
                    new PreConditionFailure("xml cannot be null."));
                createTextErrorTest.run(
                    XMLElement.create("a"),
                    new PreConditionFailure("xml.getName() (a) must be TrackInfo."));

                final Action1<XMLElement> createTextTest = (XMLElement xml) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackInfoText trackInfo = TrackInfo.createText(xml);
                        test.assertNotNull(trackInfo);
                        test.assertEqual(xml, trackInfo.toXml());
                    });
                };

                createTextTest.run(XMLElement.create("TrackInfo"));
            });

            runner.test("createFields()", (Test test) ->
            {
                final TrackInfoFields trackInfo = TrackInfo.createFields();
                test.assertNotNull(trackInfo);
                test.assertNull(trackInfo.getId());
                test.assertEqual(Iterable.create(), trackInfo.getErrors());
                test.assertThrows(() -> trackInfo.getSummary().await(),
                    new NotFoundException("No XML element children found with the name \"TrackSummary\"."));
                test.assertEqual(Iterable.create(), trackInfo.getDetails());
                test.assertEqual(XMLElement.create("TrackInfo"), trackInfo.toXml());
                test.assertEqual("<TrackInfo/>", trackInfo.toString());
            });

            runner.testGroup("createFields(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createFieldsErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackInfo.createFields(xml), expected);
                    });
                };

                createFieldsErrorTest.run(
                    null,
                    new PreConditionFailure("xml cannot be null."));
                createFieldsErrorTest.run(
                    XMLElement.create("a"),
                    new PreConditionFailure("xml.getName() (a) must be TrackInfo."));

                final Action1<XMLElement> createFieldsTest = (XMLElement xml) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackInfoFields trackInfo = TrackInfo.createFields(xml);
                        test.assertNotNull(trackInfo);
                        test.assertEqual(xml, trackInfo.toXml());
                    });
                };

                createFieldsTest.run(XMLElement.create("TrackInfo"));
            });
        });
    }
}
