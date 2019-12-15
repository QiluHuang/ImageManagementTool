package ImageManagementTool;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.im4java.core.InfoException;

public interface ButtonClick {
    void click(String savedPath, Label label) throws InfoException;
}
