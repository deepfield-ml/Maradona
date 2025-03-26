/*
    /\  \      Project Maradona is developed by DeepField ML and under GPL License. 
   |::\  \   
   |:|:\  \    ███████   ██                    ██ PixelWARP a2.06. © Will Chen 2025. 
 __|:|\:\  \  /██////██ //                    /██ 
/DFML|_\:\__\ /██   /██  ██  ██   ██   █████  /██ ██╗    ██╗ █████╗ ██████╗ ██████╗ 
\:\~~\  \/__/ /███████  /██ //██ ██   ██///██ /██ ██║    ██║██╔══██╗██╔══██╗██╔══██╗
 \:\  \       /██////   /██  //███   /███████ /██ ██║ █╗ ██║███████║██████╔╝██████╔╝
  \:\  \      /██       /██   ██/██  /██////  /██ ██║███╗██║██╔══██║██╔══██╗██╔═══╝ 
   \:\__\     /██       /██  ██ //██ //██████  ██ ╚███╔███╔╝██║  ██║██║  ██║██║     
    \/__/     //        //  //   //   ////// ///   ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     
*/

import java.awt.geom.Point2D;
import java.util.*;

public class pixelWarp2{

    public static Point2D.Double[] rotated = new Point2D.Double[] {
        new Point2D.Double(), new Point2D.Double(), new Point2D.Double(), new Point2D.Double()
    };
    public static Point2D.Double dimensions = new Point2D.Double();
    public static double rotation;
    public static double depression;
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        startup();
    }

    public static void startup(){
        System.err.println("""
                                                    PixelWARP a2.06: General algorithm improvements. \s
                                                                       Enter "/help" to get started. \s
                """);
        String help = """
                        ____________________________________________________________________________
                        Welcome to PixelWARP, developed by DeepField ML. Here's how to use PixelWARP:\s
                              \s
                              "/help" ••••••••••••••••••••••••••••••••• Open this help guide.\s
                              \s
                              "/exit" ••••••••••••••••••••••••••• Exit the PixelWARP program.\s
                              \s
                              "/restart" ••••••••••••••••••••• Restart the PixelWARP program.\s
                              \s
                              "/cli" ••••••••••••••••••••••••••••• Activate Commandline mode.\s
                                                 This mode will request variables and perform\s
                                                         the PixelWARP algorithms as regular.\s
                              \s
                              "/gui" •••••••••••••••••••••••••••••••• Activate Graphics mode.\s
                                                   This mode will create windows that animate\s
                                                        the PixelWARP algorithms for display.\s
                                                           The UI is dynamic to the terminal.\s
                              \s
                              "/allgui" •••••••••••••••••••••••••• Graphics mode on steroids.\s
                                                      All animations are dependent on the UI.\s
                                                        PixelWARP functions normally, but may\s
                                                       not be as dynamic as regular GUI mode.\s
                              \s
                              "/debug" ••••••••••••••••••••••••••••••••• Activate Debug mode.\s
                                           This mode will use predefined changeable variables\s
                                           for debugging and testing of PixelWARP algorithms.\s
                       """;
        switch(s.nextLine().toLowerCase()){
            case "/help":
                System.out.println(help);
                startup();
            case "/exit":
                System.err.println("Exiting PixelWARP.");
                System.exit(0);
            case "/cli":
                cli();
            case "/gui":
            case "/allgui":
            case "/restart":
                startup();
            case "/debug":
            default:
                System.out.println("\n This is an invalid token. Here's a guide: \n" + help);
                startup();
        }

    }

    public static void cli(){
        System.out.println("Enter the dimensions of the Original (unwarped) Rectangle\n" +
                           "                                              (ie. 40,60):");
        String[] sideValues = s.nextLine().replace(" ","").split(",");

        dimensions.x = Double.parseDouble(sideValues[0]);
        dimensions.y = Double.parseDouble(sideValues[1]);

        System.out.println("Enter the rotation of the angle Counter-Clockwise\n" +
                           "                                        (ie. 45):");
        rotation = s.nextDouble(); s.nextLine();
        if(rotation >= 180) rotation = rotation - 180 * (rotation / 180);
        else if(rotation <= -180) rotation = rotation + -(180 * (rotation / 180)); //Make rotation angle less than 180
        if(rotation > 90) rotation = -(rotation - 90); //Make rotation CW/CCW

        rotate(dimensions, rotation);

        System.out.println("Enter the camera depression angle above ground\n" +
                           "                                      (ie. 45):");
        depression = s.nextDouble(); s.nextLine();

    }

    public static void rotate(Point2D.Double dim, Double rot){
        rot = Math.toRadians(rot);
        double x = (dim.x / 2);
        double y = (dim.y / 2);
        double sin = Math.sin(rot);
        double cos = Math.cos(rot);

        double sinX = x * sin;
        double cosX = x * cos;
        double sinY = y * sin;
        double cosY = y * cos;

        rotated[0].x = -cosX - sinY; //UpperLeft X
        rotated[0].y = rotated[3].y = cosY - sinX; //UpperLeft Y, LowerLeft Y
        rotated[1].x = cosX - sinY; //UpperRight X
        rotated[1].y = sinX - cosY; //UpperRight Y
        rotated[2].x = cosX + sinY; //LowerRight X
        rotated[2].y = sinX + cosY; //LowerRight Y
        rotated[3].x = sinY - cosX; //LowerLeft X
    }

    public static void warp(){

    }
}
