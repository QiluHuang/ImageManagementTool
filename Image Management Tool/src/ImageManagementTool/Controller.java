package ImageManagementTool;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;


//import java.awt.Image;

//ImageMagick
import org.im4java.core.*;



import java.io.File;
import java.io.IOException;





public class Controller {

    @FXML
    private Button uploadButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Label heightLabel;
    @FXML
    private Label widthLabel;
    @FXML
    private Label filePathLabel;
    @FXML
    private Label formatLabel;


    private boolean oriImageStatus;


    private Pane imagePane;
    private BorderPane borderPane;

    private Image oriImage;
    String selectedFilePath; // without quotation marks
    String filePath;         // with quotation marks
    String chooseSavedFilePath;  // without quotation marks
    String chooseSavedFolderPath;
    String fileName;
    String selectedFileName;
    File selectedFile;
    File chooseSavedFolder;
    Info imageInfo;
    String savedImagePath;

    String savedJPGFilePath;
    String savedPNGFilePath;
    String savedBMPFilePath;
    String savedGIFFilePath;
    String savedRotatedFilePath;
    String savedShearedFilePath;
    String savedWatermarkedFilePath;
    String savedBWFilePath;
    String savedAddColorFilePath;

    public Controller() {
    }


    public void uploadButtonClick() throws InfoException, IOException {

        /**
         * Upload an image from laptop, only allow the PNG, JPEG, BMP
         */
        FileChooser fileChooser = new FileChooser();
        //ExtensionFilter pngImage = new ExtensionFilter("PNG image", "*.png");
        //ExtensionFilter jpegImage = new ExtensionFilter("JPEG image", "*.jpeg");
        //ExtensionFilter bmpImage = new ExtensionFilter("BMP image", "*.bmp");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpeg", "*.png", "*.bmp" ,"*.jpg"));
        //fileChooser.getExtensionFilters().addAll(pngImage, jpegImage, bmpImage);
        selectedFile = fileChooser.showOpenDialog(null);
        selectedFilePath = selectedFile.getPath();

        //Get File Format and File's Path
        //fileName = selectedFile.getName();
        fileName = selectedFile.getName();
        selectedFileName = fileName.substring(0, fileName.indexOf("."));
        String fileFormat = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        //filePath = "\"" + selectedFilePath + "\"";



        /**
         * Show the selected image on GUI
         */
        if (selectedFile != null) {
            //oriImage = ImageIO.read(new File(selectedFile.toURI().toString()));

            //ImageIO.write(oriImage, imageInfo.getImageFormat(), baos);
            oriImage = new Image(selectedFile.toURI().toString());
            System.out.println(selectedFile.toURI().toString());
            imageView.setImage(oriImage);
            //imageView.getItems().add(selectedFile.getName());
            //System.out.println(filePath);
            //System.out.println(selectedFilePath);
            heightLabel.setText("" + oriImage.getHeight());
            widthLabel.setText("" + oriImage.getWidth());
            filePathLabel.setText(selectedFilePath);

            //Get the image format using im4java
            imageInfo = new Info(String.valueOf(selectedFilePath), true);
            formatLabel.setText("  " + imageInfo.getImageFormat());
            //formatLabel.setText("  " + fileFormat);

            //the Status of the Image
            oriImageStatus = true;
        } else {
            System.out.println("file is not valid");
        }
    }

