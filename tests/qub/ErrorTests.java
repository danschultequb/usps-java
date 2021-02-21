package qub;

public interface ErrorTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(Error.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final Error error = Error.create();
                test.assertNotNull(error);
                test.assertEqual("", error.getNumber());
                test.assertEqual("", error.getDescription());
                test.assertEqual("", error.getSource());
                test.assertEqual(XMLElement.create("Error"), error.toXml());
                test.assertEqual("<Error/>", error.toString());
            });

            runner.testGroup("create(XMLElement)", () ->
            {
                final Action2<XMLElement,Throwable> createErrorTest = (XMLElement xml, Throwable expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        test.assertThrows(() -> Error.create(xml), expected);
                    });
                };

                createErrorTest.run(null, new PreConditionFailure("xml cannot be null."));
                createErrorTest.run(XMLElement.create("a"), new PreConditionFailure("xml.getName() (a) must be Error."));

                final Action1<XMLElement> createTest = (XMLElement xml) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        test.assertNotNull(error);
                        test.assertEqual(xml, error.toXml());
                    });
                };

                createTest.run(XMLElement.create("Error"));
            });

            runner.testGroup("getNumber()", () ->
            {
                final Action2<XMLElement,String> getNumberTest = (XMLElement xml, String expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        test.assertEqual(expected, error.getNumber());
                    });
                };

                getNumberTest.run(
                    XMLElement.create("Error"),
                    "");
                getNumberTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")),
                    "");
                getNumberTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")
                            .addText("abc")),
                    "abc");
                getNumberTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")
                            .addText("123")),
                    "123");
            });

            runner.testGroup("setNumber(String)", () ->
            {
                final Action3<XMLElement,String,XMLElement> getNumberTest = (XMLElement xml, String number, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(xml, Strings.escapeAndQuote(number)), (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        final Error setNumberResult = error.setNumber(number);
                        test.assertSame(error, setNumberResult);
                        test.assertEqual(number, error.getNumber());
                        test.assertEqual(expected, error.toXml());
                    });
                };

                getNumberTest.run(
                    XMLElement.create("Error"),
                    "",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")));
                getNumberTest.run(
                    XMLElement.create("Error"),
                    "abc",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")
                            .addText("abc")));
                getNumberTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")),
                    "",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")));
                getNumberTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")),
                    "123",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")
                            .addText("123")));
                getNumberTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")
                            .addText("abc")),
                    "123",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Number")
                            .addText("123")));
            });

            runner.testGroup("getDescription()", () ->
            {
                final Action2<XMLElement,String> getDescriptionTest = (XMLElement xml, String expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        test.assertEqual(expected, error.getDescription());
                    });
                };

                getDescriptionTest.run(
                    XMLElement.create("Error"),
                    "");
                getDescriptionTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")),
                    "");
                getDescriptionTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")
                            .addText("abc")),
                    "abc");
                getDescriptionTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")
                            .addText("123")),
                    "123");
            });

            runner.testGroup("setDescription(String)", () ->
            {
                final Action3<XMLElement,String,XMLElement> getDescriptionTest = (XMLElement xml, String description, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(xml, Strings.escapeAndQuote(description)), (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        final Error setDescriptionResult = error.setDescription(description);
                        test.assertSame(error, setDescriptionResult);
                        test.assertEqual(description, error.getDescription());
                        test.assertEqual(expected, error.toXml());
                    });
                };

                getDescriptionTest.run(
                    XMLElement.create("Error"),
                    "",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")));
                getDescriptionTest.run(
                    XMLElement.create("Error"),
                    "abc",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")
                            .addText("abc")));
                getDescriptionTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")),
                    "",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")));
                getDescriptionTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")),
                    "123",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")
                            .addText("123")));
                getDescriptionTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")
                            .addText("abc")),
                    "123",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Description")
                            .addText("123")));
            });

            runner.testGroup("getSource()", () ->
            {
                final Action2<XMLElement,String> getSourceTest = (XMLElement xml, String expected) ->
                {
                    runner.test("with " + xml, (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        test.assertEqual(expected, error.getSource());
                    });
                };

                getSourceTest.run(
                    XMLElement.create("Error"),
                    "");
                getSourceTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")),
                    "");
                getSourceTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")
                            .addText("abc")),
                    "abc");
                getSourceTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")
                            .addText("123")),
                    "123");
            });

            runner.testGroup("setSource(String)", () ->
            {
                final Action3<XMLElement,String,XMLElement> getSourceTest = (XMLElement xml, String source, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(xml, Strings.escapeAndQuote(source)), (Test test) ->
                    {
                        final Error error = Error.create(xml);
                        final Error setSourceResult = error.setSource(source);
                        test.assertSame(error, setSourceResult);
                        test.assertEqual(source, error.getSource());
                        test.assertEqual(expected, error.toXml());
                    });
                };

                getSourceTest.run(
                    XMLElement.create("Error"),
                    "",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")));
                getSourceTest.run(
                    XMLElement.create("Error"),
                    "abc",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")
                            .addText("abc")));
                getSourceTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")),
                    "",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")));
                getSourceTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")),
                    "123",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")
                            .addText("123")));
                getSourceTest.run(
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")
                            .addText("abc")),
                    "123",
                    XMLElement.create("Error")
                        .addChild(XMLElement.create("Source")
                            .addText("123")));
            });

            runner.testGroup("equals(Object)", () ->
            {
                final Action3<Error,Object,Boolean> equalsTest = (Error error, Object rhs, Boolean expected) ->
                {
                    runner.test("with " + English.andList(error, rhs), (Test test) ->
                    {
                        test.assertEqual(expected, error.equals(rhs));
                    });
                };

                equalsTest.run(
                    Error.create(),
                    null,
                    false);
                equalsTest.run(
                    Error.create(),
                    "hello",
                    false);
                equalsTest.run(
                    Error.create(),
                    XMLElement.create("Error"),
                    false);
                equalsTest.run(
                    Error.create(),
                    Error.create(),
                    true);
                equalsTest.run(
                    Error.create()
                        .setNumber("a"),
                    Error.create(),
                    false);
                equalsTest.run(
                    Error.create(),
                    Error.create()
                        .setNumber("a"),
                    false);
                equalsTest.run(
                    Error.create()
                        .setNumber("a"),
                    Error.create()
                        .setNumber("b"),
                    false);
                equalsTest.run(
                    Error.create()
                        .setNumber("a"),
                    Error.create()
                        .setNumber("a"),
                    true);
            });
        });
    }
}
