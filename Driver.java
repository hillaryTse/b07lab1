import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Driver {
	public static void main(String [] args) throws Exception{

		//		double [] c1 = {6,0,0,5};
		//		double [] c2 = {0,-2,0,0,-9};

        /*double[] coef = {1,2,2};
        int[] expo = {2,1,1};
        double[] coef1 = {-2,-16,0,-2};
        int[] expo1 = {1,0,1,1};*/
		
		double[] coef = {-1,-4};
        int[] expo = {1,2};
        double[] coef1 = {1,5};
		int[]expo1 = {1,2};

        Polynomial p1 = new Polynomial(coef, expo);
        Polynomial p2 = new Polynomial(coef1, expo1);
        Polynomial p3 = p2.add(p1);
		
        System.out.println("\ntest one\n");
        for(int i=0; i < p3.exp.length; i++){
            System.out.println("coeff ["+ i + "] = " + p3.coeff[i]);
            System.out.println("exp ["+ i + "] = " + p3.exp[i] + "\n");
        }

		System.out.println("coeff = ");
       for(int i=0; i < p3.exp.length; i++){
            System.out.println(p3.coeff[i] + ",");
        }

		System.out.println("exp = ");
       for(int i=0; i < p3.exp.length; i++){
            System.out.println(p3.exp[i] + ",");
        }
		
		/* 
		System.out.println("p3(.1) = " + p3.evaluate(.1));

		if(p3.hasRoot(1))
			System.out.println("1 is a root of p3");
		else
			System.out.println("1 is not a root of p3");
		*/

		
        double[] coef2 = {1,-2,0};
        int[] expo2 = {1,0,2};
        double[] coef3 = {1,2,3};
        int[] expo3 = {1,0,2};
        Polynomial p4 = new Polynomial(coef2, expo2);
        Polynomial p5 = new Polynomial(coef3, expo3);
        Polynomial p6 = p5.multiply(p4);
	
        
        System.out.println("\ntest two\n");
        for(int i=0; i < p6.exp.length; i++){
            System.out.println("coeff ["+ i + "] = " + p6.coeff[i]);
            System.out.println("exp ["+ i + "] = " + p6.exp[i] + "\n");
        }

		System.out.println("p6(.3) = " + p6.evaluate(.3));
        
        BufferedReader input = new BufferedReader(new FileReader("myfile.txt"));
        File temp = new File("C:\\Users\\hillq\b07lab1\\myfile.txt");
        File file = new File("C:\\Users\\hillq\b07lab1\\myfile.txt");
        Polynomial p7 = new Polynomial(file);
        System.out.println("***DRIVER***");
        for(int i=0; i < p7.exp.length; i++){
            System.out.println("coeffecient ["+ i + "] = " + p7.coeff[i]);
            System.out.println("exponent ["+ i + "] = " + p7.exp[i] + "\n");
        }
        
        p7.saveToFile("test");

        
    }
}