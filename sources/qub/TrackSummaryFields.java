package qub;

public class TrackSummaryFields extends TrackDetailFieldsBase implements TrackSummary
{
    private TrackSummaryFields(XMLElement xml)
    {
        super(xml);
    }

    public static TrackSummaryFields create()
    {
        return TrackSummaryFields.create(XMLElement.create(TrackSummary.elementName));
    }

    public static TrackSummaryFields create(XMLElement xml)
    {
        PreCondition.assertNotNull(xml, "xml");
        PreCondition.assertEqual(TrackSummary.elementName, xml.getName(), "xml.getName()");

        return new TrackSummaryFields(xml);
    }

    @Override
    public TrackSummaryFields setEventTime(String eventTime)
    {
        return (TrackSummaryFields)super.setEventTime(eventTime);
    }

    @Override
    public TrackSummaryFields setEventDate(String eventDate)
    {
        return (TrackSummaryFields)super.setEventDate(eventDate);
    }

    @Override
    public TrackSummaryFields setEvent(String event)
    {
        return (TrackSummaryFields)super.setEvent(event);
    }

    @Override
    public TrackSummaryFields setEventCity(String eventCity)
    {
        return (TrackSummaryFields)super.setEventCity(eventCity);
    }

    @Override
    public TrackSummaryFields setEventState(String eventState)
    {
        return (TrackSummaryFields)super.setEventState(eventState);
    }

    @Override
    public TrackSummaryFields setEventZipCode(String eventZipCode)
    {
        return (TrackSummaryFields)super.setEventZipCode(eventZipCode);
    }

    @Override
    public TrackSummaryFields setEventCountry(String eventCountry)
    {
        return (TrackSummaryFields)super.setEventCountry(eventCountry);
    }

    @Override
    public TrackSummaryFields setFirmName(String firmName)
    {
        return (TrackSummaryFields)super.setFirmName(firmName);
    }

    @Override
    public TrackSummaryFields setName(String name)
    {
        return (TrackSummaryFields)super.setName(name);
    }

    @Override
    public TrackSummaryFields setAuthorizedAgent(String authorizedAgent)
    {
        return (TrackSummaryFields)super.setAuthorizedAgent(authorizedAgent);
    }

    @Override
    public TrackSummaryFields setDeliveryAttributeCode(String deliveryAttributeCode)
    {
        return (TrackSummaryFields)super.setDeliveryAttributeCode(deliveryAttributeCode);
    }
}
