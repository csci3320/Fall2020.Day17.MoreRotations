import java.util.ArrayList;
import java.util.List;

public abstract class Digraphable<T extends Comparable> implements MyCollection<T> {

  protected Node<T> root;
  protected Node<T> tempRoot;
  protected Node<T> tempParent; //For viewing purposes only
  protected boolean isLeftRotation; //Keeps track of which direction the current rotation is--only for display
  private List<Node<T>> visitedNodes;// Prevent infinite loops when drawing

  protected Digraphable() {
    root = null;
    tempRoot = null; // Only used during rotations;
    isLeftRotation = false;
  }

  public String getDigraph() {
    visitedNodes = new ArrayList<Node<T>>();
    String toReturn = "";
   
    if (tempRoot != null && isLeftRotation){
      toReturn += "tempRoot->" + tempRoot.getValue().toString() + "\n" + digraph(tempRoot);
    }
    if(tempParent != null){
      toReturn += "tempParent->" + tempParent.getValue().toString() + "\n" + digraph(tempParent);
    }
    if (root == null) {
      toReturn += "root->null\n";
    } else {
      toReturn += "root->" + root.getValue().toString() + "\n" + digraph(root);
    }
    if (tempRoot != null && !isLeftRotation){
      toReturn += "tempRoot->" + tempRoot.getValue().toString() + "\n" + digraph(tempRoot);
    }
    return toReturn;
  }

  /**
   * Return the balance factor as a string for viewing as a digraph
   * 
   * @param node The node whose balance factor we need
   * @return A string version of the balance factor. Negative numbers are
   *         prepended with an 'n' instead of a '-'
   */
  private String getDigraphBalanceFactor(Node<T> node) {
    return " " + node.getBalanceFactor();
  }

  private String toDigraphString(Node<T> node) {
    return node.getValue().toString() + getDigraphBalanceFactor(node) + " (" + node.getHeighLeft() + ", "
        + node.getHeightRight() + ")";
  }

  /**
   * Recursively get the .dot formatted definition of a node
   * 
   * @param node The node in question
   * @return The string that representds this node in the .dot language
   */

  private String digraph(Node<T> node) {
    if (visitedNodes.contains(node))
      return "";
    visitedNodes.add(node);

    if (node == null)
      return "";
    Node<T> left = node.getLeft();
    Node<T> right = node.getRight();

    String nodeValue = toDigraphString(node);// node.getValue().toString() + getDigraphBalanceFactor(node);
    String nodeName = node.getValue().toString();

    String leftValue = "";
    String rightValue = "";
    String middle = "";

    
    
      if (left == null)
        leftValue = "null_l_" + nodeName + "[label=\"left\"]\nnull_l_" + nodeName + "[label=\"null\",color=\"white\"]\n";
      else
        leftValue = left.getValue().toString() + "[label=\"left\"]\n";
      if (right == null)
        rightValue = "null_r_" + nodeName + "[label=\"right\"]\nnull_r_" + nodeName + "[label=\"null\",color=\"white\"]\n";
      else
        rightValue = right.getValue().toString() + "[label=\"right\"]\n";
      middle = nodeName + "->" + leftValue +   nodeName + "->" + rightValue;
    

    return nodeName + "[label=\"" + nodeValue + "\"];\n" + digraph(left) + middle + digraph(right);
  }

}
