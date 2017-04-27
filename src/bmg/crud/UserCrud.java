/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmg.crud;

import bmg.entities.Favoris;
import bmg.entities.Message;
import bmg.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Oussaa
 */
public class UserCrud {

    public ArrayList<User> getUserCo(String json){
        ArrayList <User> listusr = new ArrayList<>();
        
        try {

            JSONParser j = new JSONParser();
            Map<String, Object> actualite  = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) actualite.get("utilisateur");

            for (Map<String, Object> obj : list) {
               User u = new User(Integer.parseInt(obj.get("id").toString()), 
                       obj.get("nom").toString(), obj.get("prenom").toString(),obj.get("email").toString(),
                       obj.get("username").toString(), obj.get("password").toString()
               );
               listusr.add(u);
            }
        } 
         catch(IOException ex)
         {
         }
        return listusr;
}
    
}
