package qub;

public abstract class TrackResponse extends XMLElementWrapperBase
{
    protected static final String elementName = "TrackResponse";

    protected TrackResponse(XMLElement xml)
    {
        super(xml);

        PreCondition.assertEqual(TrackResponse.elementName, xml.getName(), "xml.getName()");
    }

    public static TrackResponseFields createFields()
    {
        return TrackResponseFields.create();
    }

    public static TrackResponseFields createFields(XMLElement xml)
    {
        return TrackResponseFields.create(xml);
    }

    public static TrackResponseText createText()
    {
        return TrackResponseText.create();
    }

    public static TrackResponseText createText(XMLElement xml)
    {
        return TrackResponseText.create(xml);
    }

    protected TrackResponse addChild(XMLElement child)
    {
        PreCondition.assertNotNull(child, "child");

        this.toXml().addChild(child);

        return this;
    }

    protected TrackResponse addChildren(Iterable<XMLElement> children)
    {
        PreCondition.assertNotNull(children, "children");

        for (final XMLElement child : children)
        {
            this.addChild(child);
        }

        return this;
    }

    public Iterable<Error> getErrors()
    {
        return this.toXml().getElementChildren(Error.elementName).map(Error::create);
    }

    protected TrackResponse addError(Error error)
    {
        PreCondition.assertNotNull(error, "error");

        return this.addChild(error.toXml());
    }

    protected TrackResponse addErrors(Iterable<Error> errors)
    {
        PreCondition.assertNotNull(errors, "errors");

        return this.addChildren(errors.map(Error::toXml));
    }

    protected <T extends TrackInfo> Iterable<T> getTrackInfo(Function1<XMLElement,T> creator)
    {
        PreCondition.assertNotNull(creator, "creator");

        return this.toXml().getElementChildren(TrackInfo.elementName).map(creator);
    }
}
