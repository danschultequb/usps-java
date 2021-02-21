package qub;

/**
 * A fake USPS web services API client.
 */
public class FakeUSPSClient implements USPSClient
{
    private final List<TrackInfoText> trackInfoTexts;
    private final List<TrackInfoFields> trackInfoFields;

    private FakeUSPSClient()
    {
        this.trackInfoTexts = List.create();
        this.trackInfoFields = List.create();
    }

    public static FakeUSPSClient create()
    {
        return new FakeUSPSClient();
    }

    public FakeUSPSClient addTrackInfo(TrackInfoText trackInfo)
    {
        PreCondition.assertNotNull(trackInfo, "trackInfo");

        this.trackInfoTexts.add(trackInfo);

        return this;
    }

    public FakeUSPSClient addTrackInfo(TrackInfoFields trackInfo)
    {
        PreCondition.assertNotNull(trackInfo, "trackInfo");

        this.trackInfoFields.add(trackInfo);

        return this;
    }

    @Override
    public Result<TrackResponseText> trackText(TrackRequest trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");
        PreCondition.assertNotNullAndNotEmpty(trackRequest.getTrackIds(), "trackRequest.getTrackIds()");

        return Result.create(() ->
        {
            final TrackResponseText result = TrackResponseText.create();
            for (final String trackingId : trackRequest.getTrackIds())
            {
                final TrackInfoText match = this.trackInfoTexts.first((TrackInfoText trackInfo) -> Comparer.equal(trackInfo.getId(), trackingId));
                if (match != null)
                {
                    result.addTrackInfo(match);
                }
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public Result<TrackResponseFields> trackField(TrackFieldRequest trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");
        PreCondition.assertNotNullAndNotEmpty(trackRequest.getTrackIds(), "trackRequest.getTrackIds()");

        return Result.create(() ->
        {
            final TrackResponseFields result = TrackResponseFields.create();
            for (final String trackingId : trackRequest.getTrackIds())
            {
                final TrackInfoFields match = this.trackInfoFields.first((TrackInfoFields trackInfo) -> Comparer.equal(trackInfo.getId(), trackingId));
                if (match != null)
                {
                    result.addTrackInfo(match);
                }
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }
}
