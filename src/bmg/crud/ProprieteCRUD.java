/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmg.crud;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import bmg.entities.Propriete;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import bmg.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Daly
 */
public class ProprieteCRUD {

    private ConnectionRequest connectionRequest;
    public static Form listOfProprietes;

    public void addProp(Propriete propriete) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog d = new Dialog("Ajouter une propriete");
                TextArea popupBody = new TextArea("Propriete ajoutée avec succés");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.showDialog();
            }
        };
        connectionRequest.setUrl("http://localhost/bmg/insertPropriete.php?catProp=" + propriete.getCategoriePropriete() + "&typeProp=" + propriete.getTypePropriete() + "&pays=" + propriete.getPays() + "&ville=" + propriete.getVille() + "&rue=" + propriete.getRue() + "&titre=" + propriete.getTitre() + "&prix=" + propriete.getPrix() + "&nbrChambre=" + propriete.getNbrChambre() + "&nbrVoyageur=" + propriete.getNbrVoyageur() + "&description=" + propriete.getDescription() + "&annimaux=" + "1" + "&fumeur=" + "1" + "&enfant=" + "1" + "&alcool=" + "1" + "&id_u=" + "1");

        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void removePropriete(Propriete propriete) {   // remove Propriete by id
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog d = new Dialog("Supprimer ma propriete");
                TextArea popupBody = new TextArea("Propriete supprimée avec succés");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.showDialog();
            }
        };

        connectionRequest.setUrl("http://localhost/bmg/removePropriete.php?id_p=" + propriete.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void updatePropriete(Propriete propriete) {
        connectionRequest = new ConnectionRequest() {

            @Override
            protected void postResponse() {
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Propriete mis a jour ");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.show();
            }
        };
        connectionRequest.setUrl("http://localhost/bmg/updatePropriete.php?catProp=" + propriete.getCategoriePropriete() + "&typeProp=" + propriete.getTypePropriete() + "&pays=" + propriete.getPays() + "&ville=" + propriete.getVille() + "&rue=" + propriete.getRue() + "&titre=" + propriete.getTitre() + "&prix=" + propriete.getPrix() + "&nbrChambre=" + propriete.getNbrChambre() + "&nbrVoyageur=" + propriete.getNbrVoyageur() + "&description=" + propriete.getDescription() + "&annimaux=" + "1" + "&fumeur=" + "1" + "&enfant=" + "1" + "&alcool=" + "1" + "&id_u=" + "1"+"&id_p="+propriete.getId());

        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findAllBooks() {
        connectionRequest = new ConnectionRequest() {
            List<Propriete> props = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    props.clear();

                    for (Map<String, Object> obj : content) {
                        props.add(new Propriete((String) obj.get("categoriePropriete"),(String) obj.get("pays"), (String) obj.get("ville"), (String) obj.get("rue"),(String) obj.get("titre"),(String) obj.get("prix"),(String) obj.get("nbrChambre"),(String) obj.get("nbrVoyageur"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfProprietes = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for (Propriete p : props) {
                    libsNoms.add(p.getTitre());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Propriete currentProp = props.get(uiLibsList.getCurrentSelected());
                      //  new AfficherProp(currentProp.getId(),currentProp.getCategoriePropriete(),currentProp.getPays(), currentProp.getVille(), currentProp.getRue(),currentProp.getTitre(),currentProp.getPrix(),currentProp.getNbrChambre(),currentProp.getNbrVoyageur()).show();
                    }
                });
                listOfProprietes.setLayout(new BorderLayout());
                listOfProprietes.add(BorderLayout.NORTH, uiLibsList);
                listOfProprietes.add(BorderLayout.SOUTH, Statics.createBackBtn());
                listOfProprietes.show();
            }
        };
        connectionRequest.setUrl("http://localhost/bmg/afficherPropriete.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     public ArrayList<Propriete> getListP(String json) {
            com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
      
        ArrayList <Propriete> listAct = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            

            Map<String, Object> actualite  = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) actualite.get("propriete");

            for (Map<String, Object> obj : list) {
               Propriete p = new Propriete(obj.get("pays").toString());
                                   libsNoms.add(p.getPays());

              

                listAct.add(p);
            }

        } 
         catch(IOException ex)
         {
             
         }
        
        return listAct;
               
    }


}
