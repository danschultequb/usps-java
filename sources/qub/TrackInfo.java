package qub;

public abstract class TrackInfo extends XMLElementWrapperBase
{
    protected final static String elementName = "TrackInfo";
    private final static String idAttributeName = "ID";

    protected TrackInfo(XMLElement xml)
    {
        super(xml);

        PreCondition.assertEqual(TrackInfo.elementName, xml.getName(), "xml.getName()");
    }

    public static TrackInfoText createText()
    {
        return TrackInfoText.create();
    }

    public static TrackInfoText createText(XMLElement xml)
    {
        return TrackInfoText.create(xml);
    }

    public static TrackInfoFields createFields()
    {
        return TrackInfoFields.create();
    }

    public static TrackInfoFields createFields(XMLElement xml)
    {
        return TrackInfoFields.create(xml);
    }

    public String getId()
    {
        return this.toXml().getAttributeValue(TrackInfo.idAttributeName)
            .catchError(NotFoundException.class)
            .await();
    }

    protected TrackInfo setId(String id)
    {
        PreCondition.assertNotNull(id, "id");

        this.toXml().setAttribute(TrackInfo.idAttributeName, id);

        return this;
    }

    protected TrackInfo addXmlChild(XMLElement child)
    {
        PreCondition.assertNotNull(child, "child");

        this.toXml().addChild(child);

        return this;
    }

    protected TrackInfo addError(Error error)
    {
        PreCondition.assertNotNull(error, "error");

        this.toXml().addChild(error.toXml());

        return this;
    }

    public Iterable<Error> getErrors()
    {
        return this.toXml().getElementChildren(Error.elementName)
            .map(Error::create);
    }

    protected TrackInfo setXmlSummary(XMLElement xml)
    {
        PreCondition.assertNotNull(xml, "xml");
        PreCondition.assertEqual(TrackSummary.elementName, xml.getName(), "xml.getName()");

        this.toXml().removeElementChildren(TrackSummary.elementName)
            .catchError(NotFoundException.class)
            .await();
        return this.addXmlChild(xml);
    }

    protected Result<XMLElement> getXmlSummary()
    {
        return this.toXml().getFirstElementChild(TrackSummary.elementName);
    }

    protected Iterable<XMLElement> getXmlDetails()
    {
        return this.toXml().getElementChildren(TrackDetail.elementName);
    }
}
