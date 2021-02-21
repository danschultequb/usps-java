package qub;

public class TrackError extends RuntimeException
{
    private final Error error;

    public TrackError(Error error)
    {
        super(TrackError.getMessage(error));

        this.error = error;
    }

    private static String getMessage(Error error)
    {
        PreCondition.assertNotNull(error, "error");

        return error.toString();
    }

    public Error getError()
    {
        return this.error;
    }
}
