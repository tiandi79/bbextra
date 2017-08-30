/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.MainScreen;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen5 extends MainScreen {
    ObjectChoiceField cfield,cfield2,cfield3,cfield4,cfield5,cfield6,cfield7,cfield8,cfield9,cfield10,cfield11,cfield12;
    DateField time1,time2,time3;
    CheckboxField mon,tus,wed,thu,fri,sat,sun,mon2,tus2,wed2,thu2,fri2,sat2,sun2,mon3,tus3,wed3,thu3,fri3,sat3,sun3;
    String ext[]={"mp3","mid","acc","wma"};
    private String lockmusic = null;
    private String hourmusic = null;
    String s1 = "Clock Extra";
    String s2 = "Lock Schedule :";
    String s3 = " Auto Lock By Clock Updated:";
    String s4 = " Lock After Minutes:";
    String s5 = "Exception List";
    String s6 = " Not Lock When USB Connected:";
    String s7 = " Only When Home Screen:";
    String s8 = " Play Sound When Auto Lock:";
    String s9 = "   Play Sound:    ";
    String s10 = "Hourly Alarm :";
    String s11 = "Clock Alarm :";
    String s12 = " Always On Top:";
    String s13 = " Snooze:";
    String s14 = " Disco LED When Alarm:";
    String s15 = " Baby Mode:";
    String s16 = " Clock Alarm One:";
    String s17 = "   Alarm Time:";
    String s18 = "   Alarm Day:             ";
    String s19 = "Mon";
    String s20 = "Tus";
    String s21 = "Wed";
    String s22 = "Thu";
    String s23 = "Fri";
    String s24 = "Sat";
    String s25 = "Sun";
    String s26 = " Clock Alarm Two:";
    String s27 = " Clock Alarm Three:";
    String s28 = "Save";

        public ascreen5(){
        super();
        //#ifdef SC
//#       s1 = "时钟增强";
//#       s2 = "锁屏管理:";
//#       s3 = " 当时钟变化时自动锁屏:";
//#       s4 = " 在。。。分之后锁定:";
//#       s5 = "前台例外程序";
//#       s6 = " USB接入时禁止锁屏:";
//#       s7 = " 只在主页状态时锁屏:";
//#       s8 = " 锁屏时同时播放音乐:";
//#       s9 = "   提示音:    ";
//#       s10 = "整点报时 :";
//#       s11 = "闹铃功能 :";
//#       s12 = " 总在最前显示:";
//#       s13 = " 贪睡时间:";
//#       s14 = " 闹铃时炫彩化:";
//#       s15 = " 婴儿模式:";
//#       s16 = " 闹铃一:";
//#       s17 = "   闹铃时间:";
//#       s18 = "   有效星期:             ";
//#       s19 = "一";
//#       s20 = "二";
//#       s21 = "三";
//#       s22 = "四";
//#       s23 = "五";
//#       s24 = "六";
//#       s25 = "天";
//#       s26 = " 闹铃二:";
//#       s27 = " 闹铃三:";
//#       s28 = "保存";
          //#endif
        globel.theMusic="None";
        globel.theMusic2="None";
        globel.theMusic3="None";
        PersistentObject store;
        String a[]={"Disable","Enable"};
        String b[]={"1","2","3","4","5"};
        String c[]={"0","1","2","5","10","15"};
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        add(new LabelField(s2));
        add(new SeparatorField());
        cfield = new ObjectChoiceField(s3,a,0);
        store = PersistentStore.getPersistentObject (0xa7cb7eb2e499c592L);
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
        cfield2 = new ObjectChoiceField(s4,b,0);
        store = PersistentStore.getPersistentObject (0xf79c2d6bd19e97d5L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield2.setSelectedIndex(store.getContents());
            }
        }
        ButtonField exception = new ButtonField(s5,ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        exception.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
               UiApplication.getUiApplication().pushScreen(new listscreen());
        }
        });
        add(exception);
        add(cfield2);
        cfield12 = new ObjectChoiceField(s6,a,0);
        store = PersistentStore.getPersistentObject (0x743427ce25a6b74cL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield12.getChoice(cfield12.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield12.setSelectedIndex(store.getContents());
            }
        }
        add(cfield12);
        cfield9 = new ObjectChoiceField(s7,a,0);
        store = PersistentStore.getPersistentObject (0xc9217e122938cfaeL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield9.getChoice(cfield9.getSelectedIndex()));
                 store.commit();
            }
            else if (store.getContents().toString().equalsIgnoreCase("Enable")){
                exception.setEditable(false);
                cfield9.setSelectedIndex(store.getContents());
            }
            else {
                exception.setEditable(true);
                cfield9.setSelectedIndex(store.getContents());
            }
        }
        add(cfield9);

        cfield6 = new ObjectChoiceField(s8,a,0);
        store = PersistentStore.getPersistentObject (0xf4b763d73d7464f6L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield6.setSelectedIndex(store.getContents());
            }
        }
        add(cfield6);
        LabelField lfieldlock = new LabelField(s9);
        final ButtonField mychooselock = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        store = PersistentStore.getPersistentObject (0xe63a9bdc93bfe01aL);
        synchronized (store) {
            if (store.getContents() == null) {
                 mychooselock.setLabel("None");
            }
            else {
                  lockmusic = (String) store.getContents();
                  mychooselock.setLabel(lockmusic.substring(lockmusic.lastIndexOf('/')+1));
            }
        }
        add(lfieldlock);
        mychooselock.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
     //           FileSelectorPopupScreen fps = new FileSelectorPopupScreen("store/home/user/ringtones/",ext2);
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext);
                fps.pickFile();
                lockmusic = fps.getFile();
                if(lockmusic == null)
                    mychooselock.setLabel("None");
                else
                    mychooselock.setLabel(lockmusic.substring(lockmusic.lastIndexOf('/')+1));
            }
        });
        add(mychooselock);

        ///////////////////////////////
        //         Hourly Alarm      //
        ///////////////////////////////
        add(new SeparatorField());
        add(new LabelField(s10));
        final ButtonField hourbutton = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        store = PersistentStore.getPersistentObject (0x7897a365635c5a6aL);
        synchronized (store) {
            if (store.getContents() == null) {
                 hourbutton.setLabel("None");
            }
            else {
                  hourmusic = (String) store.getContents();
                  hourbutton.setLabel(hourmusic.substring(hourmusic.lastIndexOf('/')+1));
            }
        }
                hourbutton.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
     //           FileSelectorPopupScreen fps = new FileSelectorPopupScreen("store/home/user/ringtones/",ext2);
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext);
                fps.pickFile();
                hourmusic = fps.getFile();
                if(hourmusic == null)
                    hourbutton.setLabel("None");
                else
                    hourbutton.setLabel(hourmusic.substring(hourmusic.lastIndexOf('/')+1));
            }
        });
        add(hourbutton);
        ////////////////////////////////
        //  time alarm 1             //
        ///////////////////////////////
        add(new SeparatorField());
        add(new LabelField(s11));
        cfield7 = new ObjectChoiceField(s12,a,0);
        store = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
