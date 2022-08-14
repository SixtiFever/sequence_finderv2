import java.io.*;
import java.util.Random;

/**
 * program that takes in an argument (String), and searches a txt document for the argument
 * */
public class Run {
    public static void main(String[] args){

        SequenceFinder sf = new SequenceFinder("text.txt");

        sf.generateChars();

    }
}

class SequenceFinder {

    String fileName;
    FileOutputStream fout;
    final int TEXT_CHAR_COUNT = 10000;

    /**
     * sets fileName the instance will be connecting to.
     *
     * */
    SequenceFinder(String fname){
        this.fileName = fname;
        if(checkFileConnection(fname)){
            System.out.println("Connected.");
            return;
        }
    }

    /**
     * generate text file full of random characters
     * */
    void generateChars(){
        int i,j;
        try(FileOutputStream fout = new FileOutputStream(this.fileName)){
            Random r = new Random();
            for( i = 0; i < TEXT_CHAR_COUNT; i++ ){
                j = r.nextInt(34,126);
                fout.write((char)j);
            }
        } catch (IOException e){
            System.out.println(e);
        }
        return;
    }


    /**
     * checks that the file connects
     * */
    private boolean checkFileConnection(String fileName){
        try(FileOutputStream fout = new FileOutputStream(fileName)){
            return true;
        } catch (IOException e){
            System.out.println(e);
            return false;
        }
    }

}
