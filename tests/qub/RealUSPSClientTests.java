package qub;

public interface RealUSPSClientTests
{
    String skipUserId = "xxxxxxxxxxxx";
    String userId = RealUSPSClientTests.skipUserId;
    boolean skipTests = (RealUSPSClientTests.userId == RealUSPSClientTests.skipUserId);

    static RealUSPSClient createUspsClient(Test test)
    {
        return RealUSPSClient.create(HttpClient.create(test.getNetwork()));
    }

    static RealUSPSClient createUspsClient(Test test, String userId)
    {
        return RealUSPSClientTests.createUspsClient(test)
            .setUserId(userId);
    }

    static void test(TestRunner runner)
    {
        runner.testGroup(RealUSPSClient.class, () ->
        {
            USPSClientTests.test(
                runner,
                runner.skip(RealUSPSClientTests.skipTests),
                (Test test) -> RealUSPSClientTests.createUspsClient(test, RealUSPSClientTests.userId));

            runner.testGroup("create(HttpClient)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> RealUSPSClient.create(null),
                        new PreConditionFailure("httpClient cannot be null."));
                });

                runner.test("with non-null", (Test test) ->
                {
                    final Network network = test.getNetwork();
                    final HttpClient httpClient = HttpClient.create(network);
                    final RealUSPSClient uspsClient = RealUSPSClient.create(httpClient);
                    test.assertNotNull(uspsClient);
                    test.assertNull(uspsClient.getUserId());
                });
            });

            runner.testGroup("setUserId(String)", () ->
            {
                final Action2<String,Throwable> setUserIdErrorTest = (String userId, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(userId), (Test test) ->
                    {
                        final RealUSPSClient uspsClient = RealUSPSClientTests.createUspsClient(test);
                        test.assertThrows(() -> uspsClient.setUserId(userId),
                            expected);
                        test.assertNull(uspsClient.getUserId());
                    });
                };

                setUserIdErrorTest.run(null, new PreConditionFailure("userId cannot be null."));
                setUserIdErrorTest.run("", new PreConditionFailure("userId cannot be empty."));

                final Action1<String> setUserIdTest = (String userId) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(userId), (Test test) ->
                    {
                        final RealUSPSClient uspsClient = RealUSPSClientTests.createUspsClient(test);
                        final RealUSPSClient setUserIdResult = uspsClient.setUserId(userId);
                        test.assertSame(uspsClient, setUserIdResult);
                        test.assertEqual(userId, uspsClient.getUserId());
                    });
                };

                setUserIdTest.run("hellothere");
                setUserIdTest.run(RealUSPSClientTests.userId);
            });

            runner.testGroup("addUserId(TrackRequestBase)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final RealUSPSClient uspsClient = RealUSPSClientTests.createUspsClient(test);
                    test.assertThrows(() -> uspsClient.addUserId(null),
                        new PreConditionFailure("trackRequest cannot be null."));
                });

                final Action3<String,String,String> addUserIdTest = (String clientUserId, String requestUserId, String expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(clientUserId, requestUserId).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final RealUSPSClient uspsClient = RealUSPSClientTests.createUspsClient(test);
                        if (!Strings.isNullOrEmpty(clientUserId))
                        {
                            uspsClient.setUserId(clientUserId);
                        }

                        final TrackRequest request = TrackRequest.create();
                        if (!Strings.isNullOrEmpty(requestUserId))
                        {
                            request.setUserId(requestUserId);
                        }

                        uspsClient.addUserId(request);

                        test.assertEqual(Strings.isNullOrEmpty(clientUserId) ? null : clientUserId, uspsClient.getUserId());
                        test.assertEqual(expected, request.getUserId());
                    });
                };

                addUserIdTest.run(null, null, "");
                addUserIdTest.run(null, "b", "b");
                addUserIdTest.run("a", null, "a");
                addUserIdTest.run("a", "b", "b");
            });
        });
    }
}
