package qub;

public interface TrackErrorTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TrackError.class, () ->
        {
            runner.testGroup("constructor(Error)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    test.assertThrows(() -> new TrackError(null),
                        new PreConditionFailure("error cannot be null."));
                });

                final Action1<Error> constructorTest = (Error error) ->
                {
                    runner.test("with " + error, (Test test) ->
                    {
                        final TrackError trackError = new TrackError(error);
                        test.assertEqual(error, trackError.getError());
                        test.assertEqual(error.toString(), trackError.getMessage());
                    });
                };

                constructorTest.run(Error.create());
                constructorTest.run(Error.create().setNumber("abc"));
            });
        });
    }
}
