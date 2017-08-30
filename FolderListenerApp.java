/**
 * FolderListenerApp.java
 *
 */

package test;

import net.rim.blackberry.api.mail.event.*;
import net.rim.device.api.system.*;
import net.rim.blackberry.api.mail.*;

public final class FolderListenerApp implements FolderListener {

  private BBextra folderListenerGUI = null;
  //Long value: com.samples.folderListener
  public static final long RTSID_MY_APP = 0xf2fd3a241d1368b3L;

  public FolderListenerApp()
  {
    try
    {
      //Get the store from the default instance.
      Store store = Session.getDefaultInstance().getStore();
      //Add the folder listener to the store.
      store.addFolderListener(this);
      //System.out.println("****BBextra Log****:set store ok");
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }

  //Returns an instance of the running FolderListenerApp.
  public static FolderListenerApp waitForSingleton()
  {
    //Ensure this is a singleton instance.

    //Open the RuntimeStore.
    RuntimeStore store = RuntimeStore.getRuntimeStore();
    //Obtain the reference of FolderListenerApp.
    Object obj = store.get(RTSID_MY_APP);

    //If obj is null, there is no current reference
    //to FolderListenerApp. Start a new instance
    // of FolderListenerApp if one is not running.
    if (obj == null)
    {
      //Store a reference to this instance in the RuntimeStore.
      store.put(RTSID_MY_APP, new FolderListenerApp());

      return (FolderListenerApp)store.get(RTSID_MY_APP);
    } else
    {
      return (FolderListenerApp)obj;
    }
  }

  //Called when a new message is created.
  public void messagesAdded(FolderEvent e)
  {
    //Get the message that fired the folder event.
    final Message orginalMessage = e.getMessage();
    //Get the folder the message is in.
    Folder messageFolder = orginalMessage.getFolder();

    //Is the new message in the Inbox?
    if (messageFolder.getType() == Folder.INBOX)
    {

      //Check if the FolderListenerGUI is running.
      if (folderListenerGUI != null)
      {

        //Grab the lock for the running FolderListenerGUI.
        folderListenerGUI.invokeLater(new Runnable()
        {
          public void run()
          {
            //Call the NewMailArrived method to update the
            //screen with a notification.
            //System.out.println("****BBextra Log****:detect new email coming");
            BBextra.NewMailArrived(orginalMessage);
          }
        });
      }
    }
  }

  //Passes in reference to the FolderListenerGUI that is running.
  public void setFolderListenerGUI(BBextra folderGUI)
  {
    folderListenerGUI = folderGUI;
  }

  //Set the reference to the FolderListenerGUI to null
  //(when it is shut down).
  public void unsetFolderListenerGUI()
  {
    folderListenerGUI = null;
  }

  //Not used in this sample.
  public void messagesRemoved(FolderEvent e)
  {
  }
}