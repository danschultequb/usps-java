package qub;

public class TrackResponseFields extends TrackResponse
{
    private TrackResponseFields(XMLElement xml)
    {
        super(xml);
    }
    
    public static TrackResponseFields create()
    {
        return TrackResponseFields.create(XMLElement.create(TrackResponse.elementName));
    }
    
    public static TrackResponseFields create(XMLElement xml)
    {
        return new TrackResponseFields(xml);
    }

    public TrackResponseFields addTrackInfo(TrackInfoFields trackInfo)
    {
        PreCondition.assertNotNull(trackInfo, "trackInfo");

        return (TrackResponseFields)this.addChild(trackInfo.toXml());
    }
    
    public Iterable<TrackInfoFields> getTrackInfo()
    {
        return this.getTrackInfo(TrackInfoFields::create);
    }
}
