/*
 * Tristan Hayes
 * Program #2 - jUnit
 */
import java.lang.Math;
public class Program2 {

    // TODO #1: finish the method's implementation
    public static double add(double a, double b) {
        return (a+b);
    }

    // TODO #2: finish the method's implementation
    public static double subtract(double a, double b) {
        return (a-b);
    }

    // TODO #3: finish the method's implementation
    public static double multiply(double a, double b) {
        return (a*b);
    }

    // TODO #4: finish the method's implementation
    public static double divide(double a, double b) {
        return (a/b);
    }

    // TODO #5: finish the method's implementation - assume right triangle
    public static double sinOfAngle(double oppSide, double hyp) {
        return (oppSide/hyp);
    }

    // TODO #6: finish the method's implementation - assume right triangle
    public static double hypOfTriangle(double sideA, double sideB) {
        return Math.sqrt(Math.pow(sideA,2)+Math.pow(sideB,2));
    }
    public static void main(String[] args) {
        System.out.println("5+6="+add(5,6));
        System.out.println("5-6="+subtract(5,6));
        System.out.println("5*6="+multiply(5,6));
        System.out.println("5/6="+divide(5, 6));
        System.out.println("Opp Side:3.0\nHyp:4.1\nSine:"+sinOfAngle(3,4.1));
        System.out.println("Side 1:1\nSide 2:1\nHyp:"+hypOfTriangle(1,1));
    }
}
