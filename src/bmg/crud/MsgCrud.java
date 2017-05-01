/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmg.crud;

import bmg.entities.Favoris;
import bmg.entities.Message;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oussaa
 */
public class MsgCrud {
    public ArrayList<Message> getListT(String json) {
      
        ArrayList <Message> listMsg = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> actualite  = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) actualite.get("msg");

            for (Map<String, Object> obj : list) {
               Message a = new Message(Integer.parseInt(obj.get("id").toString()), Integer.parseInt(obj.get("author").toString()), obj.get("txt").toString());
                listMsg.add(a);
            }
        } 
         catch(IOException ex)
         {
         }
        
        return listMsg;
    }
    
}
