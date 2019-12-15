package ImageManagementTool;

import javafx.scene.control.Label;
import org.im4java.core.Info;
import org.im4java.core.InfoException;

public class RotateButton implements ButtonClick {
    @Override
    public void click(String savedPath, Label label) throws InfoException {
        //Get the image format using im4java
        Info bwImageInfo = new Info(savedPath, true);
        label.setText("  " + bwImageInfo.getImageFormat());
    }
}
