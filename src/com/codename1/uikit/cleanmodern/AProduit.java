/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.codename1.uikit.cleanmodern;

import bmg.entities.Favoris;
import bmg.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author crizby
 */
public class AProduit extends BaseForm {
    
    
    
    private final Label l2,l3,l5;
    private final TextField titleTf,authorTf;
    private final Container mainContainer;
    private final Button editBtn,backBtn;
    private Favoris currentProduit;
    
    public AProduit(String nom,String nom_image){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l2 = new Label("nom:");
        titleTf = new TextField(nom); 
        l3 = new Label("nom image:"+nom);
        authorTf = new TextField(nom_image);

        l5 = new Label("prix:");
        editBtn= new Button("ajouter au panier");
        editBtn.getUnselectedStyle().setFgColor(5542241);
      
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(titleTf);
        mainContainer.add(authorTf);
       
        
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
     
        mainContainer.add(editBtn);
       
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        currentProduit = new Favoris(nom, nom_image);
       
        this.add(BorderLayout.NORTH, mainContainer);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
