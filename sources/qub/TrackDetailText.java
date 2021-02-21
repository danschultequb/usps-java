package qub;

/**
 * A string that describes a tracking detail.
 */
public class TrackDetailText extends TrackDetailTextBase implements TrackDetail
{
    protected TrackDetailText(XMLElement xml)
    {
        super(xml);
    }

    public static TrackDetailText create()
    {
        return TrackDetailText.create(XMLElement.create(TrackDetail.elementName));
    }

    public static TrackDetailText create(String text)
    {
        return TrackDetailText.create()
            .setText(text);
    }

    public static TrackDetailText create(XMLElement xml)
    {
        PreCondition.assertNotNull(xml, "xml");
        PreCondition.assertEqual(TrackDetail.elementName, xml.getName(), "xml.getName()");

        return new TrackDetailText(xml);
    }

    @Override
    public TrackDetailText setText(String text)
    {
        return (TrackDetailText)super.setText(text);
    }
}
