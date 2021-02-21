package qub;

public interface XMLHelperTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(XMLHelper.class, () ->
        {
            runner.testGroup("getText(XMLElement,String)", () ->
            {
                final Action3<XMLElement,String,Throwable> getTextErrorTest = (XMLElement element, String childElementName, Throwable expected) ->
                {
                    runner.test("with " + English.andList(element, Strings.escapeAndQuote(childElementName)), (Test test) ->
                    {
                        test.assertThrows(() -> XMLHelper.getText(element, childElementName), expected);
                    });
                };

                getTextErrorTest.run(
                    null,
                    "b",
                    new PreConditionFailure("element cannot be null."));
                getTextErrorTest.run(
                    XMLElement.create("a"),
                    null,
                    new PreConditionFailure("childElementName cannot be null."));
                getTextErrorTest.run(
                    XMLElement.create("a"),
                    "",
                    new PreConditionFailure("childElementName cannot be empty."));

                final Action3<XMLElement,String,String> getTextTest = (XMLElement element, String childElementName, String expected) ->
                {
                    runner.test("with " + English.andList(element, Strings.escapeAndQuote(childElementName)), (Test test) ->
                    {
                        test.assertEqual(expected, XMLHelper.getText(element, childElementName));
                    });
                };

                getTextTest.run(
                    XMLElement.create("a"),
                    "b",
                    "");
                getTextTest.run(
                    XMLElement.create("a")
                        .addChild(XMLElement.create("b")),
                    "b",
                    "");
                getTextTest.run(
                    XMLElement.create("a")
                        .addChild(XMLElement.create("b")
                            .addText("c")),
                    "b",
                    "c");
            });

            runner.testGroup("setText(XMLElement,String)", () ->
            {
                final Action3<XMLElement,String,Throwable> setTextErrorTest = (XMLElement element, String text, Throwable expected) ->
                {
                    runner.test("with " + English.andList(element, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        test.assertThrows(() -> XMLHelper.setText(element, text), expected);
                    });
                };

                setTextErrorTest.run(
                    null,
                    "b",
                    new PreConditionFailure("xmlElement cannot be null."));
                setTextErrorTest.run(
                    XMLElement.create("a"),
                    null,
                    new PreConditionFailure("text cannot be null."));

                final Action3<XMLElement,String,XMLElement> setTextTest = (XMLElement element, String text, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(element, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        XMLHelper.setText(element, text);
                        test.assertEqual(expected, element);
                        test.assertEqual(text, element.getText());
                    });
                };

                setTextTest.run(
                    XMLElement.create("a"),
                    "",
                    XMLElement.create("a"));
                setTextTest.run(
                    XMLElement.create("a"),
                    "b",
                    XMLElement.create("a")
                        .addText("b"));
                setTextTest.run(
                    XMLElement.create("a")
                        .addText("b"),
                    "c",
                    XMLElement.create("a")
                        .addText("c"));
                setTextTest.run(
                    XMLElement.create("a")
                        .addChild(XMLElement.create("b")),
                    "c",
                    XMLElement.create("a")
                        .addText("c"));
            });
        });
    }
}
