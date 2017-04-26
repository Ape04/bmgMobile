/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmg.crud;

import bmg.entities.Favoris;
import bmg.entities.User;
import bmg.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.AProduit;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Oussaa
 */
public class FavorisCrud {
    
    private ConnectionRequest connectionRequest;
    public static Form listOfFavoris;
    
    
    
    
      public void findAllBooks(){
        connectionRequest = new ConnectionRequest() {
        java.util.List<Favoris> favoris = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) data.get("favoris");
                    favoris.clear();
                  
                    for (Map<String, Object> obj : content) {
                        favoris.add(new Favoris((String) obj.get("firstname"),(String) obj.get("lastname"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                Resources res;
                listOfFavoris = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Favoris l :favoris){
                    libsNoms.add(l.getFirstname());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       Favoris currentProduit = favoris.get(uiLibsList.getCurrentSelected());
                        new AProduit (currentProduit.getFirstname(),currentProduit.getLastname()).show();

                    }
                });
                listOfFavoris.setLayout(new BorderLayout());
                listOfFavoris.add(BorderLayout.NORTH,uiLibsList);
                listOfFavoris.add(BorderLayout.SOUTH,Statics.createBackBtn());
                listOfFavoris.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/Codenameone/selectF.php?id_user=1");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        
    }
      
      public ArrayList<Favoris> getListT(String json) {
      
        ArrayList <Favoris> listAct = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> actualite  = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) actualite.get("favoris");

            for (Map<String, Object> obj : list) {
               Favoris a = new Favoris(obj.get("firstname").toString(), obj.get("lastname").toString());
              

                listAct.add(a);
            }

        } 
         catch(IOException ex)
         {
             
         }
        
        return listAct;

    }

}
