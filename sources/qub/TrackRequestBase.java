package qub;

public abstract class TrackRequestBase extends XMLElementWrapperBase
{
    private static final String userIdAttributeName = "USERID";
    private static final String trackIdElementName = "TrackID";
    private static final String idAttributeName = "ID";

    protected TrackRequestBase(XMLElement xml)
    {
        super(xml);
    }

    public String getUserId()
    {
        return this.toXml().getAttributeValue(TrackRequestBase.userIdAttributeName)
            .catchError(NotFoundException.class, () -> "")
            .await();
    }

    protected TrackRequestBase setUserId(String userId)
    {
        PreCondition.assertNotNull(userId, "userId");

        this.toXml().setAttribute(TrackRequestBase.userIdAttributeName, userId);

        return this;
    }

    protected TrackRequestBase addTrackId(String trackId)
    {
        PreCondition.assertNotNullAndNotEmpty(trackId, "trackId");

        this.toXml().addChild(XMLElement.create(TrackRequestBase.trackIdElementName)
            .setAttribute(TrackRequestBase.idAttributeName, trackId));

        return this;
    }

    protected TrackRequestBase addTrackIds(Iterable<String> trackIds)
    {
        PreCondition.assertNotNull(trackIds, "trackIds");

        for (final String trackId : trackIds)
        {
            this.addTrackId(trackId);
        }

        return this;
    }

    /**
     * Get the tracking IDs that have been added to this request.
     * @return The tracking IDs that have been added to this request.
     */
    public Iterable<String> getTrackIds()
    {
        return this.toXml().getElementChildren(TrackRequestBase.trackIdElementName)
            .map((XMLElement trackId) -> trackId.getAttributeValue(TrackRequestBase.idAttributeName).await());
    }
}
