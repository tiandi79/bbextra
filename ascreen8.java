package test;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen8 extends MainScreen{
    private ObjectChoiceField cfield,cfield2,cfield3,cfield6,cfield7;
    String s1 = "Preview Extra";
    String s2 = "SMS Preview :";
    String s3 = "Email Preview :";
    String s4 = "Preview tips :";
    String s5 = "Preview Font Size :";
    String s6 = "Show Contact Photo :";
    String s7 = "Save";

    public ascreen8() {
        super();
    //#ifdef SC
//#       s1 = "预览增强";
//#       s2 = "短信预览:";
//#       s3 = "邮件预览 :";
//#       s4 = "小提示:";
//#       s5 = "字体大小:";
//#       s6 = "显示联系人照片:";
//#       s7 = "保存";
          //#endif
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        PersistentObject store;
        String a[]={"Disable","Enable"};
//        String b[]={"Default","UTF-8","UTF-16BE"};
        String c[]={"Small","Middle","Large"};
        cfield = new ObjectChoiceField(s2,a,0);
        store = PersistentStore.getPersistentObject (0x3945fecf2601a023L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield.setSelectedIndex(store.getContents());
            }
        }
        cfield3 = new ObjectChoiceField(s3,a,0);
        store = PersistentStore.getPersistentObject (0xf4224d9ff8b6f205L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield3.setSelectedIndex(store.getContents());
            }
        }

        cfield2 = new ObjectChoiceField(s4,a,0);
        store = PersistentStore.getPersistentObject(0xa6970fbc46bf0f24L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield2.getChoice(1));
                 store.commit();
            }
            else {
                cfield2.setSelectedIndex(store.getContents());
            }
        }
        cfield6 = new ObjectChoiceField(s5,c,0);
        store = PersistentStore.getPersistentObject(0x6e352242e2bbdb68L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield6.setSelectedIndex(store.getContents());
            }
        }
        cfield7 = new ObjectChoiceField(s6,a,0);
        store = PersistentStore.getPersistentObject(0x5d91f892c5fdb156L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield7.getChoice(cfield7.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield7.setSelectedIndex(store.getContents());
            }
        }
        add(cfield2);
        add(cfield6);
        add(cfield7);
        add(new SeparatorField());
        add(cfield);
//        add(cfield4);
//        add(new SeparatorField());
        add(cfield3);
//        add(cfield5);
}
    private MenuItem viewItem = new MenuItem(s7, 100, 10) {
            public void run() {
            ascreen8.this.save();
            ascreen8.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x3945fecf2601a023L);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xa6970fbc46bf0f24L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xf4224d9ff8b6f205L);
        store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x6e352242e2bbdb68L);
        store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x5d91f892c5fdb156L);
        store.setContents(cfield7.getChoice(cfield7.getSelectedIndex()));
        store.commit();
     }
}