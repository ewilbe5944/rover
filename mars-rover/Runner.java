
/**
 * Write a description of class Runner here.
 *
 * @author Emily Wilber
 * @version (a version number or a date)
 */
public class Runner
{
    public static void main(String[] args) {
        Rover r1 = new Rover("LilBoi");
        System.out.println("\n\n");
        System.out.println(r1);
        //-----------------------------------------------
        r1.moveTo(3,5);
        System.out.println(r1);
    }
}