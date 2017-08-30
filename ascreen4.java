/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;


import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen4 extends MainScreen {
    ObjectChoiceField cfield,cfield2;
    DateField fromt,tot,fromr,tor;
    String s1 = "Wireless Extra";
    String s2 = "Wi-Fi Schedule :";
    String s3 = " Wi-Fi Auto:";
    String s4 = " Turn On At:";
    String s5 = " Turn Off At:";
    String s6 = "Radio Schedule :";
    String s7 = " Radio Auto:";
    String s8 = "Save";

    public ascreen4() {
         super();
         //#ifdef SC
//#       s1 = "无线增强";
//#       s2 = "Wi-Fi 计划管理 :";
//#       s3 = " Wi-Fi自动:";
//#       s4 = " 打开:";
//#       s5 = " 关闭:";
//#       s6 = "信号 计划管理 :";
//#       s7 = " 信号自动:";
//#       s8 = "保存";
          //#endif
        PersistentObject store;
        String a[]={"Disable","Enable"};
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        add(new LabelField(s2));
        add(new SeparatorField());
        cfield = new ObjectChoiceField(s3,a,0);
        fromt = new DateField(s4,Long.MIN_VALUE,DateField.TIME);
        tot = new DateField(s5,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0x8d115948c9c2c87aL);
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

        store = PersistentStore.getPersistentObject (0x608a39b43884080eL);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                fromt = new DateField(s4,fromclock,DateField.TIME);
            }
        }
        add(fromt);
        store = PersistentStore.getPersistentObject (0xf1d06a4809a33cdcL);
        synchronized (store) {
            if (store.getContents() != null) {
                long toclock = Long.parseLong(store.getContents().toString());
                tot = new DateField(s5,toclock,DateField.TIME);
            }
        }
        add(tot);
        add(new SeparatorField());
        add(new LabelField(s6));
        add(new SeparatorField());
        cfield2 = new ObjectChoiceField(s7,a,0);
        fromr = new DateField(s4,Long.MIN_VALUE,DateField.TIME);
        tor = new DateField(s5,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0xe62b62a5dd6bbea6L);
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

        store = PersistentStore.getPersistentObject (0xfdb812bfc68f72e3L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                fromr = new DateField(s4,fromclock,DateField.TIME);
            }
        }
        add(fromr);
        store = PersistentStore.getPersistentObject (0xed24038a3dfee82bL);
        synchronized (store) {
            if (store.getContents() != null) {
                long toclock = Long.parseLong(store.getContents().toString());
                tor = new DateField(s5,toclock,DateField.TIME);
            }
        }
        add(tor);
    }
         private MenuItem viewItem = new MenuItem(s8, 100, 10) {
            public void run() {
            ascreen4.this.save();
            ascreen4.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x8d115948c9c2c87aL);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x608a39b43884080eL);
        store.setContents(String.valueOf(fromt.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xf1d06a4809a33cdcL);
        store.setContents(String.valueOf(tot.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xe62b62a5dd6bbea6L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xfdb812bfc68f72e3L);
        store.setContents(String.valueOf(fromr.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xed24038a3dfee82bL);
        store.setContents(String.valueOf(tor.getDate()));
        store.commit();
     }
}
