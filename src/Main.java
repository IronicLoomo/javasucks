/**
 * Created by afa on 6/17/17.
 */

import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class Main {
    public static void main(String[] args)
    {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Hello World!");

        String facePath = "/home/afa/tchackathon/libs/share/OpenCV/haarcascades/haarcascade_frontalface_default.xml";
        String smilePath = "/home/afa/tchackathon/libs/share/OpenCV/haarcascades/haarcascade_smile.xml";

        CascadeClassifier faceCascade = new CascadeClassifier(facePath);
        CascadeClassifier smileCascade = new CascadeClassifier(smilePath);

        VideoCapture cap = new VideoCapture(0);
        cap.set(3, 640);
        cap.set(4, 680);

        double sF = 1.05;

        while (true) {
            Mat image = new Mat();
            cap.read(image);
            Mat gray = new Mat();
            Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

            MatOfRect faces = new MatOfRect();

            faceCascade.detectMultiScale(gray, faces, sF, 8, Objdetect.CASCADE_SCALE_IMAGE, new Size(55, 55), new Size());

            for (Rect face : faces.toList()) {
                Mat roi_gray = new Mat(gray, face);
                //Mat roi_color = new Mat(image, face);

                MatOfRect smiles = new MatOfRect();
                smileCascade.detectMultiScale(roi_gray, smiles, 1.7, 22, Objdetect.CASCADE_SCALE_IMAGE, new Size(25, 25), new Size());
                //System.out.println("Got a face!");

                for (Rect smile : smiles.toList()) {
                    System.out.println("Got a smile!");
                }
            }

        }


        /*

    for (x, y, w, h) in faces:
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 0, 255), 2)
        roi_gray = gray[y:y+h, x:x+w]
        roi_color = frame[y:y+h, x:x+w]

        smile = smileCascade.detectMultiScale(
            roi_gray,
            scaleFactor= 1.7,
            minNeighbors=22,
            minSize=(25, 25),
            flags=cv2.cv.CV_HAAR_SCALE_IMAGE
            )

        # Set region of interest for smiles
        for (x, y, w, h) in smile:
            print "Found", len(smile), "smiles!"
            cv2.rectangle(roi_color, (x, y), (x+w, y+h), (255, 0, 0), 1)
            #print "!!!!!!!!!!!!!!!!!"

    #cv2.cv.Flip(frame, None, 1)
    cv2.imshow('Smile Detector', frame)
    c = cv2.cv.WaitKey(7) % 0x100
    if c == 27:
        break
         */
    }
}
