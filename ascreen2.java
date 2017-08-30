/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen2 extends MainScreen {
    ObjectChoiceField cfield,cfield2,cfield3,cfield6;
    BasicEditField cfield4,cfield5,cfield7,cfield8,cfield9,cfield10,cfield11,cfield12;
    String s1 = "Led Extra";
    String s2 = "  Blinking On:";
    String s3 = "  Blinking Off:";
    String s4 = "Blinking Period:";
    String s5 = "Change LED Color When :";
    String s6 = "  Missed Call:";
    String s7 = "  New Incoming SMS :";
    String s8 = "  New Incoming Email :";
    String s9 = "Disco Led When Imcoming Call:";
    String s10 = "Special Person:";
    String s11 = "  From:";
    String s12 = "  Led: 0x00";
    String s13 = "Save";
    String s14 = "Customize Led Color Error!";

    public ascreen2 (){
        super();
        //#ifdef SC
//#       s1 = "彩灯增强";
//#       s2 = "  灯亮时长:";
//#       s3 = "  灯灭时长:";
//#       s4 = "闪烁周期:";
//#       s5 = "当以下情况，改变LED颜色:";
//#       s6 = "  未接来电:";
//#       s7 = "  短信到达:";
//#       s8 = "  邮件到达:";
//#       s9 = "来电时炫彩化:";
//#       s10 = "特殊人群:";
//#       s11 = "  来自:";
//#       s12 = "  Led配色: 0x00";
//#       s13 = "保存";
//#       s14 = "自定义配色错误!";
          //#endif
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        PersistentObject store;
        cfield4 = new BasicEditField(s2,"10",2,BasicEditField.FILTER_NUMERIC);
        store = PersistentStore.getPersistentObject (0x7433d0f77a5cf730L);
        synchronized (store) {
            if (store.getContents() != null)
                 cfield4.setText((String) store.getContents());
            else {
                store.setContents(cfield4.getText());
                store.commit();
            }
        }
        cfield5 = new BasicEditField(s3,"30",2,BasicEditField.FILTER_NUMERIC);
        store = PersistentStore.getPersistentObject (0xc5da3270842802c1L);
        synchronized (store) {
            if (store.getContents() != null)
                 cfield5.setText((String) store.getContents());
            else {
                store.setContents(cfield5.getText());
                store.commit();
            }
        }

        add(new LabelField(s4));
        add(new SeparatorField());
        add(cfield4);
        add(cfield5);
        add(new SeparatorField());
        add(new LabelField(s5));
        add(new SeparatorField());
        // menu 1.
        String a[]={"Default","Orange","Yellow","Green","Cyan","Blue","Purple","White"};
        String b[]={"Disable","Enable"};
        cfield = new ObjectChoiceField(s6,a,0);
        add(cfield);

        store = PersistentStore.getPersistentObject (0x1c8c5fd3c1daa52aL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield.setSelectedIndex(store.getContents());
            }
        }
        // menu 2.
        cfield2 = new ObjectChoiceField(s7,a,0);
        add(cfield2);
        store = PersistentStore.getPersistentObject (0xff823c5ac48a9154L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield2.setSelectedIndex(store.getContents());
            }
        }
                // menu 3.
        cfield3 = new ObjectChoiceField(s8,a,0);
        add(cfield3);
        store = PersistentStore.getPersistentObject (0x8a839593ae4ec8ecL);
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
        cfield6 = new ObjectChoiceField(s9,b,0);
        add(cfield6);
        store = PersistentStore.getPersistentObject (0xd1513f6e5fb17e24L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield6.setSelectedIndex(store.getContents());
            }
        }
        add(new SeparatorField());
        add(new LabelField(s10));
        cfield7 = new BasicEditField(s11,"",30,BasicEditField.FILTER_DEFAULT);
        add(cfield7);
        store = PersistentStore.getPersistentObject (0x98b2e4273ded5d69L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield7.getText());
                 store.commit();
            }
            else {
                cfield7.setText((String) store.getContents());
            }
        }
        cfield8 = new BasicEditField(s12,"FFFFFF",6,BasicEditField.FILTER_DEFAULT);
        add(cfield8);
        store = PersistentStore.getPersistentObject (0x7b866b9679171a30L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield8.getText());
                 store.commit();
            }
            else {
                cfield8.setText((String) store.getContents());
            }
        }
        cfield9 = new BasicEditField(s11,"",30,BasicEditField.FILTER_DEFAULT);
        add(cfield9);
        store = PersistentStore.getPersistentObject (0x3de87c7fdc41155L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield9.getText());
                 store.commit();
            }
            else {
                cfield9.setText((String) store.getContents());
            }
        }
        cfield10 = new BasicEditField(s12,"FFFFFF",6,BasicEditField.FILTER_DEFAULT);
        add(cfield10);
        store = PersistentStore.getPersistentObject (0xd6aa329edde0ac8aL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield10.getText());
                 store.commit();
            }
            else {
                cfield10.setText((String) store.getContents());
            }
        }
        cfield11 = new BasicEditField(s11,"",30,BasicEditField.FILTER_DEFAULT);
        add(cfield11);
        store = PersistentStore.getPersistentObject (0x6f7258f00ca7b656L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield11.getText());
                 store.commit();
            }
            else {
                cfield11.setText((String) store.getContents());
            }
        }
        cfield12 = new BasicEditField(s12,"FFFFFF",6,BasicEditField.FILTER_DEFAULT);
        add(cfield12);
        store = PersistentStore.getPersistentObject (0xcd12018b3dcee492L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield12.getText());
                 store.commit();
            }
            else {
                cfield12.setText((String) store.getContents());
            }
        }

    }

    private MenuItem viewItem = new MenuItem(s13, 100, 10) {
            public void run() {
            ascreen2.this.save();
            ascreen2.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
    public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x1c8c5fd3c1daa52aL);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xff823c5ac48a9154L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x8a839593ae4ec8ecL);
        store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x7433d0f77a5cf730L);
        store.setContents(cfield4.getText());
        store.commit();
        store = PersistentStore.getPersistentObject (0xc5da3270842802c1L);
        store.setContents(cfield5.getText());
        store.commit();
        store = PersistentStore.getPersistentObject (0xd1513f6e5fb17e24L);
        store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x98b2e4273ded5d69L);
        store.setContents(cfield7.getText());
        store.commit();
        store = PersistentStore.getPersistentObject (0x7b866b9679171a30L);
        store.setContents(cfield8.getText());
        store.commit();
        if(!checknum(cfield8)){
            Dialog.alert(s14);
            return;
        }
        store = PersistentStore.getPersistentObject (0x3de87c7fdc41155L);
        store.setContents(cfield9.getText());
        store.commit();
        store = PersistentStore.getPersistentObject (0xd6aa329edde0ac8aL);
        store.setContents(cfield10.getText());
        store.commit();
        if(!checknum(cfield10)){
            Dialog.alert(s14);
            return;
        }
        store = PersistentStore.getPersistentObject (0x6f7258f00ca7b656L);
        store.setContents(cfield11.getText());
        store.commit();
        store = PersistentStore.getPersistentObject (0xcd12018b3dcee492L);
        store.setContents(cfield12.getText());
        store.commit();
        if(!checknum(cfield12)){
            Dialog.alert(s14);
            return;
        }

        ////////////////////////////
        //       led blink        //
        ////////////////////////////
        globel.blinkon = Integer.parseInt(cfield4.getText());
        if(globel.blinkon ==  0)   globel.blinkon = 10;
        globel.blinkoff = Integer.parseInt(cfield5.getText());
        if(globel.blinkoff ==  0)   globel.blinkoff = 30;
    }

    private boolean checknum(BasicEditField cfield) {
        cfield.getText();
        String a = cfield.getText();
        try{
            int result=Integer.parseInt(a, 16);
        }catch(Exception ex){
           return false;
        }
       return true;
    }
}
