package qub;

public interface XMLHelper
{
    static String getText(XMLElement element, String childElementName)
    {
        PreCondition.assertNotNull(element, "element");
        PreCondition.assertNotNullAndNotEmpty(childElementName, "childElementName");

        String result = "";
        final XMLElement child = element.getFirstElementChild(childElementName)
            .catchError(NotFoundException.class)
            .await();
        if (child != null)
        {
            result = child.getText();
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    static void setText(XMLElement xmlElement, String text)
    {
        PreCondition.assertNotNull(xmlElement, "xmlElement");
        PreCondition.assertNotNull(text, "text");

        xmlElement.clearChildren();
        if (!Strings.isNullOrEmpty(text))
        {
            xmlElement.addText(text);
        }
    }

    static void setText(XMLElement xmlElement, String childElementName, String text)
    {
        PreCondition.assertNotNull(xmlElement, "xmlElement");
        PreCondition.assertNotNullAndNotEmpty(childElementName, "childElementName");
        PreCondition.assertNotNull(text, "text");

        XMLHelper.setText(xmlElement.getFirstOrCreateElementChild(childElementName), text);
    }
}
