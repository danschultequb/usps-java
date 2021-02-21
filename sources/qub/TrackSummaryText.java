package qub;

/**
 * A string that describes a tracking summary.
 */
public class TrackSummaryText extends TrackDetailTextBase implements TrackSummary
{
    private TrackSummaryText(XMLElement xml)
    {
        super(xml);
    }

    public static TrackSummaryText create()
    {
        return TrackSummaryText.create(XMLElement.create(TrackSummary.elementName));
    }

    public static TrackSummaryText create(String text)
    {
        return TrackSummaryText.create()
            .setText(text);
    }

    public static TrackSummaryText create(XMLElement xml)
    {
        PreCondition.assertNotNull(xml, "xml");
        PreCondition.assertEqual(TrackSummary.elementName, xml.getName(), "xml.getName()");

        return new TrackSummaryText(xml);
    }

    @Override
    public TrackSummaryText setText(String text)
    {
        return (TrackSummaryText)super.setText(text);
    }
}
