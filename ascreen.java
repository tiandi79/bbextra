/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.GaugeField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen extends MainScreen {
    ObjectChoiceField cfield,cfield2,cfield3,cfield4,cfield5,cfield6,cfield8;
    GaugeField alerttime;
    EditField ipfield;
    String ext2[]={"mp3","midi","acc","wma"};
    ButtonField mychoose2 = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
    String s1 = "Vibrate With Ring Tone:";
    String s2 = "BackLight Off When Calling:";
    String s3 = "Vibrate After:";
    String s4 = "  Call Connected When Outgoing:";
    String s5 = "  Disconnected By Opposite:";
    String s6 = "  Missed Call Every 5 Mins:";
    String s7 = "     Play Sound:              ";
    String s8 = "Alert When Connected After:";
    String s9 = "  Seconds:";
    String s10 = "  Vibrate:";
    String s11 = "Add Contact Notify :";
    String s12 = "  After Disconnect:";
    String s13 = "IP Call :";
    String s14 = "  IP Number :";
    String s15 = "Save";
    String s16 = "Call extra";

    public ascreen (){
        super();
//#ifdef SC
//#      s1 = "响铃同时震动:";
//#      s2 = "通话时自动关闭背光灯:";
//#      s3 = "在以下情况之后震动:";
//#      s4 = "  主叫时接通:";
//#      s5 = "  对方挂断:";
//#      s6 = "  未接电话每5分钟提醒:";
//#      s7 = "    提示音:";
//#      s8 = "通话时间通知:";
//#      s9 = "  秒:";
//#      s10 = "  震动:";
//#      s11 = "添加联系人提示 :";
//#      s12 = "  通话挂断后:";
//#      s13 = "IP拨号 :";
//#      s14 = "  IP号码:";
//#      s15 = "保存";
//#      s16 = "通话增强";
          //#endif
        LabelField title = new LabelField(s16,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        String a[]={"Disable","Enable"};
        String b[]={"Disable","Short","Middle","Long"};
        PersistentObject store;
        cfield6 = new ObjectChoiceField(s1,a,0);
        store = PersistentStore.getPersistentObject (0x7f948c198f1bfafaL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield6.setSelectedIndex(store.getContents());
            }
        }
        if(DeviceInfo.getSoftwareVersion().startsWith("5")){
            store.setContents(cfield6.getChoice(0));
            store.commit();
            cfield6.setEditable(false);
        }
        add(cfield6);
        add(new SeparatorField());
        cfield8 = new ObjectChoiceField(s2,a,0);
        store = PersistentStore.getPersistentObject (0x520e17018653f10eL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield8.getChoice(cfield8.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield8.setSelectedIndex(store.getContents());
            }
        }
        add(cfield8);
        add(new SeparatorField());
        add(new LabelField(s3));
        add(new SeparatorField());
        // menu 1.

        cfield = new ObjectChoiceField(s4,b,0);
        add(cfield);

        store = PersistentStore.getPersistentObject (0x76e829a94adadcc6L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield.setSelectedIndex(store.getContents());
            }
        }
        //menu 2
        cfield2 = new ObjectChoiceField(s5,b,0);
        add(cfield2);
        store = PersistentStore.getPersistentObject (0x4f8080c03ba1cc10L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield2.setSelectedIndex(store.getContents());
            }
        }
        cfield5 = new ObjectChoiceField(s6,a,0);
        add(cfield5);
        store = PersistentStore.getPersistentObject (0xd13f2a862fcbd1c7L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield5.getChoice(cfield5.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield5.setSelectedIndex(store.getContents());
            }
        }
        //1.05.001 play music for missed call function
        LabelField cfield7 = new LabelField(s7,LabelField.FIELD_VCENTER);
        add(cfield7);
        store = PersistentStore.getPersistentObject (0xf61384d91cd700acL);
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



        add(new SeparatorField());
        add(new LabelField(s8));
        add(new SeparatorField());
        alerttime = new GaugeField(s9,0,60,50,GaugeField.FOCUSABLE|GaugeField.EDITABLE);
        add(alerttime);
        store = PersistentStore.getPersistentObject (0x9e5719378f00c08L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(String.valueOf(alerttime.getValue()));
                 store.commit();
            }
            else {
                alerttime.setValue(Integer.parseInt(store.getContents().toString()));
            }
        }
        cfield4 = new ObjectChoiceField(s10,b,0);
        add(cfield4);
        store = PersistentStore.getPersistentObject(0x348d7d96ab789c93L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield4.getChoice(2));
                 store.commit();
            }
            else {
                cfield4.setSelectedIndex(store.getContents());
            }
        }
        add(new SeparatorField());
        add(new LabelField(s11));
        add(new SeparatorField());
        //menu 3
        cfield3 = new ObjectChoiceField(s12,a,0);
        add(cfield3);
        store = PersistentStore.getPersistentObject(0x2d8da4ace5b17e2fL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield3.setSelectedIndex(store.getContents());
            }
        }
        add(new SeparatorField());
        add(new LabelField(s13));
        add(new SeparatorField());
        ipfield = new EditField(s14,"",10,EditField.FILTER_NUMERIC | EditField.FOCUSABLE);
        store = PersistentStore.getPersistentObject(0x2e7547ff0ac3340aL);
        synchronized (store) {
            if (store.getContents() != null) {
                ipfield.setText(store.getContents().toString());
            }
        }
        add(ipfield);
    }
     private MenuItem viewItem = new MenuItem(s15, 100, 10) {
            public void run() {
            ascreen.this.save();
            ascreen.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
    public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x76e829a94adadcc6L);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x4f8080c03ba1cc10L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x2d8da4ace5b17e2fL);
        store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x9e5719378f00c08L);
        store.setContents(String.valueOf(alerttime.getValue()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x348d7d96ab789c93L);
        store.setContents(cfield4.getChoice(cfield4.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x2e7547ff0ac3340aL);
        store.setContents(ipfield.getText());
        store.commit();
        store = PersistentStore.getPersistentObject (0xd13f2a862fcbd1c7L);
        store.setContents(cfield5.getChoice(cfield5.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x7f948c198f1bfafaL);
        store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x520e17018653f10eL);
        store.setContents(cfield8.getChoice(cfield8.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xf61384d91cd700acL);
        store.setContents(globel.theMusic);
        store.commit();
    }
}
