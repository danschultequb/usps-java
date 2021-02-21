package qub;

public class Error extends XMLElementWrapperBase
{
    public static final String elementName = "Error";
    private static final String numberElementName = "Number";
    private static final String descriptionElementName = "Description";
    private static final String sourceElementName = "Source";
    private static final String helpFileElementName = "HelpFile";
    private static final String helpContextElementName = "HelpContext";

    private Error(XMLElement xml)
    {
        super(xml);

        PreCondition.assertEqual(Error.elementName, xml.getName(), "xml.getName()");
    }

    public static Error create()
    {
        return Error.create(XMLElement.create(Error.elementName));
    }

    public static Error create(XMLElement xml)
    {
        return new Error(xml);
    }

    private String getText(String elementName)
    {
        PreCondition.assertNotNullAndNotEmpty(elementName, "elementName");

        return this.toXml().getFirstElementChild(elementName)
            .then((Function1<XMLElement, String>)XMLElement::getText)
            .catchError(NotFoundException.class, () -> "")
            .await();
    }

    private Error setText(String elementName, String text)
    {
        PreCondition.assertNotNullAndNotEmpty(elementName, "elementName");
        PreCondition.assertNotNull(text, "text");

        XMLHelper.setText(this.toXml(), elementName, text);

        return this;
    }

    public String getNumber()
    {
        return this.getText(Error.numberElementName);
    }

    public Error setNumber(String number)
    {
        PreCondition.assertNotNull(number, "number");

        return this.setText(Error.numberElementName, number);
    }

    public String getDescription()
    {
        return this.getText(Error.descriptionElementName);
    }

    public Error setDescription(String description)
    {
        PreCondition.assertNotNull(description, "description");

        return this.setText(Error.descriptionElementName, description);
    }

    public String getSource()
    {
        return this.getText(Error.sourceElementName);
    }

    public Error setSource(String source)
    {
        PreCondition.assertNotNull(source, "source");

        return this.setText(Error.sourceElementName, source);
    }

    public Error addHelpFile()
    {
        this.toXml().addChild(XMLElement.create(Error.helpFileElementName));

        return this;
    }

    public Error addHelpContext()
    {
        this.toXml().addChild(XMLElement.create(Error.helpContextElementName));

        return this;
    }
}
