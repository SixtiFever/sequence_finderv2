import java.io.*;
import java.util.Random;

/**
 * program that takes in an argument (String), and searches a txt document for the argument
 * */
public class Run {
    public static void main(String[] args){

        SequenceFinder sf = new SequenceFinder("text.txt");


        // generates new text document
        if( args.length != 0 && args[0].equalsIgnoreCase("new")){
            sf.generateChars();
        } else {
            sf.findSequence("word");
        }


       // sf.findSequence("word");

    }
}

class SequenceFinder {

    String fileName;
    FileOutputStream fout;
    int sequenceCount = 0;
    final int TEXT_CHAR_COUNT = 10000;
    boolean foundOccurance = false;

    /**
     * sets fileName the instance will be connecting to.
     *
     * */
    SequenceFinder(String fname){
        this.fileName = fname;
    }


    /**
     * searches the text document for the argument
     * */
    void findSequence(String sequence){  // sequence will be from Scanner item
        char ch = sequence.charAt(0);
        int i,j;

        // used BufferedInputStream to enable mark/reset in situations of partial matching, and having to reset backwards by an offset x
        try(BufferedInputStream fin = new BufferedInputStream(new FileInputStream(this.fileName))){
            do {
                i = (char) fin.read();


                // if there is a match
                if( (char)i == ch ){
                    fin.mark(sequence.length());

                    for( j = 0; j < sequence.length(); j++ ){
                        ch = sequence.charAt(j);

                        if( (char) i == ch) {

                            if ( j == sequence.length() - 1 ){
                                System.out.println("sequence found. ");
                                sequenceCount += 1;
                                foundOccurance = true;
                                break;
                            }

                            i = (char) fin.read();

                        } else {
                            ch = sequence.charAt(0);
                           fin.reset();  // reset to where
                            break;
                        }
                    }
                }

                // if end of file is reached without finding a match
                if ( !foundOccurance && i == (char) -1 ){
                    System.out.println("End of file reached.");
                }

            } while ( i != (char)-1);
        } catch (IOException e){
            System.out.println(e);
        }
        System.out.println("Occurances found: " + sequenceCount);
    }

    /**
     * generate text file full of random characters
     * */
    void generateChars(){
        int i,j;
        try(FileOutputStream fout = new FileOutputStream(this.fileName)){
            Random r = new Random();
            for( i = 0; i < TEXT_CHAR_COUNT; i++ ){
                j = r.nextInt(33,126);
                fout.write((char)j);
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }



}
