package qub;

public interface TrackFieldRequestTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackFieldRequest.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final TrackFieldRequest request = TrackFieldRequest.create();
                test.assertNotNull(request);
                test.assertEqual("", request.getUserId());
                test.assertEqual(Iterable.create(), request.getTrackIds());
                test.assertEqual(
                    XMLElement.create("TrackFieldRequest"),
                    request.toXml());
            });

            runner.testGroup("setUserId(String)", () ->
            {
                final Action3<TrackFieldRequest,String,Throwable> setUserIdErrorTest = (TrackFieldRequest request, String userId, Throwable expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(userId)), (Test test) ->
                    {
                        test.assertThrows(() -> request.setUserId(userId), expected);
                    });
                };

                setUserIdErrorTest.run(
                    TrackFieldRequest.create(),
                    null,
                    new PreConditionFailure("userId cannot be null."));

                final Action3<TrackFieldRequest,String,XMLElement> setUserIdTest = (TrackFieldRequest request, String userId, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(userId)), (Test test) ->
                    {
                        final TrackFieldRequest setUserIdResult = request.setUserId(userId);
                        test.assertSame(request, setUserIdResult);
                        test.assertEqual(userId, request.getUserId());
                        test.assertEqual(expected, request.toXml());
                    });
                };

                setUserIdTest.run(
                    TrackFieldRequest.create(),
                    "",
                    XMLElement.create("TrackFieldRequest")
                        .setAttribute("USERID", ""));
                setUserIdTest.run(
                    TrackFieldRequest.create(),
                    "abc",
                    XMLElement.create("TrackFieldRequest")
                        .setAttribute("USERID", "abc"));
                setUserIdTest.run(
                    TrackFieldRequest.create().setUserId("abc"),
                    "d",
                    XMLElement.create("TrackFieldRequest")
                        .setAttribute("USERID", "d"));
            });

            runner.testGroup("addTrackId(String)", () ->
            {
                final Action3<TrackFieldRequest,String,Throwable> addTrackIdErrorTest = (TrackFieldRequest request, String trackId, Throwable expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(trackId)), (Test test) ->
                    {
                        test.assertThrows(() -> request.addTrackId(trackId), expected);
                    });
                };

                addTrackIdErrorTest.run(
                    TrackFieldRequest.create(),
                    null,
                    new PreConditionFailure("trackId cannot be null."));
                addTrackIdErrorTest.run(
                    TrackFieldRequest.create(),
                    "",
                    new PreConditionFailure("trackId cannot be empty."));

                final Action3<TrackFieldRequest,String,XMLElement> addTrackIdTest = (TrackFieldRequest request, String trackId, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(request, Strings.escapeAndQuote(trackId)), (Test test) ->
                    {
                        final List<String> expectedTrackIds = request.getTrackIds().toList().add(trackId);

                        final TrackFieldRequest addTrackIdResult = request.addTrackId(trackId);
                        test.assertSame(request, addTrackIdResult);
                        test.assertEqual(expectedTrackIds, request.getTrackIds());
                        test.assertEqual(expected, request.toXml());
                    });
                };

                addTrackIdTest.run(
                    TrackFieldRequest.create(),
                    "abc",
                    XMLElement.create("TrackFieldRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc")));
                addTrackIdTest.run(
                    TrackFieldRequest.create()
                        .addTrackId("abc"),
                    "def",
                    XMLElement.create("TrackFieldRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc"))
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "def")));
            });

            runner.testGroup("addTrackIds(Iterable<String>)", () ->
            {
                final Action3<TrackFieldRequest,Iterable<String>,Throwable> addTrackIdsErrorTest = (TrackFieldRequest request, Iterable<String> trackIds, Throwable expected) ->
                {
                    runner.test("with " + English.andList(request, trackIds == null ? null : trackIds.map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        test.assertThrows(() -> request.addTrackIds(trackIds), expected);
                    });
                };

                addTrackIdsErrorTest.run(
                    TrackFieldRequest.create(),
                    null,
                    new PreConditionFailure("trackIds cannot be null."));
                addTrackIdsErrorTest.run(
                    TrackFieldRequest.create(),
                    Iterable.create((String)null),
                    new PreConditionFailure("trackId cannot be null."));
                addTrackIdsErrorTest.run(
                    TrackFieldRequest.create(),
                    Iterable.create(""),
                    new PreConditionFailure("trackId cannot be empty."));

                final Action3<TrackFieldRequest,Iterable<String>,XMLElement> addTrackIdsTest = (TrackFieldRequest request, Iterable<String> trackIds, XMLElement expected) ->
                {
                    runner.test("with " + English.andList(request, trackIds.map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final List<String> expectedTrackIds = request.getTrackIds().toList().addAll(trackIds);

                        final TrackFieldRequest addTrackIdResult = request.addTrackIds(trackIds);
                        test.assertSame(request, addTrackIdResult);
                        test.assertEqual(expectedTrackIds, request.getTrackIds());
                        test.assertEqual(expected, request.toXml());
                    });
                };

                addTrackIdsTest.run(
                    TrackFieldRequest.create(),
                    Iterable.create("abc"),
                    XMLElement.create("TrackFieldRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc")));
                addTrackIdsTest.run(
                    TrackFieldRequest.create()
                        .addTrackId("abc"),
                    Iterable.create("def"),
                    XMLElement.create("TrackFieldRequest")
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "abc"))
                        .addChild(XMLElement.create("TrackID")
                            .setAttribute("ID", "def")));
                addTrackIdsTest.run(
                    TrackFieldRequest.create(),
                    Iterable.create("1", "2", "3"),
                    XMLElement.create("TrackFieldRequest")
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
