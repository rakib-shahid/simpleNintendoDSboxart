import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ds{
    public static void main(String[] args) throws IOException{
        //take drive letter of SD Card from user
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter drive letter: ");
        String driveLetter = scan.nextLine();
        scan.close();
        String romsPath = driveLetter + ":/roms/nds";

        //UNCOMMENT AND CHANGE THIS IF YOUR ROMS PATH IS DIFFERENT FROM "D:/roms/nds"
        //romsPath = "put path here";

        //reads files in default rom location
        File folder = new File(romsPath);
        File[] fileList = folder.listFiles();

        //arrays for storing serial and file names
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> serials = new ArrayList<String>();

        for (File file : fileList) {
            if (file.isFile()) {
                titles.add(file.getName());

                //reads serial from nds
                FileInputStream fileIn = new FileInputStream(file.toPath().toString());
                fileIn.skip(12);
                String gameSerial = "";
                for (int i = 0; i < 4; i++){
                    gameSerial += (char)fileIn.read();
                }

                serials.add(gameSerial);
            }
        }


        //print to terminal (for testing)
        for (int i = 0; i < serials.size(); i++){
            System.out.println("Serial = " + serials.get(i) + "\nFile Name = " + titles.get(i) + "\n");
        }
        

        
        for (int i = 0; i < serials.size(); i++){
            File dest = new File(driveLetter+File.separator+"_nds"+File.separator+"TWiLightMenu"+File.separator+"boxart"+File.separator+serials.get(i)+".png");
            File source = new File("coverS"+File.separator+"US"+File.separator+serials.get(i)+".png");
            try {
                Files.copy(source.toPath(), dest.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

    }
}
