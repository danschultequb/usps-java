package qub;

public class TrackFieldRequest extends TrackRequestBase
{
    private static final String elementName = "TrackFieldRequest";

    private TrackFieldRequest(XMLElement xml)
    {
        super(xml);
    }

    public static TrackFieldRequest create()
    {
        return new TrackFieldRequest(XMLElement.create(TrackFieldRequest.elementName));
    }

    @Override
    public TrackFieldRequest setUserId(String userId)
    {
        return (TrackFieldRequest)super.setUserId(userId);
    }

    @Override
    public TrackFieldRequest addTrackId(String trackId)
    {
        return (TrackFieldRequest)super.addTrackId(trackId);
    }

    @Override
    public TrackFieldRequest addTrackIds(Iterable<String> trackIds)
    {
        return (TrackFieldRequest)super.addTrackIds(trackIds);
    }
}
