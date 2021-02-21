package qub;

public interface TrackDetailTextTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackDetailText.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackDetailText trackDetail = TrackDetailText.create();
                test.assertNotNull(trackDetail);
                test.assertEqual("", trackDetail.getText());
                test.assertEqual(XMLElement.create("TrackDetail"), trackDetail.toXml());
                test.assertEqual("<TrackDetail/>", trackDetail.toString());
            });

            runner.testGroup("create(String)", () ->
            {
                final Action2<String,Throwable> createErrorTest = (String text, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        test.assertThrows(() -> TrackDetailText.create(text), expected);
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("text cannot be null."));

                final Action1<String> createTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final TrackDetailText trackDetail = TrackDetailText.create(text);
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
                        test.assertThrows(() -> TrackDetailText.create(xml), expected);
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be TrackDetail."));
                createErrorTest.run(XMLElement.create("TrackSummary"), new PreConditionFailure("xml.getName() (TrackSummary) must be TrackDetail."));

                final Action2<XMLElement,String> createTest = (XMLElement xml, String expectedText) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final TrackDetailText trackDetail = TrackDetailText.create(xml);
                        test.assertNotNull(trackDetail);
                        test.assertEqual(expectedText, trackDetail.getText());
                        test.assertEqual(xml, trackDetail.toXml());
                    });
                };

                createTest.run(XMLElement.create("TrackDetail"), "");
                createTest.run(XMLElement.create("TrackDetail").setAttribute("a", "b"), "");
                createTest.run(XMLElement.create("TrackDetail").addChild(XMLText.create("hello")), "hello");
            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final TrackDetailText trackDetail = TrackDetailText.create();
                    test.assertThrows(() -> trackDetail.setText(null),
                        new PreConditionFailure("text cannot be null."));
                });

                final Action1<String> setTextTest = (String text) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(text), (Test test) ->
                    {
                        final TrackDetailText trackDetail = TrackDetailText.create();
                        final TrackDetailText setTextResult = trackDetail.setText(text);
                        test.assertSame(trackDetail, setTextResult);
                        test.assertEqual(text, trackDetail.getText());
                    });
                };

                setTextTest.run("");
                setTextTest.run("hello");

                runner.test("with multiple calls to setText()", (Test test) ->
                {
                    final TrackDetailText trackDetail = TrackDetailText.create();

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
                final Action3<TrackDetailText,Object,Boolean> equalsTest = (TrackDetailText trackDetail, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(trackDetail, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, trackDetail.equals(rhs));
                    });
                };

                equalsTest.run(
                    TrackDetailText.create(),
                    null,
                    false);
                equalsTest.run(
                    TrackDetailText.create(),
                    "hello",
                    false);
                equalsTest.run(
                    TrackDetailText.create(),
                    null,
                    false);
                equalsTest.run(
                    TrackDetailText.create(),
                    TrackDetailText.create(),
                    true);
                equalsTest.run(
                    TrackDetailText.create()
                        .setText("abc"),
                    TrackDetailText.create(),
                    false);
                equalsTest.run(
                    TrackDetailText.create(),
                    TrackDetailText.create()
                        .setText("abc"),
                    false);
                equalsTest.run(
                    TrackDetailText.create()
                        .setText("abc"),
                    TrackDetailText.create()
                        .setText("123"),
                    false);
                equalsTest.run(
                    TrackDetailText.create()
                        .setText("abc"),
                    TrackDetailText.create()
                        .setText("abc"),
                    true);
            });
        });
    }
}
