package com.codename1.uikit.cleanmodern;

import bmg.crud.MsgCrud;
import bmg.crud.UserCo;
import bmg.entities.Message;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Oussaa
 */
public final class MsgForm {

    private Image userPicture;
    private TTS tts;
    private Resources theme;
    Resources res;
    public static Message lastMsg;
    private ConnectionRequest cnx;
    private MsgCrud mc;

    public MsgForm(int idu) {

        theme = UIManager.initFirstTheme("/themeMsg");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        userPicture = theme.getImage("duke_iphone.png");
        tts = (TTS) NativeLookup.create(TTS.class);

        showSbaitso(idu);
    }

    private DataChangedListener createSearchListener(final TextField searchField, final Container discussion, final Button ask) {
        return (type, index) -> {
            String t = searchField.getText();
            int count = discussion.getComponentCount();
            if (t.length() == 0) {
                ask.setEnabled(true);
                for (Component c : discussion) {
                    c.setHidden(false);
                    c.setVisible(true);
                }
                animateChanage(discussion);
                return;
            }
            t = t.toLowerCase();
            ask.setEnabled(false);
            for (Component c : discussion) {
                SpanLabel tt = (SpanLabel) c;
                if (tt.getText().toLowerCase().indexOf(t) < 0) {
                    tt.setHidden(true);
                    tt.setVisible(false);
                } else {
                    tt.setHidden(false);
                    tt.setVisible(true);
                }
            }
            animateChanage(discussion);
        };
    }

    private boolean animateLock;

    void animateChanage(Container discussion) {
        if (!animateLock) {
            animateLock = true;
            discussion.animateLayoutAndWait(300);
            animateLock = false;
        }
    }

    void showSbaitso(int id) {
        mc = new MsgCrud();
        Form sb = new Form(new BorderLayout());
        sb.setFormBottomPaddingEditingMode(true);
        Toolbar t = sb.getToolbar();

        t.setBackCommand(new Command("", FontImage.MATERIAL_ARROW_BACK) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Toolbar.setGlobalToolbar(false);
                res = UIManager.initFirstTheme("/theme");
                new NewsfeedForm(res).show();
                Display.getInstance().getCurrent().refreshTheme();
                sb.revalidate();
            }
        });

        final TextField searchField = new TextField("", "Search in Discussion...", 20, TextField.ANY);
        t.setTitleComponent(searchField);
        final TextField ask = new TextField("", "Type in your message", 20, TextField.ANY);
        Button askButton = new Button("Ask");
        final Container discussion = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        sb.add(BorderLayout.SOUTH, BorderLayout.center(ask).
                add(BorderLayout.EAST, askButton)).
                add(BorderLayout.CENTER, discussion);

        discussion.setScrollableY(true);

        sb.show();

        Display.getInstance().callSerially(() -> {

            cnx = new ConnectionRequest();
            cnx.setUrl("http://localhost/Codenameone/SelectM?idco=" + UserCo.userCo.getId_u() + "&idr=" + id + "");

            NetworkManager.getInstance().addToQueue(cnx);
            cnx.addResponseListener(new ActionListener() {

                public void actionPerformed(ActionEvent ev) {
                    try {
                        for (Message m : mc.getListT(new String(cnx.getResponseData()))) {

                            if (m.getAuthor() == id) {
                                say(discussion, m.getContenu(), false);
                                lastMsg = mc.getListT(new String(cnx.getResponseData())).get(mc.getListT(new String(cnx.getResponseData())).size() - 1);
                            } else {
                                say(discussion, m.getContenu(), true);
                            }
                            if (tts != null && tts.isSupported()) {
                                tts.say(m.getContenu());
                            }
                        }
                    } catch (Exception ex) {
                        say(discussion, "You still haven't initiated a conversation", false);
                        discussion.animateLayoutAndWait(6000);
                        discussion.removeAll();

                    }
                }
            });
        });

        //pull to refresh
        discussion.addPullToRefresh(new Runnable() {
            @Override
            public void run() {

                cnx.setUrl("http://localhost/Codenameone/SelectM?idco=" + UserCo.userCo.getId_u() + "&idr=" + id + "");
                NetworkManager.getInstance().addToQueue(cnx);
                cnx.addResponseListener(new ActionListener() {

                    public void actionPerformed(ActionEvent ev) {
                        try {
                            for (Message m : mc.getListT(new String(cnx.getResponseData()))) {
                                if (m.getId_m() > lastMsg.getId_m()) {
                                    if (m.getAuthor() == id) {
                                        say(discussion, m.getContenu(), false);
                                    } else {
                                        say(discussion, m.getContenu(), true);
                                    }
                                    if (tts != null && tts.isSupported()) {
                                        tts.say(m.getContenu());
                                    }
                                }
                            }
                        } catch (Exception exe) {
                            say(discussion, "You still haven't initiated a conversation", false);
                            discussion.animateLayoutAndWait(6000);
                            discussion.removeAll();

                        }
                    }
                });
                discussion.removeAll();
            }
        });

        searchField.addDataChangeListener(createSearchListener(searchField, discussion, askButton));
        askButton.addActionListener(e -> {
            ConnectionRequest connectionRequest = new ConnectionRequest();
            connectionRequest.setUrl("http://localhost/Codenameone/insertMsg?author='" + "1'" + "&addressee='" + id + "'&txt='" + ask.getText() + "'");
            NetworkManager.getInstance().addToQueue(connectionRequest);
        });

        ActionListener askEvent = (e) -> {
            String t1 = ask.getText();
            if (t1.length() > 0) {
                ask.setText("");
                say(discussion, t1, true);
            }
        };
        ask.setDoneListener(askEvent);
        askButton.addActionListener(askEvent);

    }

    void say(Container destination, String text, boolean question) {
        SpanLabel t = new SpanLabel(text);
        t.setWidth(destination.getWidth());
        t.setX(0);
        t.setHeight(t.getPreferredH());

        if (question) {
            t.setY(Display.getInstance().getDisplayHeight());
            t.setTextUIID("BubbleUser");
            t.setIconPosition(BorderLayout.EAST);
            t.setTextBlockAlign(Component.RIGHT);
        } else {
            t.setY(0);
            t.setTextUIID("BubbleSbaitso");
            t.setTextBlockAlign(Component.LEFT);
        }
        destination.add(t);
        destination.animateLayoutAndWait(100);
        destination.scrollComponentToVisible(t);
    }
}
