package qub;

/**
 * A client that can be used to access the USPS web services API.
 */
public interface USPSClient
{
    /**
     * Create a real USPS client implementation.
     * @param httpClient The HTTP client that the USPS client will use to make requests.
     * @return A real USPS client implementation.
     */
    static RealUSPSClient create(HttpClient httpClient)
    {
        return RealUSPSClient.create(httpClient);
    }

    /**
     * Get the simple text-based tracking for the provided TrackRequest.
     * @param trackRequest The TrackRequest to send.
     * @return The simple text-based tracking for the provided TrackRequest.
     */
    Result<TrackResponseText> trackText(TrackRequest trackRequest);

    /**
     * Get the simple text-based tracking for the provided tracking IDs.
     * @param trackingIds The tracking IDs to get tracking for.
     * @return The simple text-based tracking for the provided tracking IDs.
     */
    default Result<TrackResponseText> trackText(Iterable<String> trackingIds)
    {
        PreCondition.assertNotNullAndNotEmpty(trackingIds, "trackingIds");

        return this.trackText(TrackRequest.create().addTrackIds(trackingIds));
    }

    /**
     * Get the simple text-based tracking for the provided tracking ID.
     * @param trackingId The tracking ID to get tracking for.
     * @return The simple text-based tracking for the provided tracking ID.
     */
    default Result<TrackResponseText> trackText(String trackingId)
    {
        PreCondition.assertNotNullAndNotEmpty(trackingId, "trackingId");

        return this.trackText(TrackRequest.create().addTrackId(trackingId));
    }

    /**
     * Get the detailed field-based tracking for the provided TrackRequest.
     * @param trackFieldRequest The TrackFieldRequest to send.
     * @return The detailed field-based tracking for the provided TrackRequest.
     */
    Result<TrackResponseFields> trackField(TrackFieldRequest trackFieldRequest);

    /**
     * Get the detailed field-based tracking for the provided tracking IDs.
     * @param trackingIds The tracking IDs to get tracking for.
     * @return The detailed field-based tracking for the provided tracking IDs.
     */
    default Result<TrackResponseFields> trackField(Iterable<String> trackingIds)
    {
        PreCondition.assertNotNullAndNotEmpty(trackingIds, "trackingIds");

        return this.trackField(TrackFieldRequest.create().addTrackIds(trackingIds));
    }

    /**
     * Get the detailed field-based tracking for the provided tracking ID.
     * @param trackingId The tracking ID to get tracking for.
     * @return The detailed field-based tracking for the provided tracking ID.
     */
    default Result<TrackResponseFields> trackField(String trackingId)
    {
        PreCondition.assertNotNullAndNotEmpty(trackingId, "trackingId");

        return this.trackField(TrackFieldRequest.create().addTrackId(trackingId));
    }
}