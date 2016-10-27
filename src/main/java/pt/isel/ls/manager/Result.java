package pt.isel.ls.manager;

import java.util.HashMap;

public class Result<E> {

    private E result;

    public Result(E result) {
        this.result = result;
    }

    public E getResult() {
        return result;
    }

    /**
     * Presents the result on the standard output.
     */
    public void print() {
        System.out.println(result.toString());
    }

}
