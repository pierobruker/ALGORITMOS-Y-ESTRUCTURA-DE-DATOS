package btree;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int order;
    private boolean up;
    private BNode<E> nDes;
    private int nodeIdCounter; 

    public BTree(int order) {
        this.order = order;
        this.root = null;
        this.nodeIdCounter = 0;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // Inserta una clave en el árbol
    public void insert(E key) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, key);
        if (up) {
            pnew = new BNode<>(this.order, nodeIdCounter++);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    // Método recursivo para insertar una clave en el árbol
    private E push(BNode<E> current, E key) {
        int[] pos = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return key;
        } else {
            boolean found = current.searchNode(key, pos);
            if (found) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), key);
            if (up) {
                if (current.nodeFull(this.order - 1)) {
                    mediana = divideNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }
            return mediana;
        }
    }

    // Dividir el nodo cuando está lleno
    private E divideNode(BNode<E> current, E key, int k) {
        BNode<E> newNode = nDes;
        int midPos = (k <= this.order / 2) ? this.order / 2 : this.order / 2 + 1;
        nDes = new BNode<>(this.order, nodeIdCounter++);
        for (int i = midPos; i < this.order - 1; i++) {
            nDes.keys.set(i - midPos, current.keys.get(i));
            nDes.childs.set(i - midPos + 1, current.childs.get(i + 1));
        }
        nDes.count = this.order - 1 - midPos;
        current.count = midPos;

        if (k <= this.order / 2) {
            putNode(current, key, newNode, k);
        } else {
            putNode(nDes, key, newNode, k - midPos);
        }

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // Insertar una clave en un nodo
    private void putNode(BNode<E> current, E key, BNode<E> right, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, key);
        current.childs.set(k + 1, right);
        current.count++;
    }

    // Mostrar el árbol
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("BTree is empty...");
        } else {
            sb.append(writeTree(this.root));
        }
        return sb.toString();
    }

    // Recorrer el árbol y generar la representación en cadena
    private String writeTree(BNode<E> current) {
        StringBuilder sb = new StringBuilder();
        sb.append(current.toString()).append("\n");
        for (BNode<E> child : current.childs) {
            if (child != null) {
                sb.append(writeTree(child));
            }
        }
        return sb.toString();
    }

    //ejercicio 1
    public Boolean search(E cl){
        return searchNode(root, cl); 
    }
    private boolean searchNode(BNode <E> current, E cl){
        if( current == null){
            return false;
        }
        int [] pos = new int [1];
        boolean found = current.searchNode(cl, pos);
        if(found){
            System.out.println(cl+" se encuentra el nodo "+current.idNode+" en la posicion "+pos[0]);
            return true;
        }
        if( pos[0] == current.count){
            return searchNode(current.childs.get(pos[0]),cl);
        } else {
            return searchNode(current.childs.get(pos[0]),cl);
        }
        
    }

//EJERCICIO 2
public void remove(E cl) {
    if (root == null) {
        System.out.println("El árbol está vacío.");
        return;
    }

    removeNode(root, cl);
}

private void removeNode(BNode<E> current, E cl) {
    int[] pos = new int[1];
    boolean found = current.searchNode(cl, pos);

    if (found) {
        if (current.childs.get(0) == null) {
            current.keys.remove(pos[0]);
            current.count--;
        } else {
            E successor = getSuccessor(current, pos[0]);
            current.keys.set(pos[0], successor);
            removeNode(current.childs.get(pos[0]), successor);
        }
    } else {
        // Si la clave no se encuentra en el nodo actual, descendemos al hijo correspondiente
        BNode<E> childNode = current.childs.get(pos[0]);
        if (childNode != null) {
            removeNode(childNode, cl);

            if (childNode.count < (order / 2)) {
                fixNodeAfterDeletion(current, pos[0]);
            }
        }
    }
}

private E getSuccessor(BNode<E> current, int pos) {
    BNode<E> successorNode = current.childs.get(pos + 1);
    while (successorNode.childs.get(0) != null) {
        successorNode = successorNode.childs.get(0);
    }
    return successorNode.keys.get(0);
}

