package qub;

public interface TrackSummaryTextTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackSummaryText.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackSummaryText trackDetail = TrackSummaryText.create();
                test.assertNotNull(trackDetail);
                test.assertEqual("", trackDetail.getText());
                test.assertEqual(XMLElement.create("TrackSummary"), trackDetail.toXml());
                test.assertEqual("<TrackSummary/>", trackDetail.toString());
            });

            runner.testGroup("create(String)", () ->
            {
                final Action2<String,Throwable> createErrorTest = (String text, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        test.assertThrows(() -> TrackSummaryText.create(text), expected);
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("text cannot be null."));

                final Action1<String> createTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final TrackSummaryText trackDetail = TrackSummaryText.create(text);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(text, trackDetail.getText());
                    });
                };

                createTest.run("");
                createTest.run("hello");
                createTest.run(" there ");
            });

            runner.testGroup("create(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> TrackSummaryText.create(xml), expected);
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackSummary."));
                createErrorTest.run(XMLElement.create("TrackDetail"), new PreConditionFailure("xml.getName() (TrackDetail) must be TrackSummary."));

                final Action2<XMLElement,String> createTest = (XMLElement xml, String expectedText) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackSummaryText trackDetail = TrackSummaryText.create(xml);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(expectedText, trackDetail.getText());
                        test.assertEqual(xml, trackDetail.toXml());
                    });
                };

                createTest.run(XMLElement.create("TrackSummary"), "");
                createTest.run(XMLElement.create("TrackSummary").setAttribute("a", "b"), "");
                createTest.run(XMLElement.create("TrackSummary").addChild(XMLText.create("hello")), "hello");
            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final TrackSummaryText trackDetail = TrackSummaryText.create();
                    test.assertThrows(() -> trackDetail.setText(null),
                        new PreConditionFailure("text cannot be null."));
                });

                final Action1<String> setTextTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final TrackSummaryText trackDetail = TrackSummaryText.create();
                        final TrackSummaryText setTextResult = trackDetail.setText(text);
                        test.assertSame(trackDetail, setTextResult);
                        test.assertEqual(text, trackDetail.getText());
                    });
                };

                setTextTest.run("");
                setTextTest.run("hello");

                runner.test("with multiple calls to setText()", (Test test) ->
                {
                    final TrackSummaryText trackDetail = TrackSummaryText.create();

                    trackDetail.setText("");
                    test.assertEqual("", trackDetail.getText());

                    trackDetail.setText("hello");
                    test.assertEqual("hello", trackDetail.getText());

                    trackDetail.setText("there");
                    test.assertEqual("there", trackDetail.getText());
                });
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<TrackSummaryText,Object,Boolean> equalsTest = (TrackSummaryText trackSummary, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(trackSummary, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, trackSummary.equals(rhs));
                    });
                };

                equalsTest.run(
                    TrackSummaryText.create(),
                    null,
                    false);
                equalsTest.run(
                    TrackSummaryText.create(),
                    "hello",
                    false);
                equalsTest.run(
                    TrackSummaryText.create(),
                    null,
                    false);
                equalsTest.run(
                    TrackSummaryText.create(),
                    TrackSummaryText.create(),
                    true);
                equalsTest.run(
                    TrackSummaryText.create()
                        .setText("abc"),
                    TrackSummaryText.create(),
                    false);
                equalsTest.run(
                    TrackSummaryText.create(),
                    TrackSummaryText.create()
                        .setText("abc"),
                    false);
                equalsTest.run(
                    TrackSummaryText.create()
                        .setText("abc"),
                    TrackSummaryText.create()
                        .setText("123"),
                    false);
                equalsTest.run(
                    TrackSummaryText.create()
                        .setText("abc"),
                    TrackSummaryText.create()
                        .setText("abc"),
                    true);
            });
        });
    }
}
