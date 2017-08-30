/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//#define SC
package test;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen7 extends MainScreen{
    private ObjectChoiceField cfield,cfield2,cfield3;
    String s1 = "Firewall Extra";
    String s2 = "Phone Firewall :";
    String s3 = "Do Action :";
    String s4 = "Block When Calling:";
    String s5 = "Phone Blacklist";
    String s6 = "Phone Whitelist";
    String s7 = "Save";

    public ascreen7()  {
        super();
        //#ifdef SC
      s1 = "防火墙增强";
      s2 = "电话防火墙:";
      s3 = "操作 :";
      s4 = "阻止来电:";
      s5 = "电话黑名单";
      s6 = "电话白名单";
      s7 = "保存";
          //#endif
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        PersistentObject store;
        String a[]={"Disable","Enable"};
        String b[]={"Hangup","Mute"};
        String c[]={"In Blacklist","Not In Addressbook","Not In Whitelist"};
        cfield = new ObjectChoiceField(s2,a,0);
        store = PersistentStore.getPersistentObject (0x1d0e86de3c5a9540L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield.setSelectedIndex(store.getContents());
            }
        }
        add(cfield);
        cfield2 = new ObjectChoiceField(s3,b,0);
        store = PersistentStore.getPersistentObject (0x860e202733f13780L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield2.setSelectedIndex(store.getContents());
            }
        }
        add(cfield2);
        cfield3 = new ObjectChoiceField(s4,c,0);
        store = PersistentStore.getPersistentObject (0xbe60c7c34967bf0aL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield3.setSelectedIndex(store.getContents());
            }
        }
        add(cfield3);
        ButtonField pblacklist = new ButtonField(s5,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER );
        add(pblacklist);
        pblacklist.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                UiApplication.getUiApplication().pushScreen(new blacklistscreen());
            }
        });
        ButtonField pwhitelist = new ButtonField(s6,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER );
        add(pwhitelist);
        pwhitelist.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                UiApplication.getUiApplication().pushScreen(new whitelistscreen());
            }
        });
    }
private MenuItem viewItem = new MenuItem(s7, 100, 10) {
            public void run() {
            ascreen7.this.save();
            ascreen7.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x1d0e86de3c5a9540L);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x860e202733f13780L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xbe60c7c34967bf0aL);
        store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
        store.commit();
}
}
class blacklistscreen extends MainScreen {
     EditField e1;
     String s1 = "Please add phone numbers per line and not to leave a blank line.\n";
    public blacklistscreen()  {
        super();
        //#ifdef SC
         s1 = "请在每一行添加一个电话号码，不要留空行。";
          //#endif
        LabelField title = new LabelField("Blacklist",LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        add(new LabelField(s1));
        add(new SeparatorField());
        PersistentObject store;
        e1 = new EditField();
        store = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(e1.getText());
                 store.commit();
            }
            else {
                e1.setText( (String) store.getContents());
            }
        }
        add(e1);
    }
    private MenuItem viewItem = new MenuItem("Save", 100, 10) {
            public void run() {
            blacklistscreen.this.save();
            blacklistscreen.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
        store.setContents(e1.getText());
        store.commit();
}
}
class whitelistscreen extends MainScreen {
     EditField e1;
     String s1 = "Please add phone numbers per line and not to leave a blank line.\n";
    public whitelistscreen()  {
        super();
          //#ifdef SC
         s1 = "请在每一行添加一个电话号码，不要留空行。";
          //#endif
        LabelField title = new LabelField("Whitelist",LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        add(new LabelField(s1));
        add(new SeparatorField());
        PersistentObject store;
        e1 = new EditField();
        store = PersistentStore.getPersistentObject (0x4dd59dd49bb680c7L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(e1.getText());
                 store.commit();
            }
            else {
                e1.setText( (String) store.getContents());
            }
        }
        add(e1);
    }
    private MenuItem viewItem = new MenuItem("Save", 100, 10) {
            public void run() {
            whitelistscreen.this.save();
            whitelistscreen.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x4dd59dd49bb680c7L);
        store.setContents(e1.getText());
        store.commit();
}
}