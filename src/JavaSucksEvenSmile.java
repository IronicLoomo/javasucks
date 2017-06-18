import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class JavaSucksEvenSmile {
    private final double sF = 1.05;
    private CascadeClassifier faceCascade;
    private CascadeClassifier smileCascade;

    public JavaSucksEvenSmile()
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String facePath = "haarcascade_frontalface_default.xml";
        String smilePath = "haarcascade_smile.xml";

        faceCascade = new CascadeClassifier(facePath);
        smileCascade = new CascadeClassifier(smilePath);
    }

    public boolean detect(Mat image) {
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        MatOfRect faces = new MatOfRect();

        faceCascade.detectMultiScale(gray, faces, sF, 8, Objdetect.CASCADE_SCALE_IMAGE, new Size(55, 55), new Size());

        for (Rect face : faces.toList()) {
            Mat roi_gray = new Mat(gray, face);

            MatOfRect smiles = new MatOfRect();
            smileCascade.detectMultiScale(roi_gray, smiles, 1.7, 22, Objdetect.CASCADE_SCALE_IMAGE, new Size(25, 25), new Size());

            for (Rect smile : smiles.toList()) {
                return true;
            }
        }

        return false;
    }

}
