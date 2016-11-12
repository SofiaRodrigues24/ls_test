package pt.isel.ls.domain;


import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.html.HTMLData;
import pt.isel.ls.representation.json.JSONArray;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.plain.TextPlain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Collections<E> extends ObjectRepresentation {

   private List<? extends ObjectRepresentation> list;
   private String type;

   public Collections(String type, List<? extends ObjectRepresentation> list) {
      this.type = type;
      this.list = list;
   }

   public List<? extends ObjectRepresentation> getList() {
      return list;
   }

   @Override
   public JSONObject getJsonObject() throws IOException {
      JSONObject jo = new JSONObject();
      JSONArray ja = new JSONArray();

      Iterator<? extends ObjectRepresentation> iter = list.iterator();
      while (iter.hasNext()) {
         ja.add(iter.next().getJsonObject());
      }

      jo.add("class", new JSONArray()
              .add(type)
              .add("collection")
      )
              .add("properties", new JSONObject()
              .add("count", list.size()))
              .add("entities",ja);

      return jo;
   }

   @Override
   public HTML getHTML() {
      return HTMLData.get(type, list);
   }


   @Override
   public String toString() {
      return list.toString();
   }

    @Override
    public TextPlain getTextPlain() {
        return new TextPlain(toString());
    }

}
