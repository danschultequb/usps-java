package qub;

public class TrackRequest extends TrackRequestBase
{
    private static final String trackRequestElementName = "TrackRequest";

    private TrackRequest(XMLElement xml)
    {
        super(xml);
    }

    public static TrackRequest create()
    {
        return new TrackRequest(XMLElement.create(TrackRequest.trackRequestElementName));
    }

    @Override
    public TrackRequest setUserId(String userId)
    {
        return (TrackRequest)super.setUserId(userId);
    }

    @Override
    public TrackRequest addTrackId(String trackId)
    {
        return (TrackRequest)super.addTrackId(trackId);
    }

    @Override
    public TrackRequest addTrackIds(Iterable<String> trackIds)
    {
        return (TrackRequest)super.addTrackIds(trackIds);
    }
}
