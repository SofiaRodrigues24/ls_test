package pt.isel.ls.representation.html;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class HTMLData {

    private static  final HashMap<String, HTMLCollections> templates = new HashMap<String, HTMLCollections>() {
        {
            put("checklist", new HTMLCollectionsChecklist("checklist"));
            put("template", new HTMLCollectionsTemplate("template"));
            put("tag", new HTMLCollectionsTag("tag"));
        }
    };



    public static <E> HTML get(String key, List<E> list) {

        HTMLCollections collections = templates.get(key);
        collections.setList(list);
        return collections;
    }
}
