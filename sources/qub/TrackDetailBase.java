package qub;

public abstract class TrackDetailBase extends XMLElementWrapperBase
{
    private static final Iterable<String> allowedElementNames = Iterable.create(TrackSummary.elementName, TrackDetail.elementName);

    protected TrackDetailBase(XMLElement xml)
    {
        super(xml);

        PreCondition.assertOneOf(xml.getName(), TrackDetailBase.allowedElementNames, "xml.getName()");
    }
}