// Método para manejar la redistribución o fusión de nodos después de la eliminación
private void fixNodeAfterDeletion(BNode<E> parent, int childPos) {
    BNode<E> childNode = parent.childs.get(childPos);
    BNode<E> leftSibling = (childPos > 0) ? parent.childs.get(childPos - 1) : null;
    BNode<E> rightSibling = (childPos < parent.count) ? parent.childs.get(childPos + 1) : null;

    // Caso 1: Redistribución con el hermano izquierdo
    if (leftSibling != null && leftSibling.count > (order / 2)) {
        redistributeWithLeftSibling(parent, childPos);
    }
    // Caso 2: Redistribución con el hermano derecho
    else if (rightSibling != null && rightSibling.count > (order / 2)) {
        redistributeWithRightSibling(parent, childPos);
    }
    // Caso 3: Fusión de nodos
    else {
        if (leftSibling != null) {
            mergeWithLeftSibling(parent, childPos);
        } else {
            mergeWithRightSibling(parent, childPos);
        }
    }
    // Si la raíz quedó vacía, bajamos un nivel
    if (parent == root && root.count == 0) {
        root = root.childs.get(0);
    }
}

// Redistribuir con el hermano izquierdo
private void redistributeWithLeftSibling(BNode<E> parent, int childPos) {
    BNode<E> childNode = parent.childs.get(childPos);
    BNode<E> leftSibling = parent.childs.get(childPos - 1);

    // Mover una clave del padre al hermano izquierdo
    leftSibling.keys.add(parent.keys.get(childPos - 1));
    parent.keys.set(childPos - 1, childNode.keys.get(0));

    // Mover un hijo del hermano izquierdo al hijo
    childNode.keys.remove(0);
    childNode.count--;

    // Si el hijo tiene hijos, movemos el hijo correspondiente
    if (childNode.childs.get(0) != null) {
        leftSibling.childs.add(childNode.childs.remove(0));
    }
    childNode.count--;
}

// Redistribuir con el hermano derecho
private void redistributeWithRightSibling(BNode<E> parent, int childPos) {
    BNode<E> childNode = parent.childs.get(childPos);
    BNode<E> rightSibling = parent.childs.get(childPos + 1);

    // Mover una clave del padre al hermano derecho
    rightSibling.keys.add(0, parent.keys.get(childPos));
    parent.keys.set(childPos, childNode.keys.get(childNode.count - 1));

    // Mover un hijo del hermano derecho al hijo
    if (childNode.childs.get(0) != null) {
        childNode.childs.add(rightSibling.childs.remove(0));
    }

    childNode.keys.remove(childNode.count - 1);
    childNode.count--;
}

// Fusionar el nodo con el hermano izquierdo
private void mergeWithLeftSibling(BNode<E> parent, int childPos) {
    BNode<E> childNode = parent.childs.get(childPos);
    BNode<E> leftSibling = parent.childs.get(childPos - 1);

    leftSibling.keys.add(parent.keys.get(childPos - 1));
    parent.keys.remove(childPos - 1);
    parent.count--;

    leftSibling.keys.addAll(childNode.keys);
    leftSibling.childs.addAll(childNode.childs);

    parent.childs.remove(childPos);
    childNode = null;
}

// Fusionar el nodo con el hermano derecho
private void mergeWithRightSibling(BNode<E> parent, int childPos) {
    BNode<E> childNode = parent.childs.get(childPos);
    BNode<E> rightSibling = parent.childs.get(childPos + 1);

    // Mover la clave del padre al hermano derecho
    childNode.keys.add(parent.keys.get(childPos));
    parent.keys.remove(childPos);
    parent.count--;

    // Mover todas las claves y los hijos al hermano derecho
    childNode.keys.addAll(rightSibling.keys);
    childNode.childs.addAll(rightSibling.childs);

    parent.childs.remove(childPos + 1);
    rightSibling = null;
}


