package ImageManagementTool;

import javafx.scene.image.Image;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import java.io.*;


public class ImageTools {

    public ImageTools imageTools;

    public ImageTools() {
        imageTools = new ImageTools();
    }


    /**
     * Black and And White Effect
     * @param srcPath
     * @param newPath
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    public static void blackAndWhiteImage(String srcPath, String newPath) throws InterruptedException, IOException, IM4JavaException {
        // create command
        ConvertCmd cmd = new ConvertCmd();
        // create the operation, add images and operators/options
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        // black and white effect
        op.monochrome();
        op.addImage(newPath);
        // execute the operation
        cmd.run(op);
    }

    /**
     * Add color
     * @param srcPath
     * @param newPath
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    public static void thresholdImage(String srcPath, String newPath) throws InterruptedException, IOException, IM4JavaException {
        // create command
        ConvertCmd cmd = new ConvertCmd();
        // create the operation, add images and operators/options
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        // threshold effect
        //op.threshold(90);
        op.colors(5);
        op.addImage(newPath);
        // execute the operation
        cmd.run(op);
    }

    /**
     * Rotate the Image
     * @param srcPath
     * @param newPath
     * @param height
     * @param width
     * @throws Exception
     */
    public static void rotateImage(String srcPath, String newPath, int height, int width) throws Exception{
        // create command
        ConvertCmd cmd = new ConvertCmd();
        // create the operation, add images and operators/options
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        op.rotate(90.0);
        op.addImage(newPath);
        // execute the operation
        cmd.run(op);
    }

    /**
     * Resize the Image
     *
     * @param width   the width after resizing
     * @param height  the height after resizing
     * @param srcPath the original file path
     * @param newPath the file path for saving the image
     */
    public static void shearImage(String srcPath, String newPath, int height, int width) throws Exception{
        // create command
        ConvertCmd cmd = new ConvertCmd();
        // create the operation, add images and operators/options
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        op.resize(height, width);
        op.addImage(newPath);
        // execute the operation
        cmd.run(op);
    }



    /**
     * Add Watermark
     *
     */
    public static void addImgText(String srcPath, String newPath) throws Exception {
        // create an image
        ConvertCmd cmd = new ConvertCmd();
        // create the operation, add images and operators/options
        IMOperation operation = new IMOperation();

        // get the image
        operation.addImage(srcPath);
        // add text on the image
        operation.pointsize(50); //size of text
        operation.gravity("southeast");
        operation.font("Calibri"); // font
        operation.fill("#FE0101"); //font color
        operation.draw("text 80,10 'We love Java!'");
        operation.draw("text 80,60 'We love Java!'");
        operation.draw("text 80,110 'We love Java!'");
        operation.draw("text 80,160 'We love Java!'");
        operation.draw("text 80,210 'We love Java!'");
        operation.draw("text 80,260 'We love Java!'");
        // save the image
        operation.addImage(newPath);
        //execute the operation
        cmd.run(operation);
    }

    /**
     * Convert the format of the Image
     *
     * @param formatName the format the user want to convert
     * @param srcPath the original file path
     * @param newPath the file path for saving the image
     */
    public static void convertFormat(String formatName, String srcPath, String newPath) throws InterruptedException, IOException, IM4JavaException {
        boolean ifConvert = false;
        //ProcessStarter.setGlobalSearchPath("/Users/lunahuang/Documents/Java/ImageMagick-7.0.9");
        //changedImage = SwingFXUtils.fromFXImage(imageView.getImage(),null);
        // create an image
        ConvertCmd cmd = new ConvertCmd();
        // create the operation, add images and operators/options
        IMOperation operation = new IMOperation();
        // get the image
        operation.addImage(srcPath);
        //operation.addImage(String.valueOf(selectedFilePath));
        operation.format(formatName);
        // save the image
        operation.addImage(newPath);
        //execute the operation
        cmd.run(operation);
    }

    /**
     * help user to download the image to the choosen flolder
     * @param src
     * @param target
     */
    public static void copyFile(String src, String target) {
        File srcFile = new File(src);
        File targetFile = new File(target);
        try {
            InputStream in = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
