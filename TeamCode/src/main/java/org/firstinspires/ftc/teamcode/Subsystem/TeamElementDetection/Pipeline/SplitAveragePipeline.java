package org.firstinspires.ftc.teamcode.Subsystem.TeamElementDetection.Pipeline;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.List;

public class SplitAveragePipeline extends OpenCvPipeline {

    List<Scalar> ELEMENT_COLOR_RED = Arrays.asList(new Scalar(0,100,100), new Scalar(25,255,255), new Scalar(160,100,100), new Scalar(255,255,255)); // Range for red color
    List<Scalar> ELEMENT_COLOR_BLUE = Arrays.asList(new Scalar(100,100,100), new Scalar(115,255,255)); // Range for blue color

    static int color_zone = 3; // Default to 3

    int toggleShow = 1;

    Mat original;

    Mat zone1;
    Mat zone2;

    Scalar avgColor1;
    Scalar avgColor2;

    double distance1 = 1;
    double distance2 = 1;

    double max_distance = 0;

    @Override
    public Mat processFrame(Mat input) {
        // Creating duplicate of original frame with no edits
        original = input.clone();

        // Defining Zones
        // Rect(top left x, top left y, bottom right x, bottom right y)
        zone1 = input.submat(new Rect(75, 380, 300, 230));
        zone2 = input.submat(new Rect(735, 380, 300, 230));

        // Convert input frame to HSV color space
        Imgproc.cvtColor(zone1, zone1, Imgproc.COLOR_RGB2HSV_FULL);
        Imgproc.cvtColor(zone2, zone2, Imgproc.COLOR_RGB2HSV_FULL);

        // Continue with your color detection logic...
        // For example, you might calculate the average color in HSV space as follows:

        avgColor1 = Core.mean(zone1);
        avgColor2 = Core.mean(zone2);

        // Putting averaged colors on zones (we can see on camera now)
        zone1.setTo(avgColor1);
        zone2.setTo(avgColor2);

        distance1 = Math.min(color_distance(avgColor1, ELEMENT_COLOR_RED), color_distance(avgColor1, ELEMENT_COLOR_BLUE));
        distance2 = Math.min(color_distance(avgColor2, ELEMENT_COLOR_RED), color_distance(avgColor2, ELEMENT_COLOR_BLUE));

        if (distance1 > 195 && distance2 > 190) {
            max_distance = -1;
            System.out.println("NO color Detected");
        } else {
            max_distance = Math.min(distance1, distance2);

            if (max_distance == distance1) {
                color_zone = 1;
                System.out.println("Zone 1 detected color");
            } else {
                color_zone = 2;
                System.out.println("Zone 2 detected color");
            }
        }

        // Allowing for the showing of the averages on the stream
        if (toggleShow == 1) {
            return input;
        } else {
            return original;
        }
    }

    public double color_distance(Scalar color1, List<Scalar> colorRange) {
        double h1 = color1.val[0];
        double s1 = color1.val[1];
        double v1 = color1.val[2];

        double hMin = colorRange.get(0).val[0];
        double sMin = colorRange.get(0).val[1];
        double vMin = colorRange.get(0).val[2];

        double hMax = colorRange.get(1).val[0];
        double sMax = colorRange.get(1).val[1];
        double vMax = colorRange.get(1).val[2];

        double distance = Math.sqrt(Math.pow(Math.max(0, Math.max(h1 - hMax, hMin - h1)), 2)
                + Math.pow(Math.max(0, Math.max(s1 - sMax, sMin - s1)), 2)
                + Math.pow(Math.max(0, Math.max(v1 - vMax, vMin - v1)), 2));

        return distance;
    }

    public boolean isInRange(Scalar color, List<Scalar> colorRange) {
        return (color_distance(color, colorRange) >= 0 && color_distance(color, colorRange) <= 100);
    }

    public int setAlliancePipe(String alliance) {
        if (alliance.equals("red")) {
            ELEMENT_COLOR_RED = AArrays.asList(new Scalar(0,100,100), new Scalar(25,255,255), new Scalar(160,100,100), new Scalar(255,255,255));
        } else {
            ELEMENT_COLOR_BLUE = Arrays.asList(new Scalar(100,100,100), new Scalar(115,255,255));;
        }
        return 0;
    }

    public int get_element_zone() {
        return color_zone;
    }
}