//MÉTODO building_Btree
public static BTree<Integer> building_Btree(String filename) throws ItemNoFound {
 try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
     int order = Integer.parseInt(br.readLine().trim());
     BTree<Integer> tree = new BTree<>(order);

     Map<Integer, BNode<Integer>> nodes = new HashMap<>();
     Map<Integer, Integer> levels = new HashMap<>();

     String line;
     while ((line = br.readLine()) != null) {
         String[] parts = line.split(",");
         int level = Integer.parseInt(parts[0].trim());
         int idNode = Integer.parseInt(parts[1].trim());

         BNode<Integer> node = new BNode<>(order, idNode);
         node.count = parts.length - 2;
         for (int i = 2; i < parts.length; i++) {
             node.keys.set(i - 2, Integer.parseInt(parts[i].trim()));
         }

         nodes.put(idNode, node);
         levels.put(idNode, level);
     }

     // Identificar la raíz (nivel 0)
     Integer rootId = levels.entrySet()
             .stream()
             .filter(e -> e.getValue() == 0)
             .map(Map.Entry::getKey)
             .findFirst()
             .orElseThrow(() -> new ItemNoFound("No se encontró nodo raíz"));

     tree.root = nodes.get(rootId);

     // Conectar hijos
     for (Map.Entry<Integer, BNode<Integer>> entry : nodes.entrySet()) {
         int parentId = entry.getKey();
         int parentLevel = levels.get(parentId);
         BNode<Integer> parentNode = entry.getValue();

         int childIndex = 0;
         for (Map.Entry<Integer, BNode<Integer>> subEntry : nodes.entrySet()) {
             int childId = subEntry.getKey();
             int childLevel = levels.get(childId);
             if (childLevel == parentLevel + 1) {
                 if (childIndex <= parentNode.count) {
                     parentNode.childs.set(childIndex, nodes.get(childId));
                     childIndex++;
                 }
             }
         }
     }

     // Validar el árbol
     if (!tree.validateBTree()) {
         throw new ItemNoFound("El árbol no cumple las propiedades del B-Tree");
     }

	     return tree;
	 } catch (IOException e) {
	     throw new ItemNoFound("Error al leer el archivo: " + e.getMessage());
	 } catch (NumberFormatException e) {
	     throw new ItemNoFound("Formato incorrecto en el archivo: " + e.getMessage());
	 }
}

//MÉTODO validateBTree
	public boolean validateBTree() {
	    if (root == null) return true;
	    return validateNode(root, 0, new int[]{-1});
	}
	
	private boolean validateNode(BNode<E> node, int currentLevel, int[] leafLevel) {
	    int minKeys = (node == root) ? 1 : (int) Math.ceil(this.order / 2.0) - 1;
	
	    if (node.count < minKeys && node != root) return false;
	    if (node.count > this.order - 1) return false;
	
	    for (int i = 1; i < node.count; i++) {
	        if (node.keys.get(i - 1).compareTo(node.keys.get(i)) >= 0) return false;
	    }
	
	    boolean isLeaf = true;
	    for (BNode<E> child : node.childs) {
	        if (child != null) {
	            isLeaf = false;
	            break;
	        }
	    }
	
	    if (isLeaf) {
	        if (leafLevel[0] == -1) {
	            leafLevel[0] = currentLevel;
	        } else if (leafLevel[0] != currentLevel) {
	            return false;
	        }
	    } else {
	        for (int i = 0; i <= node.count; i++) {
	            BNode<E> child = node.childs.get(i);
	            if (child != null) {
	                if (!validateNode(child, currentLevel + 1, leafLevel)) return false;
	            } else {
	                for (int j = i + 1; j <= node.count; j++) {
	                    if (node.childs.get(j) != null) return false;
	                }
	            }
	        }
	    }
	
	    return true;
	}

	//Ejercicio 4
	public String buscarNombre(int codigo) {
	    RegistroEstudiante dummy = new RegistroEstudiante(codigo, "");
	    E result = buscarRegistro(root, (E) dummy);
	    if (result != null) {
	        return ((RegistroEstudiante) result).getNombre();
	    }
	    return "No encontrado";
	}

	private E buscarRegistro(BNode<E> node, E target) {
	    if (node == null) return null;
	    int[] pos = new int[1];
	    boolean found = node.searchNode(target, pos);
	    if (found) {
	        return node.keys.get(pos[0]);
	    }
	    return buscarRegistro(node.childs.get(pos[0]), target);
	}

	
}



