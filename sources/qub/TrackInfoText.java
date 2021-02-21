package qub;

public class TrackInfoText extends TrackInfo
{
    private TrackInfoText(XMLElement xml)
    {
        super(xml);
    }

    public static TrackInfoText create()
    {
        return TrackInfoText.create(XMLElement.create(TrackInfo.elementName));
    }

    public static TrackInfoText create(XMLElement xml)
    {
        return new TrackInfoText(xml);
    }

    @Override
    public TrackInfoText setId(String id)
    {
        return (TrackInfoText)super.setId(id);
    }

    @Override
    public TrackInfoText addError(Error error)
    {
        return (TrackInfoText)super.addError(error);
    }

    public TrackInfoText setSummary(TrackSummaryText summary)
    {
        PreCondition.assertNotNull(summary, "summary");

        return (TrackInfoText)this.setXmlSummary(summary.toXml());
    }

    public TrackInfoText setSummary(String summary)
    {
        PreCondition.assertNotNull(summary, "summary");

        return this.setSummary(TrackSummary.createText(summary));
    }

    public Result<TrackSummaryText> getSummary()
    {
        return this.getXmlSummary().then((Function1<XMLElement, TrackSummaryText>)TrackSummaryText::create);
    }

    public TrackInfoText addDetail(TrackDetailText detail)
    {
        PreCondition.assertNotNull(detail, "detail");

        return (TrackInfoText)this.addXmlChild(detail.toXml());
    }

    public TrackInfoText addDetail(String detail)
    {
        PreCondition.assertNotNull(detail, "detail");

        return this.addDetail(TrackDetailText.create(detail));
    }

    public Iterable<TrackDetailText> getDetails()
    {
        return this.getXmlDetails().map(TrackDetailText::create);
    }
}
