

package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import bmg.crud.ProprieteCRUD;
import bmg.entities.Propriete;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class AjouterPropForm extends BaseForm {

    public AjouterPropForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField categorieTf = new TextField("", "Categorie", 20, TextField.ANY);
      /*  ComboBox comboBox = new ComboBox("","combobox");
        comboBox.addItem("Appartement");
        comboBox.addItem("Maison");
*/
        TextField typeTf = new TextField("", "Type", 20, TextField.ANY);
        TextField paysTf = new TextField("", "Pays", 20, TextField.ANY);
        TextField villeTf = new TextField("", "Ville", 20, TextField.ANY);
        TextField rueTf = new TextField("", "Rue", 20, TextField.ANY);
        TextField titreTf = new TextField("", "Titre", 20, TextField.ANY);
        TextField prixTf = new TextField("", "Prix", 20, TextField.ANY);
        TextField nbrVoyageurTf = new TextField("", "Nombre voyageur", 20, TextField.ANY);
        TextField nbrChambreTf = new TextField("", "Nombre Chambre", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.ANY);

        categorieTf.setSingleLineTextArea(false);
        typeTf.setSingleLineTextArea(false);
        paysTf.setSingleLineTextArea(false);
        villeTf.setSingleLineTextArea(false);
        rueTf.setSingleLineTextArea(false);
        titreTf.setSingleLineTextArea(false);
        prixTf.setSingleLineTextArea(false);
        nbrVoyageurTf.setSingleLineTextArea(false);
        nbrChambreTf.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);

        Button ajouter = new Button("Ajouter");
        ajouter.setUIID("Link");
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter une propriete", "LogoLabel"),
                new FloatingHint(categorieTf),
                createLineSeparator(),
                new FloatingHint(typeTf),
                createLineSeparator(),
                new FloatingHint(paysTf),
                createLineSeparator(),
                new FloatingHint(villeTf),
                createLineSeparator(),
                new FloatingHint(rueTf),
                createLineSeparator(),
                new FloatingHint(titreTf),
                createLineSeparator(),
                new FloatingHint(prixTf),
                createLineSeparator(),
                new FloatingHint(nbrVoyageurTf),
                createLineSeparator(),
                new FloatingHint(nbrChambreTf),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                FlowLayout.encloseCenter(ajouter)
        ));
        ajouter.requestFocus();
                ajouter.addActionListener(new ActionListener() {

                @Override
            public void actionPerformed(ActionEvent evt) {
                Propriete typedProp = new Propriete(categorieTf.getText(), typeTf.getText(), paysTf.getText(), villeTf.getText(), rueTf.getText(), titreTf.getText(),prixTf.getText(),nbrVoyageurTf.getText(),nbrChambreTf.getText(), description.getText());
            new ProprieteCRUD().addProp(typedProp);
            }
        });    }
    
}
