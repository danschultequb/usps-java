package qub;

public interface TrackSummaryTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackSummary.class, () ->
        {
            runner.test("createText()", (Test test) ->
            {
                final TrackSummaryText trackDetail = TrackSummary.createText();
                test.assertNotNull(trackDetail);
                test.assertEqual("", trackDetail.getText());
                test.assertEqual(XMLElement.create("TrackSummary"), trackDetail.toXml());
                test.assertEqual("<TrackSummary/>", trackDetail.toString());
            });

            runner.testGroup("createText(String)", () ->
            {
                final Action2<String,Throwable> createTextErrorTest = (String text, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        test.assertThrows(() -> TrackSummary.createText(text), expected);
                    });
                };

                createTextErrorTest.run(null, new PreConditionFailure("text cannot be null."));

                final Action1<String> createTextTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final TrackSummaryText trackDetail = TrackSummary.createText(text);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(text, trackDetail.getText());
                    });
                };

                createTextTest.run("");
                createTextTest.run("hello");
                createTextTest.run(" there ");
            });

            runner.testGroup("createText(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createTextErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackSummary.createText(xml), expected);
                    });
                };

                createTextErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createTextErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackSummary."));
                createTextErrorTest.run(XMLElement.create("TrackDetail"), new PreConditionFailure("xml.getName() (TrackDetail) must be TrackSummary."));

                final Action2<XMLElement,String> createTextTest = (XMLElement xml, String expectedText) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackSummaryText trackDetail = TrackSummary.createText(xml);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(expectedText, trackDetail.getText());
                        test.assertEqual(xml, trackDetail.toXml());
                    });
                };

                createTextTest.run(XMLElement.create("TrackSummary"), "");
                createTextTest.run(XMLElement.create("TrackSummary").setAttribute("a", "b"), "");
                createTextTest.run(XMLElement.create("TrackSummary").addChild(XMLText.create("hello")), "hello");
            });

            runner.test("createFields()", (Test test) ->
            {
                final TrackSummaryFields trackSummary = TrackSummary.createFields();
                test.assertNotNull(trackSummary);
                test.assertEqual(XMLElement.create("TrackSummary"), trackSummary.toXml());
                test.assertEqual("<TrackSummary/>", trackSummary.toString());
            });

            runner.testGroup("createFields(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createFieldsErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackSummary.createFields(xml), expected);
                    });
                };

                createFieldsErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createFieldsErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackSummary."));
                createFieldsErrorTest.run(XMLElement.create("TrackDetail"), new PreConditionFailure("xml.getName() (TrackDetail) must be TrackSummary."));

                final Action1<XMLElement> createFieldsTest = (XMLElement xml) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackSummaryFields trackSummary = TrackSummary.createFields(xml);
                        test.assertNotNull(trackSummary);
                        test.assertEqual(xml, trackSummary.toXml());
                    });
                };

                createFieldsTest.run(XMLElement.create("TrackSummary"));
                createFieldsTest.run(XMLElement.create("TrackSummary").setAttribute("a", "b"));
                createFieldsTest.run(XMLElement.create("TrackSummary").addChild(XMLText.create("hello")));
            });
        });
    }
}
