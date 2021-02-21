package qub;

public abstract class TrackDetailTextBase extends TrackDetailBase
{
    protected TrackDetailTextBase(XMLElement xml)
    {
        super(xml);
    }

    public String getText()
    {
        return this.toXml().getText();
    }

    protected TrackDetailTextBase setText(String text)
    {
        XMLHelper.setText(this.toXml(), text);

        return this;
    }
}
