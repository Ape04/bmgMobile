/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmg.crud;

import bmg.entities.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Oussaa
 */
public class UserCrud {

    public void getUserCo(String username, String password) {
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codenameone/userCo.php?username=" + "\'" + username + "\'" + "&password=" + "\'" + password + "\'" + "");
        
        con.addResponseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
            
                try {
                    NetworkEvent event = (NetworkEvent) ev;
                    byte[] data = (byte[]) event.getMetaData();
                    String s = new String(data, "UTF-8");
                    JSONObject obj = new JSONObject(s);

                    JSONObject u = obj.getJSONObject("utilisateur");
                    int id = u.getInt("id");
                    UserCo.userCo=id;

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
        NetworkManager.getInstance().addToQueue(con);
    }
}
