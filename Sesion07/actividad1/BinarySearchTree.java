package actividad1;

import Exeptions.ExceptionIsEmpty;
import Exeptions.ItemDuplicated;
import Exeptions.ItemNoFound;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();    
}
