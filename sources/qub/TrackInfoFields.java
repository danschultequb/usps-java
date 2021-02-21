package qub;

public class TrackInfoFields extends TrackInfo
{
    private TrackInfoFields(XMLElement xml)
    {
        super(xml);
    }

    public static TrackInfoFields create()
    {
        return TrackInfoFields.create(XMLElement.create(TrackInfo.elementName));
    }

    public static TrackInfoFields create(XMLElement xml)
    {
        return new TrackInfoFields(xml);
    }

    @Override
    public TrackInfoFields setId(String id)
    {
        return (TrackInfoFields)super.setId(id);
    }

    @Override
    public TrackInfoFields addError(Error error)
    {
        return (TrackInfoFields)super.addError(error);
    }

    public Result<TrackSummaryFields> getSummary()
    {
        return this.getXmlSummary().then((Function1<XMLElement, TrackSummaryFields>)TrackSummaryFields::create);
    }

    public TrackInfoFields addSummary(TrackSummaryFields summary)
    {
        PreCondition.assertNotNull(summary, "summary");

        return (TrackInfoFields)super.addXmlChild(summary.toXml());
    }

    public Iterable<TrackDetailFields> getDetails()
    {
        return this.getXmlDetails().map(TrackDetailFields::create);
    }

    public TrackInfoFields addDetail(TrackDetailFields detail)
    {
        PreCondition.assertNotNull(detail, "detail");

        return (TrackInfoFields)super.addXmlChild(detail.toXml());
    }
}
