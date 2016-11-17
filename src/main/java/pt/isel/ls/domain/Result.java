package pt.isel.ls.domain;

import pt.isel.ls.manager.CommandManager;

import java.io.IOException;

public class Result<E> {

    private E  result;

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

    public void consumer(CommandManager cmdManager) throws IOException {
        String method = cmdManager.getMethod();
        if(method.equals("OPTIONS") || method.equals("POST")) {
            print();
        } else {
            Result result = cmdManager.getResultType(this);
            cmdManager.consumerResult(result);
        }
    }
}
