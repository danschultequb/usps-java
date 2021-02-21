package qub;

public interface TrackDetail
{
    String elementName = "TrackDetail";

    static TrackDetailText createText()
    {
        return TrackDetailText.create();
    }
    
    static TrackDetailText createText(String text)
    {
        return TrackDetailText.create(text);
    }
    
    static TrackDetailText createText(XMLElement xml)
    {
        return TrackDetailText.create(xml);
    }

    static TrackDetailFields createFields()
    {
        return TrackDetailFields.create();
    }

    static TrackDetailFields createFields(XMLElement xml)
    {
        return TrackDetailFields.create(xml);
    }

    XMLElement toXml();
}
