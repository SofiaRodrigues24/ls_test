package pt.isel.ls.representation.html;

import java.util.List;

@SuppressWarnings("unchecked")
public abstract class HTMLCollections<E> extends HTML{

    protected abstract <E> void setList(List<E> list);
}
