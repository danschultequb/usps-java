package qub;

public interface TrackRequestURLTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackRequestURL.class, () ->
        {
            runner.testGroup("create(TrackRequestBase)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> TrackRequestURL.create(null),
                        new PreConditionFailure("trackRequest cannot be null."));
                });

                final Action1<TrackRequestBase> createTest = (TrackRequestBase trackRequest) ->
                {
                    runner.test("with " + trackRequest, (Test test) ->
                    {
                        final MutableURL url = TrackRequestURL.create(trackRequest);
                        test.assertNotNull(url);
                        test.assertEqual("https", url.getScheme());
                        test.assertEqual("secure.shippingapis.com", url.getHost());
                        test.assertEqual("/ShippingAPI.dll", url.getPath());
                        test.assertEqual("API=TrackV2&XML=" + trackRequest, url.getQueryString());
                        test.assertNull(url.getFragment());
                    });
                };

                createTest.run(TrackRequest.create());
                createTest.run(TrackRequest.create().addTrackId("hello"));
                createTest.run(TrackFieldRequest.create());
                createTest.run(TrackFieldRequest.create().addTrackId("there"));
            });
        });
    }
}
