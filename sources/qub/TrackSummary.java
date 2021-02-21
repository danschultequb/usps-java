package qub;

public interface TrackSummary
{
    String elementName = "TrackSummary";

    static TrackSummaryText createText()
    {
        return TrackSummaryText.create();
    }

    static TrackSummaryText createText(String text)
    {
        return TrackSummaryText.create(text);
    }

    static TrackSummaryText createText(XMLElement xml)
    {
        return TrackSummaryText.create(xml);
    }

    static TrackSummaryFields createFields()
    {
        return TrackSummaryFields.create();
    }

    static TrackSummaryFields createFields(XMLElement xml)
    {
        return TrackSummaryFields.create(xml);
    }

    XMLElement toXml();
}
