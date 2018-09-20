/**
 * Write a description of class Rover here.
 *
 * @author Emily Wilber
 * @version Sept. 17, 2018
 */

    
public class Rover
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;  
    private int dir;
    private int origX;
    private int origY;
    private String name;
    private boolean isAlive = true;
    private int numPics = 0;
    private int power = 100;
    private int health = 20;
    
    /**
     * Constructor for objects of class Rover
     */
    public Rover(String name)
    {
        // initialise instance variables
        this.x = 0;
        this.origX = 0;
        this.y = 0;
        this.origY = 0;
        this.dir = 0;
        this.name = name;
    }

    /**
     * Constructor for objects of class Rover
     */
    public Rover(String name, int x, int y, int dir)
    {
        // initialise instance variables
        this.x = x;
        this.origX = x;
        this.y = y;
        this.origY = y;
        this.dir = dir;
        this.name = name;
    }
    
    /**
     * Determines if the rover has power and is alive
     */
    private boolean willWork()
    {
        if (this.power == 0){
            return false;
        }
        else if (!this.isAlive) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Determines if the rover has power and is alive 
     * AND has enough power to do the action
     * 
     * @param powerNeeded    the amount of power that will be substracted from the rover's
     *                       power variable if it has enough
     */
    private boolean willWork(int powerNeeded)
    {
        if (this.power == 0){
            return false;
        }
        else if (!this.isAlive) {
            return false;
        }
        else if (this.power < powerNeeded) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Determines what's wrong (out of power or dead) if it won't work
     */
    private void findWhatsWrong()
    {
        if (this.power == 0){
            System.out.println(this.name + " is out of power!");
        }
        else if (this.isAlive == false) {
            System.out.println(this.name + " can't do anything. He ded!");
        }
        else {
            System.out.println("This message should not appear");
        }
    }
    
    /**
     * Determines what's wrong (out of power or dead OR doesn't have enough 
     * power to do the action) if it won't work
     * 
     * @param powerNeeded    the amount of power that will be substracted from the rover's
     *                       power variable if it has enough
     */
    private void findWhatsWrong(int powerNeeded)
    {
        if (this.power == 0){
            System.out.println(this.name + " is out of power!");
        }
        else if (this.isAlive == false) {
            System.out.println(this.name + " can't do anything. He ded!");
        }
        else if (this.power < powerNeeded) {
            System.out.println(this.name + " does not have enough power to do that right now!");
        }
        else {
            System.out.println("This message should not appear");
        }
    }
    
    /**
     * Adds a given amount to the power of a rover but doesnt let it
     * exceed 100
     * 
     * @param amount     the amount that will be added to the rover's charge if it doesn't exceed 100
     */
    public void charge(int amount)
    {
        if (!this.isAlive) {
            System.out.println(this.name + " can't do anything. He ded!");
        }
              
        else {
            power += amount; 
            if (power < 100) {
                System.out.println(this.name + " charged up " + amount + "% and is at " + power + " % power!");
            }
            if (power >= 100) {
                power = 100;
                System.out.println(this.name + " is at full power!");
            }
        }
              
        
    }
    
    /**
     * Tells the cardinal direction of the direction the rover is facing
     * 
     * @param dir   the numerical representation of a cardinal direction
     */
    private String getDirectionName(int dir)
    {
        if (dir == 0) {
            return "North";
        } 
        else if (dir == 1) {
            return "NorthEast";
        } 
        else if (dir == 2) {
            return "East";
        } 
        else if (dir == 3) {
            return "SouthEast";
        } 
        else if (dir == 4) {
            return "South";
        } 
        else if (dir == 5) {
            return "SouthWest";
        } 
        else if (dir == 6) {
            return "West";
        } 
        else {
            return "NorthWest";
        } 
    }
        
    /** Rotates the rover once to the right 
     */
    public void rotateRight()
    {
        //  It takes 2 power to turn
        if (!willWork(2)) {
            findWhatsWrong(2);
        }
        
        else {
            dir += 1;
            if (dir > 7) {
                dir -= 8;
            }
            this.power -= 2;
            
            System.out.println(this.name + " turned to the right! " 
            + this.name + " is now facing "+ getDirectionName(dir) + "!");
        }  
    }
    
    /** Rotates the rover once to the left
    */
    public void rotateLeft()
    {
        if (!willWork(2)) {
            findWhatsWrong(2);
        }
        
        else {
            dir -= 1;  
            if (dir < 0) {
                dir += 8;
            }
            
            this.power -= 2;
            
            System.out.println(this.name + " turned to the left! " + this.name
            + " is now facing " + getDirectionName(dir) + "!");
        } 
    }
    
    /** Rotates the rover amount number of times to the right if the int is pos
     * and to the left if the int is neg
     * 
     * @param amount    the number of times the rover will turn left if negative or right if positive
    */
    public void rotate(int amount)
    {
        if (!willWork(2 * Math.abs(amount))) {
            findWhatsWrong(2* Math.abs(amount));
        }
        
        else {
            if (amount > 0) {
                dir += amount;
                dir = dir % 8; 
            }  
            else {
                dir += amount;
                while (this.dir < 0){
                    dir += 8; 
                }
            }
            
            this.power -= (2 * Math.abs(amount));
            
            System.out.println(this.name + " turned " + Math.abs(amount)
            + " times! " + this.name
            + " is now facing " + getDirectionName(dir) + "!");
        } 
    }
    
    /** Makes the rover move amount number of units in the direction he is 
     * facing
     * 
     * @param amount   the number of times the rover will move in the direction it is facing
     */
    public void move(int amount)
    {
        // takes 5 power to move per unit
        if (!willWork(5 * amount)) {
            findWhatsWrong(5 * amount);
        }
        
        else {
            if (dir == 0) {
                y += amount;
            }    
            if (dir == 1) {
                x += amount;
                y += amount;
            }  
            if (dir == 2) {
                x += amount;
            }  
            if (dir == 3) {
                x += amount;
                y -= amount;
            } 
            if (dir == 4) {
                y -= amount;
            }  
            if (dir == 5) {
                x -= amount;
                y -= amount;
            }  
            if (dir == 6) {
                x -= amount;
            } 
            if (dir == 7) {
                x -= amount;
                y += amount;
            } 
            this.power -= (5 * amount);
            
            System.out.println(this.name + " moved " + amount + " spaces forward!");
        } 
    }
    
    /**
     * Will figure out a path to reach a certain point using move() 
     * and rotate() functions but only in 4-directional motion 
     * 
     * @param x   horizontal location of the desired location 
     * @param y   vertical location of the desired location 
     * 
     */
    public void moveTo(int x, int y)
    {
        int rotate1 = 0;
        if (x > this.x) { // if rover goes right
            if (this.dir > 2 && this.dir < 6) {
                rotate1 = this.dir - 2;
            }
            else if (this.dir <= 6) {
                rotate1 = (-1 * this.dir) + 10;
            }
            else if (this.dir >= 0 && this.dir < 3){
                rotate1 = (-1 * this.dir) + 2;
            }
        }
        
        if (x < this.x) { // if rover goes left
            if (this.dir > 2 && this.dir < 6) {
                rotate1 = (-1 * this.dir) + 6;
            }
            else if (this.dir >= 6){
                rotate1 = this.dir - 6;
            }
            else if (this.dir >= 0 && this.dir < 3){
                rotate1 = this.dir + 2;
            }
        }
        
        int potPower = (5 * (Math.abs(x - this.x) + (Math.abs(y - this.y))) + (2 * rotate1) + (4)); 
        // the 4 at the end is for turning north or south once the rover is facing east or west ( 2 * 2 turns)
        if (!this.isAlive) {
            System.out.println(this.name + " is ded!");
        }
        else if (this.power < potPower) {
            System.out.println(this.name + " is absolutely certain that he does not have enough power to move that far.");
        }
        else{
            if (x > this.x) { // if the rover is going to the right
                // dir 2 is east, dir 6 is west. 
                if (this.dir > 2 && this.dir < 6) { // if the rover is facing southeast, south, or southwest
                    while (this.dir != 2) { // while he isnt facing east
                        this.rotateLeft();
                    }
                }
                else {
                    while (this.dir != 2) { // while he isnt facing east
                        this.rotateRight();
                    }
                }
                this.move(Math.abs(x - this.x));
                    System.out.println(this.name + " turned East and moved " 
                    + (x - this.x) + " units forward!");
            }    
            else { // if the rover is going to the left
                if (this.dir > 2 && this.dir < 6) { // if the rover is southeast, south, or southwest
                    while (this.dir != 6) { // while he isnt facing west
                        this.rotateRight();
                    }
                }
                else {
                    while (this.dir != 6) { // while he isnt facing west
                        this.rotateLeft();
                    }
                }
                this.move(Math.abs(x - this.x));
                    System.out.println(this.name + " turned West and moved " 
                    + Math.abs(x - this.x) + " units forward!");
            }
            
            if (y > this.y) { // if the rover is going up
               if (this.dir < 4) { // if the rover is facing southeast, east, or northeast
                   while (this.dir != 0) { //while he isnt facing north
                       this.rotateLeft();
                   }
                }
               else {
                  while (this.dir != 0) { //while he isnt facing north
                       this.rotateRight();
                  } 
               } 
               this.move(Math.abs(y - this.y));
               System.out.println(this.name + " turned North and moved "
               + Math.abs(y - this.y) + " units forward!");
            }
            else { // if the rover is going down
                if (this.dir > 4) { // if the rover is facing southwest, west, or northwest
                   while (this.dir != 4) { //while he isnt facing south
                       this.rotateLeft();
                   }
                }
               else { // if the rover is facing southwest, west, or northwest
                  while (this.dir != 4) { //while he isnt facing south
                       this.rotateRight();
                  } 
               } 
               this.move(Math.abs(y - this.y));
               System.out.println(this.name + " turned South and moved "
               + Math.abs(y - this.y) + " units forward!");
            }
        }
    }
    
    /**
     * Moves the Rover to its original location using the moveTo method
     */
    public void goHome()
    {
        if (!willWork()) {
            findWhatsWrong();
        }  
        
        else {
            this.moveTo(this.origX, this.origY);
        }
    }
    
    
    /**
     * Subtracts points of damage from the other rover that is being attacked
     * 
     * @param Rover other    takes the name of another rover that will take damage
     */
    public void attack(Rover other) 
            
        {
        int critical = (int)(10 * Math.random());
        
            if (!willWork(10)) {
                findWhatsWrong(10);
            }
            else {
                System.out.println(this.name + " shoots " + other.name + "!");
                
                if (critical == 1) {
                    other.health -= 5 + (Math.random() * 6);
                    System.out.println("Wow! A critical hit!");
                }
                else {
                    other.health -= 5;
                }
    
                if (other.health <= 0) {
                    other.health = 0;
                    other.isAlive = false;
                    System.out.println(other.name 
                    + " was absolutely obliterated by " + this.name + ".");
                }
                else {
                    System.out.println(other.name + " now has " + other.health 
                    + " health!");
                    
                }   
                this.power -= 10;
        } 
    }
    
    /**
     * Instantly moves the rover to whatever (x,y) coordinate is inputted
     * 
     * @param x   horizontal location of the desired location 
     * @param y   vertical location of the desired location  
     */
    public void teleport(int x, int y) {
        if (!willWork(50)) {
            findWhatsWrong(50);
        }
        
        else {
            this.x = x;
            this.y = y;
            this.power -= 50;
            
            System.out.println("OwO!!! " + this.name + " teleported!");
            }   
    }
    
    /**
     * Adds 1 to the numPics variable unless the rover already has 5 photos
     */
    public void takePicture() {
        if (!willWork(1)) {
            findWhatsWrong(1);
        }
        
        else {
            // the rover cant take more than 5 photos
            if (numPics < 5) {
                this.numPics += 1;
                this.power -= 1;
                
                System.out.println("My boy, " + this.name + ", took a picture at ("
                + this.x + ", " + this.y + "), facing " + getDirectionName(this.dir) 
                + "!");
            }
            else {
                System.out.println(this.name + "tried to take a picture but the camera's memory is already full!");
            }
        }   
    }
    
    /**
     * Sends all pics away and sets numPics = 0
     */
    public void transmitPicture() {
        if (!willWork(5)) {
            findWhatsWrong(5);
        }
        
        else{
            this.numPics = 0;
            this.power -= 5;
            
            System.out.println(this.name + "transmitted his photos back to Earth! He now has nothing to call his own..");
        }
    }
    
    /** Tells you where the rover is and where it's facing 
    */
    public String toString()
    {
     return "[Name=" + name + "] [x=" + x + "] [y=" + y +
            "] [dir=" + getDirectionName(dir) + "] [isAlive=" + isAlive 
            + "] [numPics=" + numPics + "] [power=" + power + "] [health=" 
            + health + "]";
    }    
}
