import javax.swing.*;
import java.io.*;

public class FileThread extends Thread {

    private final String fileName;
    private final String successMessage;
    private final Object obj;
    private final JFrame frame;
    private final JDialog dialog;
    private final boolean manager;
    public Store store;

    public FileThread(String fileName, String successMessage, Object obj, JFrame frame, boolean manager, Store store) {
        this.fileName = fileName;
        this.successMessage = successMessage;
        this.obj = obj;
        this.frame = frame;
        this.dialog = new JDialog(frame, "Saving to file"); // create a dialog with the frame as the owner
        this.manager = manager;
        this.store = store;
    }

    @Override
    public void run() {
        // this method runs in a separate thread
        try {
            Store.WriteToFile(fileName, obj);
            // use SwingUtilities.invokeLater to show the message dialog on the EDT
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(dialog, successMessage); // show the message dialog with the dialog as the parent
                    dialog.dispose(); // dispose the dialog
                    frame.dispose(); // dispose the frame
                    if (manager)
                        Main.ManagerGUI(store);
                    else
                        Main.CustomerGUI(store);
                }
            });
        } catch (Exception e) {
            // use SwingUtilities.invokeLater to show the error message on the EDT
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(dialog, e.getMessage()); // show the error message with the dialog as the parent
                    dialog.dispose();
                    frame.dispose();
                }
            });
        }
    }
}
