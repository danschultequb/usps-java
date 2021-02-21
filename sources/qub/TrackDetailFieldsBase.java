package qub;

public abstract class TrackDetailFieldsBase extends TrackDetailBase
{
    private static final String eventTimeElementName = "EventTime";
    private static final String eventDateElementName = "EventDate";
    private static final String eventElementName = "Event";
    private static final String eventCityElementName = "EventCity";
    private static final String eventStateElementName = "EventState";
    private static final String eventZipCodeElementName = "EventZIPCode";
    private static final String eventCountryElementName = "EventCountry";
    private static final String firmNameElementName = "FirmName";
    private static final String nameElementName = "Name";
    private static final String authorizedAgentElementName = "AuthorizedAgent";
    private static final String deliveryAttributeCodeElementName = "DeliveryAttributeCode";

    protected TrackDetailFieldsBase(XMLElement xml)
    {
        super(xml);
    }

    private String getText(String childElementName)
    {
        PreCondition.assertNotNullAndNotEmpty(childElementName, "childElementName");

        return XMLHelper.getText(this.toXml(), childElementName);
    }

    private TrackDetailFieldsBase setText(String childElementName, String text)
    {
        PreCondition.assertNotNullAndNotEmpty(childElementName, "childElementName");
        PreCondition.assertNotNull(text, "text");

        XMLHelper.setText(this.toXml(), childElementName, text);

        return this;
    }

    public String getEventTime()
    {
        return this.getText(TrackDetailFieldsBase.eventTimeElementName);
    }

    protected TrackDetailFieldsBase setEventTime(String eventTime)
    {
        PreCondition.assertNotNull(eventTime, "eventTime");

        return this.setText(TrackDetailFieldsBase.eventTimeElementName, eventTime);
    }

    public String getEventDate()
    {
        return this.getText(TrackDetailFieldsBase.eventDateElementName);
    }

    protected TrackDetailFieldsBase setEventDate(String eventDate)
    {
        PreCondition.assertNotNull(eventDate, "eventDate");

        return this.setText(TrackDetailFieldsBase.eventDateElementName, eventDate);
    }

    public String getEvent()
    {
        return this.getText(TrackDetailFieldsBase.eventElementName);
    }

    protected TrackDetailFieldsBase setEvent(String event)
    {
        PreCondition.assertNotNull(event, "event");

        return this.setText(TrackDetailFieldsBase.eventElementName, event);
    }

    public String getEventCity()
    {
        return this.getText(TrackDetailFieldsBase.eventCityElementName);
    }

    protected TrackDetailFieldsBase setEventCity(String eventCity)
    {
        PreCondition.assertNotNull(eventCity, "eventCity");

        return this.setText(TrackDetailFieldsBase.eventCityElementName, eventCity);
    }

    public String getEventState()
    {
        return this.getText(TrackDetailFieldsBase.eventStateElementName);
    }

    protected TrackDetailFieldsBase setEventState(String eventState)
    {
        PreCondition.assertNotNull(eventState, "eventState");

        return this.setText(TrackDetailFieldsBase.eventStateElementName, eventState);
    }

    public String getEventZipCode()
    {
        return this.getText(TrackDetailFieldsBase.eventZipCodeElementName);
    }

    protected TrackDetailFieldsBase setEventZipCode(String eventZipCode)
    {
        PreCondition.assertNotNull(eventZipCode, "eventZipCode");

        return this.setText(TrackDetailFieldsBase.eventZipCodeElementName, eventZipCode);
    }

    public String getEventCountry()
    {
        return this.getText(TrackDetailFieldsBase.eventCountryElementName);
    }

    protected TrackDetailFieldsBase setEventCountry(String eventCountry)
    {
        PreCondition.assertNotNull(eventCountry, "eventCountry");

        return this.setText(TrackDetailFieldsBase.eventCountryElementName, eventCountry);
    }

    public String getFirmName()
    {
        return this.getText(TrackDetailFieldsBase.firmNameElementName);
    }

    protected TrackDetailFieldsBase setFirmName(String firmName)
    {
        PreCondition.assertNotNull(firmName, "firmName");

        return this.setText(TrackDetailFieldsBase.firmNameElementName, firmName);
    }

    public String getName()
    {
        return this.getText(TrackDetailFieldsBase.nameElementName);
    }

    protected TrackDetailFieldsBase setName(String name)
    {
        PreCondition.assertNotNull(name, "name");

        return this.setText(TrackDetailFieldsBase.nameElementName, name);
    }

    public String getAuthorizedAgent()
    {
        return this.getText(TrackDetailFieldsBase.authorizedAgentElementName);
    }

    protected TrackDetailFieldsBase setAuthorizedAgent(String authorizedAgent)
    {
        PreCondition.assertNotNull(authorizedAgent, "authorizedAgent");

        return this.setText(TrackDetailFieldsBase.authorizedAgentElementName, authorizedAgent);
    }

    public String getDeliveryAttributeCode()
    {
        return this.getText(TrackDetailFieldsBase.deliveryAttributeCodeElementName);
    }

    protected TrackDetailFieldsBase setDeliveryAttributeCode(String deliveryAttributeCode)
    {
        PreCondition.assertNotNull(deliveryAttributeCode, "deliveryAttributeCode");

        return this.setText(TrackDetailFieldsBase.deliveryAttributeCodeElementName, deliveryAttributeCode);
    }
}