//        store.setContents(cfield7.getChoice(1));
//        store.commit();
//        cfield7.setSelectedIndex(store.getContents());
//        cfield7.setEditable(false);
//    for BBextra SC version
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield7.getChoice(cfield7.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield7.setSelectedIndex(store.getContents());
            }
        }
        add(cfield7);
        cfield8 = new ObjectChoiceField(s13,c,0);
        store = PersistentStore.getPersistentObject (0xb7bf8946dda72749L);
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
        cfield10 = new ObjectChoiceField(s14,a,0);
        store = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield10.getChoice(cfield10.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield10.setSelectedIndex(store.getContents());
            }
        }
        add(cfield10);
        cfield11 = new ObjectChoiceField(s15,a,0);
        store = PersistentStore.getPersistentObject (0xbf39f850a23b9573L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield11.getChoice(cfield11.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield11.setSelectedIndex(store.getContents());
            }
        }
        add(cfield11);
        add(new SeparatorField());
        cfield3 = new ObjectChoiceField(s16,a,0);
        store = PersistentStore.getPersistentObject (0x7847782406658f55L);
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
        time1 = new DateField(s17,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0x20c5f6d1336be7b6L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time1.setDate(fromclock);
            }
        }
        add(time1);
        LabelField lfield = new LabelField(s9);
        final ButtonField mychoose = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        store = PersistentStore.getPersistentObject (0x5559ad9e5ba41f57L);
        synchronized (store) {
            if (store.getContents() == null) {
                 mychoose.setLabel("None");
            }
            else {
                  globel.theMusic = (String) store.getContents();
                  mychoose.setLabel(globel.theMusic.substring(globel.theMusic.lastIndexOf('/')+1));
            }
        }
        FlowFieldManager ffm = new FlowFieldManager();
        LabelField dayfield = new LabelField(s18);
        mon = new CheckboxField("Mon ",false);
        store = PersistentStore.getPersistentObject (0xa85576eaeaf1ef20L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 mon.setChecked(true);
            }
        }
        tus = new CheckboxField("Tus ",false);
        store = PersistentStore.getPersistentObject (0x8d49121cb7684695L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 tus.setChecked(true);
            }
        }
        wed = new CheckboxField("Wed ",false);
        store = PersistentStore.getPersistentObject (0xc39425bf786480dfL);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 wed.setChecked(true);
            }
        }
        thu = new CheckboxField("Thu ",false);
        store = PersistentStore.getPersistentObject (0x26dd5c21153ea8b7L);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 thu.setChecked(true);
            }
        }
        FlowFieldManager ffm2 = new FlowFieldManager(Manager.FIELD_HCENTER);
        fri = new CheckboxField("Fri ",false);
        store = PersistentStore.getPersistentObject (0xb4e00b28758a7525L);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 fri.setChecked(true);
            }
        }
        sat = new CheckboxField("Sat ",false);
        store = PersistentStore.getPersistentObject (0x882a5b70fe4f00L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 sat.setChecked(true);
            }
        }
        sun = new CheckboxField("Sun ",false);
        store = PersistentStore.getPersistentObject (0xd60bae6df03f5a34L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 sun.setChecked(true);
            }
        }
        ffm.add(dayfield);
        ffm.add(mon);
        ffm.add(tus);
        ffm2.add(wed);
        ffm2.add(thu);
        ffm2.add(fri);
        ffm2.add(sat);
        ffm2.add(sun);
        add(ffm);
        add(ffm2);
        add(lfield);
        mychoose.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
     //           FileSelectorPopupScreen fps = new FileSelectorPopupScreen("store/home/user/ringtones/",ext2);
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext);
                fps.pickFile();
                globel.theMusic = fps.getFile();
                if(globel.theMusic == null)
                    mychoose.setLabel("None");
                else
                    mychoose.setLabel(globel.theMusic.substring(globel.theMusic.lastIndexOf('/')+1));
            }
        });
        add(mychoose);
        ////////////////////////////////
        //  time alarm 2             //
        ///////////////////////////////
        add(new SeparatorField());
        cfield4 = new ObjectChoiceField(s26,a,0);
        store = PersistentStore.getPersistentObject (0x9e9a51a9c53906f7L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield4.getChoice(cfield4.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield4.setSelectedIndex(store.getContents());
            }
        }
        add(cfield4);
        time2 = new DateField(s17,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0x182b063c5c35aa1fL);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time2.setDate(fromclock);
            }
        }
        add(time2);
        LabelField lfield2 = new LabelField(s9);
        final ButtonField mychoose2 = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        store = PersistentStore.getPersistentObject (0x836c7b22b1180985L);
        synchronized (store) {
            if (store.getContents() == null) {
                 mychoose2.setLabel("None");
            }
            else {
                  globel.theMusic2 = (String) store.getContents();
                  mychoose2.setLabel(globel.theMusic2.substring(globel.theMusic2.lastIndexOf('/')+1));
            }
        }
        FlowFieldManager ffm3 = new FlowFieldManager();
        LabelField dayfield2 = new LabelField(s18);
        mon2 = new CheckboxField(s19,false);
        store = PersistentStore.getPersistentObject (0xa1c8b5fbbca891f5L);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 mon2.setChecked(true);
            }
        }
        tus2 = new CheckboxField(s20,false);
        store = PersistentStore.getPersistentObject (0x641488d388cfa1ecL);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 tus2.setChecked(true);
            }
        }
        wed2 = new CheckboxField(s21,false);
        store = PersistentStore.getPersistentObject (0xaec09de4d1ec3f4eL);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 wed2.setChecked(true);
            }
        }
        thu2 = new CheckboxField(s22,false);
        store = PersistentStore.getPersistentObject (0x23cc435034469c9fL);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 thu2.setChecked(true);
            }
        }
        FlowFieldManager ffm4 = new FlowFieldManager(Manager.FIELD_HCENTER);
        fri2 = new CheckboxField(s23,false);
        store = PersistentStore.getPersistentObject (0x2692d94e6114eac5L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 fri2.setChecked(true);
            }
        }
        sat2 = new CheckboxField(s24,false);
        store = PersistentStore.getPersistentObject (0xef2a91f63529defbL);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 sat2.setChecked(true);
            }
        }
        sun2 = new CheckboxField(s25,false);
        store = PersistentStore.getPersistentObject (0xefb2f40e7ca8157fL);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 sun2.setChecked(true);
            }
        }
        ffm3.add(dayfield2);
        ffm3.add(mon2);
        ffm3.add(tus2);
        ffm4.add(wed2);
        ffm4.add(thu2);
        ffm4.add(fri2);
        ffm4.add(sat2);
        ffm4.add(sun2);
        add(ffm3);
        add(ffm4);
        add(lfield2);
        mychoose2.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
     //           FileSelectorPopupScreen fps = new FileSelectorPopupScreen("store/home/user/ringtones/",ext2);
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext);
                fps.pickFile();
                globel.theMusic2 = fps.getFile();
                if(globel.theMusic2 == null)
                    mychoose2.setLabel("None");
                else
                    mychoose2.setLabel(globel.theMusic2.substring(globel.theMusic2.lastIndexOf('/')+1));
            }
        });
        add(mychoose2);
        ////////////////////////////////
        //  time alarm 3             //
        ///////////////////////////////
        add(new SeparatorField());
        cfield5 = new ObjectChoiceField(s27,a,0);
        store = PersistentStore.getPersistentObject (0x4e30030f1ea96be1L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield5.getChoice(cfield5.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield5.setSelectedIndex(store.getContents());
            }
        }
        add(cfield5);
        time3 = new DateField(s17,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0xff5d29eae5143093L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time3.setDate(fromclock);
            }
        }
        add(time3);
        LabelField lfield3 = new LabelField(s9);
        final ButtonField mychoose3 = new ButtonField("Browse",ButtonField.CONSUME_CLICK|ButtonField.FIELD_RIGHT );
        store = PersistentStore.getPersistentObject (0x6b9b78bc6bf657daL);
        synchronized (store) {
            if (store.getContents() == null) {
                 mychoose3.setLabel("None");
            }
            else {
                  globel.theMusic3 = (String) store.getContents();
                  mychoose3.setLabel(globel.theMusic3.substring(globel.theMusic3.lastIndexOf('/')+1));
            }
        }
        FlowFieldManager ffm5 = new FlowFieldManager();
        LabelField dayfield3 = new LabelField(s18);
        mon3 = new CheckboxField(s19,false);
        store = PersistentStore.getPersistentObject (0xce1472c295472e87L);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 mon3.setChecked(true);
            }
        }
        tus3 = new CheckboxField(s20,false);
        store = PersistentStore.getPersistentObject (0xe952454c385f2d60L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 tus3.setChecked(true);
            }
        }
        wed3 = new CheckboxField(s21,false);
        store = PersistentStore.getPersistentObject (0x73fe9c32146ab6e4L);
        synchronized (store) {
            if(store.getContents()!= null)
           if (store.getContents().toString().equalsIgnoreCase("true")) {
                 wed3.setChecked(true);
            }
        }
        thu3 = new CheckboxField(s22,false);
        store = PersistentStore.getPersistentObject (0xf281b213404da0daL);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 thu3.setChecked(true);
            }
        }
        FlowFieldManager ffm6 = new FlowFieldManager(Manager.FIELD_HCENTER);
        fri3 = new CheckboxField(s23,false);
        store = PersistentStore.getPersistentObject (0x8e8bd8b20e6da5eL);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 fri3.setChecked(true);
            }
        }
        sat3 = new CheckboxField(s24,false);
        store = PersistentStore.getPersistentObject (0x1c8f91eb76a13d0bL);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 sat3.setChecked(true);
            }
        }
        sun3 = new CheckboxField(s25,false);
        store = PersistentStore.getPersistentObject (0x4592bdf7f3feaaa0L);
        synchronized (store) {
            if(store.getContents()!= null)
            if (store.getContents().toString().equalsIgnoreCase("true")) {
                 sun3.setChecked(true);
            }
        }
        ffm5.add(dayfield3);
        ffm5.add(mon3);
        ffm5.add(tus3);
        ffm6.add(wed3);
        ffm6.add(thu3);
        ffm6.add(fri3);
        ffm6.add(sat3);
        ffm6.add(sun3);
        add(ffm5);
        add(ffm6);
        add(lfield3);
        mychoose3.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
     //           FileSelectorPopupScreen fps = new FileSelectorPopupScreen("store/home/user/ringtones/",ext2);
                FileSelectorPopupScreen fps = new FileSelectorPopupScreen(null,ext);
                fps.pickFile();
                globel.theMusic3 = fps.getFile();
                if(globel.theMusic3 == null)
                    mychoose3.setLabel("None");
                else
                    mychoose3.setLabel(globel.theMusic3.substring(globel.theMusic3.lastIndexOf('/')+1));
            }
        });
        add(mychoose3);
    }
        private MenuItem viewItem = new MenuItem(s28, 100, 10) {
            public void run() {
            ascreen5.this.save();
            ascreen5.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0xa7cb7eb2e499c592L);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xc9217e122938cfaeL);
        store.setContents(cfield9.getChoice(cfield9.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
        store.setContents(cfield10.getChoice(cfield10.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xbf39f850a23b9573L);
        store.setContents(cfield11.getChoice(cfield11.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x743427ce25a6b74cL);
        store.setContents(cfield12.getChoice(cfield12.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xf79c2d6bd19e97d5L);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x7847782406658f55L);
        store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x20c5f6d1336be7b6L);
        store.setContents(String.valueOf(time1.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xa85576eaeaf1ef20L);
        store.setContents(String.valueOf(mon.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x8d49121cb7684695L);
        store.setContents(String.valueOf(tus.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xc39425bf786480dfL);
        store.setContents(String.valueOf(wed.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x26dd5c21153ea8b7L);
        store.setContents(String.valueOf(thu.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xb4e00b28758a7525L);
        store.setContents(String.valueOf(fri.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x882a5b70fe4f00L);
        store.setContents(String.valueOf(sat.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xd60bae6df03f5a34L);
        store.setContents(String.valueOf(sun.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x9e9a51a9c53906f7L);
        store.setContents(cfield4.getChoice(cfield4.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x182b063c5c35aa1fL);
        store.setContents(String.valueOf(time2.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xa1c8b5fbbca891f5L);
        store.setContents(String.valueOf(mon2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x641488d388cfa1ecL);
        store.setContents(String.valueOf(tus2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xaec09de4d1ec3f4eL);
        store.setContents(String.valueOf(wed2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x23cc435034469c9fL);
        store.setContents(String.valueOf(thu2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x2692d94e6114eac5L);
        store.setContents(String.valueOf(fri2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xef2a91f63529defbL);
        store.setContents(String.valueOf(sat2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xefb2f40e7ca8157fL);
        store.setContents(String.valueOf(sun2.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x4e30030f1ea96be1L);
        store.setContents(cfield5.getChoice(cfield5.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xff5d29eae5143093L);
        store.setContents(String.valueOf(time3.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xce1472c295472e87L);
        store.setContents(String.valueOf(mon3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xe952454c385f2d60L);
        store.setContents(String.valueOf(tus3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x73fe9c32146ab6e4L);
        store.setContents(String.valueOf(wed3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xf281b213404da0daL);
        store.setContents(String.valueOf(thu3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x8e8bd8b20e6da5eL);
        store.setContents(String.valueOf(fri3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x1c8f91eb76a13d0bL);
        store.setContents(String.valueOf(sat3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x4592bdf7f3feaaa0L);
        store.setContents(String.valueOf(sun3.getChecked()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x5559ad9e5ba41f57L);
        store.setContents(globel.theMusic);
        store.commit();
        store = PersistentStore.getPersistentObject (0x836c7b22b1180985L);
        store.setContents(globel.theMusic2);
        store.commit();
        store = PersistentStore.getPersistentObject (0x6b9b78bc6bf657daL);
        store.setContents(globel.theMusic3);
        store.commit();
        store = PersistentStore.getPersistentObject (0xf4b763d73d7464f6L);
        store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xe63a9bdc93bfe01aL);
        store.setContents(lockmusic);
        store.commit();
        store = PersistentStore.getPersistentObject (0x7897a365635c5a6aL);
        store.setContents(hourmusic);
        store.commit();
        store = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
        store.setContents(cfield7.getChoice(cfield7.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xb7bf8946dda72749L);
        store.setContents(cfield8.getChoice(cfield8.getSelectedIndex()));
        store.commit();
    }
}
class listscreen extends MainScreen {
     EditField e1;
     int k;
     ButtonField b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;
    public listscreen()  {
        super();
        LabelField title = new LabelField("Exception List",LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        PersistentObject store;
        e1 = new EditField("","",1000,EditField.FIELD_LEFT);
        store = PersistentStore.getPersistentObject (0xb4d4b7c025832afeL);
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
        add(new SeparatorField());
        add(new LabelField("Now Running:"));
        ApplicationManager manager = ApplicationManager.getApplicationManager();
        ApplicationDescriptor[] appDes = manager.getVisibleApplications();
        k = appDes.length;
        if(k> 15) k = 15;
        for (int i=0;i<k;i++){
            final String tmp = appDes[i].getName();
            if (i == 0){
                b1 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b1);
                b1.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 1){
                b2 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b2);
                b2.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 2){
                b3 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b3);
                b3.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 3){
                b4 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b4);
                b4.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 4){
                b5 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b5);
                b5.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 5){
                b6 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b6);
                b6.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 6){
                b7 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b7);
                b7.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 7){
                b8 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b8);
                b8.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 8){
                b9 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b9);
                b9.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 9){
                b10 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b10);
                b10.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 10){
                b11 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b11);
                b11.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }else if (i == 11){
                b12 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b12);
                b12.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 12){
                b13 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b13);
                b13.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 13){
                b14 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b14);
                b14.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
            else if (i == 14){
                b15 = new ButtonField("Add "+tmp,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b15);
                b15.setChangeListener(new FieldChangeListener() {
        public void fieldChanged(Field field, int context) {
            e1.setText(e1.getText()+tmp+"\n");
                        }
        });
    }
    }
    }
    private MenuItem viewItem = new MenuItem("Save", 100, 10) {
            public void run() {
            listscreen.this.save();
            listscreen.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0xb4d4b7c025832afeL);
        store.setContents(e1.getText());
        store.commit();
}
}