    /**
     * Allow user to download the converted image
     * @throws InfoException
     */
    @FXML
    public void downloadButtonClick() throws InfoException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        chooseSavedFolder = directoryChooser.showDialog(null);
        chooseSavedFolderPath = chooseSavedFolder.getPath();
        Info savedImageInfo = new Info(savedImagePath, true);
        System.out.println(savedImageInfo.getImageFormat());
        String chooseSavedImagePath = chooseSavedFolderPath + "/copy" + selectedFileName + "." + savedImageInfo.getImageFormat();
        System.out.println(selectedFileName);
        System.out.println(chooseSavedImagePath);
        ImageTools.copyFile(savedImagePath, chooseSavedImagePath);
    }


    /**
     * Convert the image to different format
     * @throws IM4JavaException
     * @throws InterruptedException
     * @throws IOException
     */
    @FXML
    public void jpegButtonClick() throws IM4JavaException, InterruptedException, IOException {
        filePath = "\"" + selectedFilePath + "\"";
        savedJPGFilePath = "convertedImages/copy"  + selectedFileName + ".jpg";
        //String savedJPGFilePath = "convertedImages/copy"  + selectedFileName + ".jpg";
        //System.out.println(savedJPGFilePath);

        if(oriImageStatus){
            ImageTools.convertFormat("JPEG", selectedFilePath, savedJPGFilePath);
            //formatLabel.setText("  JPEG");


            //Get the image format using im4java
            Info jpgImageInfo = new Info(savedJPGFilePath, true);
            formatLabel.setText("  " + jpgImageInfo.getImageFormat());


            // Show the converted image
            String convertedJPGFilePath = "file:" + savedJPGFilePath;
            System.out.println(convertedJPGFilePath);
            Image jpgSavedImage = new Image(convertedJPGFilePath);
            imageView.setImage(jpgSavedImage);
            filePathLabel.setText(savedJPGFilePath);

            savedImagePath = savedJPGFilePath;

        } else {
            System.out.println("Please upload your image first.");
        }
    }

    @FXML
    public void pngButtonClick() throws IM4JavaException, InterruptedException, IOException {

        savedPNGFilePath = "convertedImages/copy"  + selectedFileName + ".png";

        if(oriImageStatus){
            ImageTools.convertFormat("PNG", selectedFilePath, savedPNGFilePath);


            //Get the image format using im4java
            Info pngImageInfo = new Info(savedPNGFilePath, true);
            formatLabel.setText("  " + pngImageInfo.getImageFormat());


            // Show the converted image
            String convertedPNGFilePath = "file:" + savedPNGFilePath;
            //System.out.println(convertedPNGFilePath);
            Image pngSavedImage = new Image(convertedPNGFilePath);
            imageView.setImage(pngSavedImage);
            filePathLabel.setText(savedPNGFilePath);


            savedImagePath = savedPNGFilePath;

        } else {
            System.out.println("Please upload your image first.");
        }
    }

    @FXML
    public void bmpButtonClick() throws InterruptedException, IOException, IM4JavaException {
        savedBMPFilePath = "convertedImages/copy"  + selectedFileName + ".bmp";

        if(oriImageStatus){
            ImageTools.convertFormat("BMP", selectedFilePath, savedBMPFilePath);


            //Get the image format using im4java
            Info bmpImageInfo = new Info(savedBMPFilePath, true);
            formatLabel.setText("  " + bmpImageInfo.getImageFormat());


            // Show the converted image
            String convertedBMPFilePath = "file:" + savedBMPFilePath;
            //System.out.println(convertedBMPFilePath);
            Image bmpSavedImage = new Image(convertedBMPFilePath);
            imageView.setImage(bmpSavedImage);
            filePathLabel.setText(savedBMPFilePath);

            savedImagePath = savedBMPFilePath;

        } else {
            System.out.println("Please upload your image first.");
        }
    }

    @FXML
    public void gifButtonClick() throws InterruptedException, IOException, IM4JavaException {
        savedGIFFilePath = "convertedImages/copy"  + selectedFileName + ".gif";

        if(oriImageStatus){
            ImageTools.convertFormat("GIF", selectedFilePath, savedGIFFilePath);


            //Get the image format using im4java
            Info gifImageInfo = new Info(savedGIFFilePath, true);
            formatLabel.setText("  " + gifImageInfo.getImageFormat());


            // Show the converted image
            String convertedGIFFilePath = "file:" + savedGIFFilePath;
            //System.out.println(convertedPDFFilePath);
            Image gifSavedImage = new Image(convertedGIFFilePath);
            imageView.setImage(gifSavedImage);
            filePathLabel.setText(savedGIFFilePath);

            savedImagePath = savedGIFFilePath;

        } else {
            System.out.println("Please upload your image first.");
        }
    }

    /**
     * Black and White Button
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    @FXML
    public void blackAndWhiteButtonClick() throws InterruptedException, IOException, IM4JavaException{
        savedBWFilePath = "convertedImages/copy"  + fileName;

        if(oriImageStatus){
        ImageTools.blackAndWhiteImage(selectedFilePath, savedBWFilePath);

        //Get the image format using im4java
        ButtonClick bwButton = new BWButton();
        bwButton.click(savedBWFilePath, formatLabel);

        // Show the converted image
        String bwFilePath = "file:" + savedBWFilePath;
        //System.out.println(convertedPDFFilePath);
        Image bwSavedImage = new Image(bwFilePath);
        imageView.setImage(bwSavedImage);
        filePathLabel.setText(savedBWFilePath);
        heightLabel.setText("" + bwSavedImage.getHeight());
        widthLabel.setText("" + bwSavedImage.getWidth());

        savedImagePath = savedBWFilePath;

    } else {
        System.out.println("Please upload your image first!");
    }
    }

    /**
     * Add color on the image
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    @FXML
    public void addColorButtonClick() throws InterruptedException, IOException, IM4JavaException{
        savedAddColorFilePath = "convertedImages/copy"  + fileName;

        if(oriImageStatus){
            ImageTools.thresholdImage(selectedFilePath, savedAddColorFilePath);

            //Get the image format using im4java
            ButtonClick addColorButton = new AddColorButton();
            addColorButton.click(savedAddColorFilePath, formatLabel);

            // Show the converted image
            String addColorFilePath = "file:" + savedAddColorFilePath;
            //System.out.println(convertedPDFFilePath);
            Image addColorSavedImage = new Image(addColorFilePath);
            imageView.setImage(addColorSavedImage);
            filePathLabel.setText(savedAddColorFilePath);
            heightLabel.setText("" + addColorSavedImage.getHeight());
            widthLabel.setText("" + addColorSavedImage.getWidth());

            savedImagePath = savedAddColorFilePath;

        } else {
            System.out.println("Please upload your image first!");
        }
    }

    /**
     * Rotate the Image
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    @FXML
    public void rotateButtonClick() throws Exception {
        savedRotatedFilePath = "convertedImages/copy"  + fileName;

        if(oriImageStatus){
            ImageTools.rotateImage(selectedFilePath, savedRotatedFilePath, 500, 500);

            //Get the image format using im4java
            ButtonClick rotateButton = new RotateButton();
            rotateButton.click(savedRotatedFilePath, formatLabel);

            // Show the converted image
            String rotatedFilePath = "file:" + savedRotatedFilePath;
            //System.out.println(convertedPDFFilePath);
            Image rotatedSavedImage = new Image(rotatedFilePath);
            imageView.setImage(rotatedSavedImage);
            filePathLabel.setText(savedRotatedFilePath);
            heightLabel.setText("" + rotatedSavedImage.getHeight());
            widthLabel.setText("" + rotatedSavedImage.getWidth());

            savedImagePath = savedRotatedFilePath;

        } else {
            System.out.println("Please upload your image first!");
        }
    }

    /**
     * Shear the height and width of the image are no more than 500
     * @throws Exception
     */
    @FXML
    public void shearButtonClick() throws Exception {
        savedShearedFilePath = "convertedImages/copy"  + fileName;

        if(oriImageStatus){
            ImageTools.shearImage(selectedFilePath, savedShearedFilePath, 500, 500);

            //Get the image format using im4java
            ButtonClick shearButton = new ShearButton();
            shearButton.click(savedShearedFilePath, formatLabel);

            // Show the converted image
            String shearedFilePath = "file:" + savedShearedFilePath;
            //System.out.println(convertedPDFFilePath);
            Image shearedSavedImage = new Image(shearedFilePath);
            imageView.setImage(shearedSavedImage);
            filePathLabel.setText(savedShearedFilePath);
            heightLabel.setText("" + shearedSavedImage.getHeight());
            widthLabel.setText("" + shearedSavedImage.getWidth());

            savedImagePath = savedShearedFilePath;

        } else {
            System.out.println("Please upload your image first!");
        }
    }


    /**
     * Add watermark on the image
     * @throws Exception
     */
    @FXML
    public void watermarkButtonClick() throws Exception{
        savedWatermarkedFilePath = "convertedImages/copy"  + fileName;

        if(oriImageStatus){
            ImageTools.addImgText(selectedFilePath, savedWatermarkedFilePath);

            //Get the image format using im4java
            ButtonClick watermarkButton = new ShearButton();
            watermarkButton.click(savedWatermarkedFilePath, formatLabel);

            // Show the converted image
            String watermarkFilePath = "file:" + savedWatermarkedFilePath;
            Image watermarkedImage = new Image(watermarkFilePath);
            imageView.setImage(watermarkedImage);
            filePathLabel.setText(savedWatermarkedFilePath);

            savedImagePath = savedWatermarkedFilePath;

        } else {
            System.out.println("Please upload your image first and then type the correct height and width.");
        }
    }

}

