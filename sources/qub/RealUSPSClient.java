package qub;

/**
 * A real USPS client that makes web requests to the actual USPS web services.
 */
public class RealUSPSClient implements USPSClient
{
    private final HttpClient httpClient;
    private String userId;

    private RealUSPSClient(HttpClient httpClient)
    {
        PreCondition.assertNotNull(httpClient, "httpClient");

        this.httpClient = httpClient;
    }

    public static RealUSPSClient create(HttpClient httpClient)
    {
        return new RealUSPSClient(httpClient);
    }

    public String getUserId()
    {
        return this.userId;
    }

    public RealUSPSClient setUserId(String userId)
    {
        PreCondition.assertNotNullAndNotEmpty(userId, "userId");

        this.userId = userId;

        return this;
    }

    public void addUserId(TrackRequestBase trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");

        if (Strings.isNullOrEmpty(trackRequest.getUserId()) && !Strings.isNullOrEmpty(this.userId))
        {
            trackRequest.setUserId(this.userId);
        }
    }

    private URL createRequestURL(TrackRequestBase trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");

        return TrackRequestURL.create(trackRequest);
    }

    @Override
    public Result<TrackResponseText> trackText(TrackRequest trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");
        PreCondition.assertNotNullAndNotEmpty(trackRequest.getTrackIds(), "trackRequest.getTrackIds()");

        return Result.create(() ->
        {
            TrackResponseText result;

            this.addUserId(trackRequest);

            final URL requestUrl = this.createRequestURL(trackRequest);
            try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
            {
                final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                final XMLElement rootElement = bodyXml.getRoot();
                if (Error.elementName.equals(rootElement.getName()))
                {
                    throw new TrackError(Error.create(rootElement));
                }

                result = TrackResponseText.create(rootElement);
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
            TrackResponseFields result;

            this.addUserId(trackRequest);

            final URL requestUrl = this.createRequestURL(trackRequest);
            try (final HttpResponse httpResponse = httpClient.get(requestUrl).await())
            {
                final XMLDocument bodyXml = XML.parse(CharacterReadStream.iterate(CharacterReadStream.create(httpResponse.getBody()))).await();
                final XMLElement rootElement = bodyXml.getRoot();
                if (Error.elementName.equals(rootElement.getName()))
                {
                    throw new TrackError(Error.create(rootElement));
                }

                result = TrackResponseFields.create(rootElement);
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }
}
