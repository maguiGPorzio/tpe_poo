package backend.model;

public class Pair<E> {

    private final E left, right;

    public Pair(E left, E right){
        this.left = left;
        this.right = right;
    }

    public E getLeft(){
        return left;
    }

    public E getRight(){
        return right;
    }


}
