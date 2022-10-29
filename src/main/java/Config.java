import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {
    boolean enabled;
    String fileName;
    String format;

    public Config(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                if (currentNode.getNodeName().equals("enabled")) {
                    enabled = Boolean.parseBoolean(currentNode.getTextContent());
                }
                if (currentNode.getNodeName().equals("fileName")) {
                    fileName = currentNode.getTextContent();
                }
                if (currentNode.getNodeName().equals("format")) {
                    format = currentNode.getTextContent();
                }

            }
        }
    }
}