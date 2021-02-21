package qub;

public interface TrackRequestURL
{
    static MutableURL create(TrackRequestBase trackRequest)
    {
        PreCondition.assertNotNull(trackRequest, "trackRequest");

        return URL.create()
            .setScheme("https")
            .setHost("secure.shippingapis.com")
            .setPath("/ShippingAPI.dll")
            .setQueryParameter("API", "TrackV2")
            .setQueryParameter("XML", trackRequest.toString());
    }
}
