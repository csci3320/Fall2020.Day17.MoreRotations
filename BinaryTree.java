
public class BinaryTree<T extends Comparable> extends Digraphable<T> {

    /**
     * Constructor. Set the root to null
     */
    public BinaryTree() {
        super();
    }

    /**
     * Returns the string that represents this binary tree in the .dot language
     */

    /**
     * Add a new entry to the tree
     * 
     */
    public void add(T toAdd) {
        // Wrap the value T in a new Node object
        Node<T> node = new Node<T>(toAdd);

        if (root == null) {
            // If it's the first entry, make it the root
            root = node;
        } else {
            // Otherwise, add it recursively
            addNode(root, node);
        }
        boolean didBalance = false;
        do {
            calculateBalance(root);
            Main.viewTree(this);
            didBalance = balanceRoot();
        } while (didBalance);
        calculateBalance(root);

    }

    private boolean balanceRoot() {
        if (root == null)
            return false;
        // We need to balance this node
        if (root.getBalanceFactor() > 1 || root.getBalanceFactor() < -1) {
            if (root.getBalanceFactor() < 0) {
                isLeftRotation = false;
                // We need to do a right rotation
                // 1. Get a new reference to the root
                Main.viewTree(this);
                tempRoot = root;
                Main.viewTree(this);
                // 2. Move the root reference to the left child
                root = root.getLeft();
                Main.viewTree(this);

                tempRoot.setLeft(root.getRight());
                Main.viewTree(this);

                root.setRight(tempRoot);
                Main.viewTree(this);

                tempRoot = null;
            }
            if (root.getBalanceFactor() > 0) {
                isLeftRotation = true;
                // 1. Get a new reference to the root
                Main.viewTree(this);
                tempRoot = root;
                Main.viewTree(this);
                // 2. Move the root reference to the left child
                root = root.getRight();
                Main.viewTree(this);

                tempRoot.setRight(root.getLeft());
                Main.viewTree(this);

                root.setLeft(tempRoot);
                Main.viewTree(this);

                tempRoot = null;
            }
            return true;
        }
        if (balance(root.getLeft(), root, true))
            return true;
        if (balance(root.getRight(), root, false))
            return true;
        return false;

    }

    private boolean balance(Node<T> node, Node<T> parent, boolean fromParentLeft) {
        if (node == null)
            return false;
        // We need to balance this node
        if (node.getBalanceFactor() > 1 || node.getBalanceFactor() < -1) {
            if (node.getBalanceFactor() < 0) {
                isLeftRotation = false;
                // We need to do a right rotation
                // 1. Get a new reference to the root
                Main.viewTree(this);
                tempRoot = node;
                Main.viewTree(this);
                // 2. Move the root reference to the left child
                if (fromParentLeft)
                    parent.setLeft(node.getLeft());
                else
                    parent.setRight(node.getLeft());
                Main.viewTree(this);

                tempRoot.setLeft(parent.getRight());
                Main.viewTree(this);

                parent.setRight(tempRoot);
                Main.viewTree(this);

                tempRoot = null;
            }
            if (node.getBalanceFactor() > 0) {
                isLeftRotation = true;
                // 1. Get a new reference to the root
                Main.viewTree(this);
                tempRoot = node;
                Main.viewTree(this);
                // 2. Move the root reference to the left child
                if (fromParentLeft)
                    parent.setLeft(node.getRight());
                else
                    parent.setRight(node.getRight());
                Main.viewTree(this);

                tempRoot.setRight(node.getLeft());
                Main.viewTree(this);

                parent.setLeft(tempRoot);
                Main.viewTree(this);

                tempRoot = null;
            }
            return true;
        }
        if (balance(node.getLeft(), node, true))
            return true;
        if (balance(node.getRight(), node, false))
            return true;
        return false;
    }

    private int calculateBalance(Node<T> parentNode) {
        if (parentNode == null)
            return 0;
        int heightLeft = calculateBalance(parentNode.getLeft());
        int heightRight = calculateBalance(parentNode.getRight());
        parentNode.setHeightLeft((heightLeft));
        parentNode.setHeightRight((heightRight));
        parentNode.setBalanceFactor(heightRight - heightLeft);
        return Math.max(heightLeft, heightRight) + 1;
    }

    /**
     * Recursively add a node to our tree
     * 
     * @param parentNode the node that needs to place the new node
     * @param toAdd      The new node
     */
    private void addNode(Node<T> parentNode, Node<T> toAdd) {
        if (toAdd.getValue().compareTo(parentNode.getValue()) <= 0) {

            // Go left
            if (parentNode.getLeft() == null) {
                parentNode.addBalanceLeft();
                parentNode.setLeft(toAdd);
            } else {
                addNode(parentNode.getLeft(), toAdd);
            }
        } else {
            // Go right
            if (parentNode.getRight() == null) {
                parentNode.addBalanceRight();
                parentNode.setRight(toAdd);
            } else {
                addNode(parentNode.getRight(), toAdd);
            }
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * Recursively find the number of nodes below this node and this node
     * 
     * @param parent The node in question
     * @return The number node below this node plus itself (if it existis)
     */
    private int size(Node<T> parent) {
        if (parent == null)
            return 0;
        int leftSize = size(parent.getLeft());
        int rightSize = size(parent.getRight());
        return leftSize + rightSize + 1;
    }

    /**
     * True if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Remove a node from the tree whose value matches the provided value
     */
    public void remove(T toRemove) {

    }

    /**
     * True if a node in the true has a value that matches the one provided. False
     * otherwise.
     */
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> parent, T element) {
        if (parent == null)
            return false;
        boolean left = contains(parent.getLeft(), element);
        boolean right = contains(parent.getRight(), element);
        if (left || right)
            return true;
        if (parent.getValue().equals(element)) {
            return true;
        }
        return false;
    }

}
