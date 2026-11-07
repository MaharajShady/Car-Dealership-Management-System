
package carframeapp;

import javax.swing.SwingUtilities;
import za.ac.tut.CarFrame;

public class CarFrameApp
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            CarFrame carFrame = new CarFrame();
        });
    }
}