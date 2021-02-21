package qub;

public interface TrackRequestTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackRequest.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackRequest request = TrackRequest.create();
                test.assertNotNull(request);
                test.assertEqual("", request.getUserId());
                test.assertEqual(Iterable.create(), request.getTrackIds());
                test.assertEqual(
                    XMLElement.create("TrackRequest"),
                    request.toXml());
            });

            runner.testGroup("setUserId(String)", () ->
            {
                final Action3<TrackRequest,String,Throwable> setUserIdErrorTest = (TrackRequest request, String userId, Throwable expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(userId)), (Test test) ->
                    {
                        test.assertThrows(() -> request.setUserId(userId), expected);
                    });
                };

                setUserIdErrorTest.run(
                    TrackRequest.create(),
                    null,
                    new PreConditionFailure("userId cannot be null."));

                final Action3<TrackRequest,String,XMLElement> setUserIdTest = (TrackRequest request, String userId, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(userId)), (Test test) ->
                    {
                        final TrackRequest setUserIdResult = request.setUserId(userId);
                        test.assertSame(request, setUserIdResult);
                        test.assertEqual(userId, request.getUserId());
                        test.assertEqual(expected, request.toXml());
                    });
                };

                setUserIdTest.run(
                    TrackRequest.create(),
                    "",
                    XMLElement.create("TrackRequest")
                        .setAttribute("USERID", ""));
                setUserIdTest.run(
                    TrackRequest.create(),
                    "abc",
                    XMLElement.create("TrackRequest")
                        .setAttribute("USERID", "abc"));
                setUserIdTest.run(
                    TrackRequest.create().setUserId("abc"),
                    "d",
                    XMLElement.create("TrackRequest")
                        .setAttribute("USERID", "d"));
            });

            runner.testGroup("addTrackId(String)", () ->
            {
                final Action3<TrackRequest,String,Throwable> addTrackIdErrorTest = (TrackRequest request, String trackId, Throwable expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(trackId)), (Test test) ->
                    {
                        test.assertThrows(() -> request.addTrackId(trackId), expected);
                    });
                };

                addTrackIdErrorTest.run(
                    TrackRequest.create(),
                    null,
                    new PreConditionFailure("trackId cannot be null."));
                addTrackIdErrorTest.run(
                    TrackRequest.create(),
                    "",
                    new PreConditionFailure("trackId cannot be empty."));

                final Action3<TrackRequest,String,XMLElement> addTrackIdTest = (TrackRequest request, String trackId, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(trackId)), (Test test) ->
                    {
                        final List<String> expectedTrackIds = request.getTrackIds().toList().add(trackId);

                        final TrackRequest addTrackIdResult = request.addTrackId(trackId);
                        test.assertSame(request, addTrackIdResult);
                        test.assertEqual(expectedTrackIds, request.getTrackIds());
                        test.assertEqual(expected, request.toXml());
                    });
                };

                addTrackIdTest.run(
                    TrackRequest.create(),
                    "abc",
                    XMLElement.create("TrackRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc")));
                addTrackIdTest.run(
                    TrackRequest.create()
                        .addTrackId("abc"),
                    "def",
                    XMLElement.create("TrackRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc"))
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "def")));
            });

            runner.testGroup("addTrackIds(Iterable<String>)", () ->
            {
                final Action3<TrackRequest,Iterable<String>,Throwable> addTrackIdsErrorTest = (TrackRequest request, Iterable<String> trackIds, Throwable expected) ->
                {
                    runner.test("with " + English.andList(request, trackIds == null ? null : trackIds.map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        test.assertThrows(() -> request.addTrackIds(trackIds), expected);
                    });
                };

                addTrackIdsErrorTest.run(
                    TrackRequest.create(),
                    null,
                    new PreConditionFailure("trackIds cannot be null."));
                addTrackIdsErrorTest.run(
                    TrackRequest.create(),
                    Iterable.create((String)null),
                    new PreConditionFailure("trackId cannot be null."));
                addTrackIdsErrorTest.run(
                    TrackRequest.create(),
                    Iterable.create(""),
                    new PreConditionFailure("trackId cannot be empty."));

                final Action3<TrackRequest,Iterable<String>,XMLElement> addTrackIdsTest = (TrackRequest request, Iterable<String> trackIds, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(request, trackIds.map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final List<String> expectedTrackIds = request.getTrackIds().toList().addAll(trackIds);

                        final TrackRequest addTrackIdResult = request.addTrackIds(trackIds);
                        test.assertSame(request, addTrackIdResult);
                        test.assertEqual(expectedTrackIds, request.getTrackIds());
                        test.assertEqual(expected, request.toXml());
                    });
                };

                addTrackIdsTest.run(
                    TrackRequest.create(),
                    Iterable.create("abc"),
                    XMLElement.create("TrackRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc")));
                addTrackIdsTest.run(
                    TrackRequest.create()
                        .addTrackId("abc"),
                    Iterable.create("def"),
                    XMLElement.create("TrackRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc"))
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "def")));
                addTrackIdsTest.run(
                    TrackRequest.create(),
                    Iterable.create("1", "2", "3"),
                    XMLElement.create("TrackRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "1"))
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "2"))
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "3")));
            });
        });
    }
}
