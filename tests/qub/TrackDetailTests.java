package qub;

public interface TrackDetailTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackDetail.class, () ->
        {
            runner.test("createText()", (Test test) ->
            {
                final TrackDetailText trackDetail = TrackDetail.createText();
                test.assertNotNull(trackDetail);
                test.assertEqual("", trackDetail.getText());
                test.assertEqual(XMLElement.create("TrackDetail"), trackDetail.toXml());
                test.assertEqual("<TrackDetail/>", trackDetail.toString());
            });

            runner.testGroup("createText(String)", () ->
            {
                final Action2<String,Throwable> createTextErrorTest = (String text, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        test.assertThrows(() -> TrackDetail.createText(text), expected);
                    });
                };

                createTextErrorTest.run(null, new PreConditionFailure("text cannot be null."));

                final Action1<String> createTextTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final TrackDetailText trackDetail = TrackDetail.createText(text);
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
                        test.assertThrows(() -> TrackDetail.createText(xml), expected);
                    });
                };

                createTextErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createTextErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackDetail."));
                createTextErrorTest.run(XMLElement.create("TrackSummary"), new PreConditionFailure("xml.getName() (TrackSummary) must be TrackDetail."));

                final Action2<XMLElement,String> createTextTest = (XMLElement xml, String expectedText) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackDetailText trackDetail = TrackDetail.createText(xml);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(expectedText, trackDetail.getText());
                        test.assertEqual(xml, trackDetail.toXml());
                    });
                };

                createTextTest.run(XMLElement.create("TrackDetail"), "");
                createTextTest.run(XMLElement.create("TrackDetail").setAttribute("a", "b"), "");
                createTextTest.run(XMLElement.create("TrackDetail").addChild(XMLText.create("hello")), "hello");
            });

            runner.test("createFields()", (Test test) ->
            {
                final TrackDetailFields trackDetail = TrackDetail.createFields();
                test.assertNotNull(trackDetail);
                test.assertEqual(XMLElement.create("TrackDetail"), trackDetail.toXml());
                test.assertEqual("<TrackDetail/>", trackDetail.toString());
            });

            runner.testGroup("createFields(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createFieldsErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackDetail.createFields(xml), expected);
                    });
                };

                createFieldsErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createFieldsErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackDetail."));
                createFieldsErrorTest.run(XMLElement.create("TrackSummary"), new PreConditionFailure("xml.getName() (TrackSummary) must be TrackDetail."));

                final Action2<XMLElement,String> createFieldsTest = (XMLElement xml, String expectedText) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackDetailFields trackDetail = TrackDetail.createFields(xml);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(xml, trackDetail.toXml());
                    });
                };

                createFieldsTest.run(XMLElement.create("TrackDetail"), "");
                createFieldsTest.run(XMLElement.create("TrackDetail").setAttribute("a", "b"), "");
                createFieldsTest.run(XMLElement.create("TrackDetail").addChild(XMLText.create("hello")), "hello");
            });
        });
    }
}
