package qub;

public class TrackDetailFields extends TrackDetailFieldsBase implements TrackDetail
{
    private TrackDetailFields(XMLElement xml)
    {
        super(xml);
    }

    public static TrackDetailFields create()
    {
        return TrackDetailFields.create(XMLElement.create(TrackDetail.elementName));
    }

    public static TrackDetailFields create(XMLElement xml)
    {
        PreCondition.assertNotNull(xml, "xml");
        PreCondition.assertEqual(TrackDetail.elementName, xml.getName(), "xml.getName()");

        return new TrackDetailFields(xml);
    }

    @Override
    public TrackDetailFields setEventTime(String eventTime)
    {
        return (TrackDetailFields)super.setEventTime(eventTime);
    }

    @Override
    public TrackDetailFields setEventDate(String eventDate)
    {
        return (TrackDetailFields)super.setEventDate(eventDate);
    }

    @Override
    public TrackDetailFields setEvent(String event)
    {
        return (TrackDetailFields)super.setEvent(event);
    }

    @Override
    public TrackDetailFields setEventCity(String eventCity)
    {
        return (TrackDetailFields)super.setEventCity(eventCity);
    }

    @Override
    public TrackDetailFields setEventState(String eventState)
    {
        return (TrackDetailFields)super.setEventState(eventState);
    }

    @Override
    public TrackDetailFields setEventZipCode(String eventZipCode)
    {
        return (TrackDetailFields)super.setEventZipCode(eventZipCode);
    }

    @Override
    public TrackDetailFields setEventCountry(String eventCountry)
    {
        return (TrackDetailFields)super.setEventCountry(eventCountry);
    }

    @Override
    public TrackDetailFields setFirmName(String firmName)
    {
        return (TrackDetailFields)super.setFirmName(firmName);
    }

    @Override
    public TrackDetailFields setName(String name)
    {
        return (TrackDetailFields)super.setName(name);
    }

    @Override
    public TrackDetailFields setAuthorizedAgent(String authorizedAgent)
    {
        return (TrackDetailFields)super.setAuthorizedAgent(authorizedAgent);
    }

    @Override
    public TrackDetailFields setDeliveryAttributeCode(String deliveryAttributeCode)
    {
        return (TrackDetailFields)super.setDeliveryAttributeCode(deliveryAttributeCode);
    }
}
