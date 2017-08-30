/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen3 extends MainScreen {
    ObjectChoiceField cfield,cfield2;
    String ext[]={"jpg","png","gif","bmp"};
    String ext2[]={"mp3","midi","acc","wma"};
    DateField myclock,myclock2;
    String s1 = "Charging Extra";
    String s2 = "When Charging :";
    String s3 = "  Display Image:";
    String s4 = "  Choose an image file:    ";
    String s5 = "Finish Charging :";
    String s6 = "  Notify:";
    String s7 = "  Play Sound:                ";
    String s8 = "  Time Available:";
    String s9 = "    From";
    String s10 = "    To";
    String s11 = "Save";

public ascreen3 (){
        super();
//#ifdef SC
//#       s1 = "充电增强";
//#       s2 = "当充电时 :";
//#       s3 = "  显示图片:";
//#       s4 = "  选择一张图片:    ";
//#       s5 = "完成充电时 :";
//#       s6 = "  提示:";
//#       s7 = "  提示音:                ";
//#       s8 = "  有效时间段:";
//#       s9 = "    从";
//#       s10 = "    到";
//#       s11 = "保存";
          //#endif
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        add(new LabelField(s2));
        add(new SeparatorField());
        String a[]={"Disable","Enable"};
        cfield = new ObjectChoiceField(s3,a,0);
        add(cfield);
        PersistentObject store;
        // menu 1
        store = PersistentStore.getPersistentObject (0x42f7439cfea21673L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        //         store.commit();
            }
            else {
                cfield.setSelectedIndex(store.getContents());
            }
        }
        // menu 2
        final LabelField path = new LabelField();
        store = PersistentStore.getPersistentObject (0x75a21d7e57dbebbL);
        synchronized (store) {
            if (store.getContents() == null) {
                 path.setText("  Image Path: Null");
            }
            else {
                  path.setText("  Image Path:"+store.getContents());
                  globel.theFile = (String) store.getContents();
            }
        }

        HorizontalFieldManager hfield = new HorizontalFieldManager();
        LabelField lfield = new LabelField(s4,LabelField.FIELD_HCENTER);
        ButtonField mychoose = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        hfield.add(lfield);
        add(hfield);
        add(mychoose);
        add(path);
        mychoose.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext);
                fps.pickFile();
                globel.theFile = fps.getFile();
                if(globel.theFile != null)
                    path.setText("  Image Path:"+globel.theFile);
                else
                   path.setText("  Image Path: Null");
            }
        });
        add(new SeparatorField());
        add(new LabelField(s5));
        add(new SeparatorField());
        cfield2 = new ObjectChoiceField(s6,a,0);
        // menu 3
        store = PersistentStore.getPersistentObject (0x94d16aab37867123L);
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

        // menu 4

        final ButtonField mychoose2 = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        LabelField cfield3 = new LabelField(s7,LabelField.FIELD_VCENTER);
        add(cfield3);

        store = PersistentStore.getPersistentObject (0xbbf87d7fc545afe9L);
        synchronized (store) {
            if (store.getContents() == null) {
                 mychoose2.setLabel("None");
            }
            else {
                  globel.theMusic = (String) store.getContents();
                  mychoose2.setLabel(globel.theMusic.substring(globel.theMusic.lastIndexOf('/')+1));
            }
        }

        mychoose2.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
     //           FileSelectorPopupScreen fps = new FileSelectorPopupScreen("store/home/user/ringtones/",ext2);
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext2);
                fps.pickFile();
                globel.theMusic = fps.getFile();
                if(globel.theMusic == null)
                    mychoose2.setLabel("None");
                else
                    mychoose2.setLabel(globel.theMusic.substring(globel.theMusic.lastIndexOf('/')+1));
            }
        });
        add(mychoose2);
        add(new LabelField(s8));
        myclock = new DateField(s9,Long.MIN_VALUE,DateField.TIME);
        myclock.setEditable(true);
        store = PersistentStore.getPersistentObject (0x9baf7e102a05663fL);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                myclock = new DateField(s9,fromclock,DateField.TIME);
            }
        }
        myclock2 = new DateField(s10,Long.MIN_VALUE,DateField.TIME);
        myclock2.setEditable(true);
        store = PersistentStore.getPersistentObject (0xa991639c1eea79aeL);
        synchronized (store) {
            if (store.getContents() != null) {
                long toclock = Long.parseLong(store.getContents().toString());
                myclock2 = new DateField(s10,toclock,DateField.TIME);
            }
        }
        add(myclock);
        add(myclock2);
    }
    private MenuItem viewItem = new MenuItem(s11, 100, 10) {
            public void run() {
            save();
            close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
    public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x42f7439cfea21673L);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x94d16aab37867123L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x75a21d7e57dbebbL);
        store.setContents(globel.theFile);
        store.commit();
        store = PersistentStore.getPersistentObject (0xbbf87d7fc545afe9L);
        store.setContents(globel.theMusic);
        store.commit();
        store = PersistentStore.getPersistentObject (0x9baf7e102a05663fL);
        store.setContents(String.valueOf(myclock.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xa991639c1eea79aeL);
        store.setContents(String.valueOf(myclock2.getDate()));
        store.commit();
    }
}

