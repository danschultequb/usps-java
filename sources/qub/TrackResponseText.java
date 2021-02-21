package qub;

public class TrackResponseText extends TrackResponse
{
    private TrackResponseText(XMLElement xml)
    {
        super(xml);
    }

    public static TrackResponseText create()
    {
        return TrackResponseText.create(XMLElement.create(TrackResponse.elementName));
    }

    public static TrackResponseText create(XMLElement xml)
    {
        return new TrackResponseText(xml);
    }

    @Override
    public TrackResponseText addError(Error error)
    {
        return (TrackResponseText)super.addError(error);
    }

    @Override
    public TrackResponseText addErrors(Iterable<Error> errors)
    {
        return (TrackResponseText)super.addErrors(errors);
    }

    public TrackResponseText addTrackInfo(TrackInfoText trackInfo)
    {
        PreCondition.assertNotNull(trackInfo, "trackInfo");

        return (TrackResponseText)this.addChild(trackInfo.toXml());
    }

    public Iterable<TrackInfoText> getTrackInfo()
    {
        return this.getTrackInfo(TrackInfoText::create);
    }
}
