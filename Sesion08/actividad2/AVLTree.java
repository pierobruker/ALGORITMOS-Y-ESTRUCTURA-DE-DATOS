package actividad2;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    @Override
    public void insert(E key) {
        root = insert((NodeAVL<E>) root, key);
    }

    private NodeAVL<E> insert(NodeAVL<E> node, E key) {
        if (node == null) {
            return new NodeAVL<>(key);
        }

        int cmp = key.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert((NodeAVL<E>) node.left, key);
            node = balanceToRight(node);
        } else if (cmp > 0) {
            node.right = insert((NodeAVL<E>) node.right, key);
            node = balanceToLeft(node);
        } else {
            System.out.println("Clave duplicada: " + key);
        }
        return node;
    }

    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        if (node == null) return null;

        node.bf++;
        if (node.bf == 2) { // desbalance a la derecha
            NodeAVL<E> hijo = (NodeAVL<E>) node.right;
            if (hijo.bf == 1) {
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
            } else if (hijo.bf == -1) {
                NodeAVL<E> nieto = (NodeAVL<E>) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
            }
        }
        return node;
    }

    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        if (node == null) return null;

        node.bf--;
        if (node.bf == -2) { // desbalance a la izquierda
            NodeAVL<E> hijo = (NodeAVL<E>) node.left;
            if (hijo.bf == -1) {
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
            } else if (hijo.bf == 1) {
                NodeAVL<E> nieto = (NodeAVL<E>) hijo.right;
                switch (nieto.bf) {
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
            }
        }
        return node;
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        System.out.println("Rotación Simple Izquierda (RSL) en nodo: " + node.data);
        NodeAVL<E> hijo = (NodeAVL<E>) node.right;
        node.right = hijo.left;
        hijo.left = node;
        System.out.println("Nuevo nodo raíz del subárbol: " + hijo.data);
        return hijo;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        System.out.println("Rotación Simple Derecha (RSR) en nodo: " + node.data);
        NodeAVL<E> hijo = (NodeAVL<E>) node.left;
        node.left = hijo.right;
        hijo.right = node;
        System.out.println("Nuevo nodo raíz del subárbol: " + hijo.data);
        return hijo;
    }


    @Override
    public void inorder() {
        inorder((NodeAVL<E>) root);
        System.out.println();
    }

    private void inorder(NodeAVL<E> node) {
        if (node != null) {
            inorder((NodeAVL<E>) node.left);
            System.out.print(node + " ");
            inorder((NodeAVL<E>) node.right);
        }
    }
    
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public void delete(E key) {
        root = delete((NodeAVL<E>) root, key);
    }

    private NodeAVL<E> delete(NodeAVL<E> node, E key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.data);

        if (cmp < 0) {
            node.left = delete((NodeAVL<E>) node.left, key);
            node = balanceToLeftAfterDelete(node);  // balance después de eliminar en subárbol izquierdo
        } else if (cmp > 0) {
            node.right = delete((NodeAVL<E>) node.right, key);
            node = balanceToRightAfterDelete(node);  // balance después de eliminar en subárbol derecho
        } else {
            // Nodo encontrado: eliminar
            if (node.left == null) return (NodeAVL<E>) node.right;
            else if (node.right == null) return (NodeAVL<E>) node.left;
            else {
                // Nodo con dos hijos: usar sucesor inorden
                NodeAVL<E> successor = minValueNode((NodeAVL<E>) node.right);
                node.data = successor.data;
                node.right = delete((NodeAVL<E>) node.right, successor.data);
                node = balanceToRightAfterDelete(node);
            }
        }
        return node;
    }

    private NodeAVL<E> minValueNode(NodeAVL<E> node) {
        NodeAVL<E> current = node;
        while (current.left != null) {
            current = (NodeAVL<E>) current.left;
        }
        return current;
    }

    // Balanceo después de eliminar en el subárbol izquierdo
    private NodeAVL<E> balanceToLeftAfterDelete(NodeAVL<E> node) {
        if (node == null) return null;

        node.bf++;
        if (node.bf == 2) {
            NodeAVL<E> hijo = (NodeAVL<E>) node.right;
            if (hijo.bf >= 0) {
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
            } else {
                NodeAVL<E> nieto = (NodeAVL<E>) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
            }
        }
        return node;
    }

    // Balanceo después de eliminar en el subárbol derecho
    private NodeAVL<E> balanceToRightAfterDelete(NodeAVL<E> node) {
        if (node == null) return null;

        node.bf--;
        if (node.bf == -2) {
            NodeAVL<E> hijo = (NodeAVL<E>) node.left;
            if (hijo.bf <= 0) {
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
            } else {
                NodeAVL<E> nieto = (NodeAVL<E>) hijo.right;
                switch (nieto.bf) {
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
            }
        }
        return node;
    }
    
    public void breadthFirstTraversal() {
        int h = height();
        for (int i = 0; i < h; i++) {
            printLevel(root, i);
        }
        System.out.println();
    }

    private void printLevel(Node<E> node, int level) {
        if (node == null) return;
        if (level == 0) {
            System.out.print(node + " ");
        } else {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }

    //5
    public void preorderTraversal() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node<E> node) {
        if (node == null) return;
        System.out.print(node + " ");
        preorder(node.left);
        preorder(node.right);
    }
}
