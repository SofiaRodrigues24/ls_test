package pt.isel.ls.manager;

import pt.isel.ls.commands.CommandManager;

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
        if(!cmdManager.hasFileName()) {
            print();
        }
        else {
            cmdManager.createFile(this);
        }
    }
}
