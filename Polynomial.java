import java.lang.Math;

public class Polynomial {
	double [] coeff;
	
	// constructor
	public Polynomial() {
		// set array to 0
		coeff = new double[1];
		coeff[0]=0;

	}

	public Polynomial(double[] x) {
		// get length of
		// for, set all values of coeff == x
		coeff=new double[x.length];
		for (int i=0; i<x.length; i++) {
			coeff[i]=x[i];
		}

	}
	
	public Polynomial add(Polynomial poly) {
		// returns result of adding calling object + argument
		//coeff of type double, x of type Polynomial??
		// ex. Polynomial s = p1.add(p2); -- returns p1 added to p2 in which p1 and p2 are polynomials
		// how to refer to calling object??
		//Polynomial result = new Polynomial(x+coeff);
		
		// 2 double arrays. construct the new double array
			// return result = new Polynomial(result);
		Polynomial result = new Polynomial();

		int len1 = poly.coeff.length;
		int len2 = coeff.length;

		double[] array = new double[1];

		if (len1==len2) {
			array=new double[len1];

			for (int i=0; i<len1; i++) {
				array[i] = coeff[i]+poly.coeff[i];
			}
		}
		else if (len1>len2) {
			array=new double[len1];

			// for loop until, len2 reached
			for (int i=0; i<len2; i++) {
				array[i] = coeff[i]+poly.coeff[i];
			}

			for (int i=len2; i<len1; i++) {
				array[i] = poly.coeff[i];
			}

		}
		else if (len2>len1) {
			array=new double[len2];

			// for loop until, len1 reached
			int i=0;
			for (i=0; i<len1; i++) {
				array[i] = coeff[i]+poly.coeff[i];
			}

			for (i=len2; i<len2; i++) {
				array[i] = coeff[i];
			}

		}

		result = new Polynomial(array);
		return result;
	}

	public double evaluate(double x) {
		// sub in variable for double x, evaluate
		int length=0;
		length=coeff.length;
		double result=0.0;

		for (int i=0; i<length; i++) {
			if (i==0) {
				result=result+coeff[i];
			}
			else {
				result=result+(Math.pow(x, i))*coeff[i];
			}
			//System.out.println("poly[0]: "+coeff[i]);
			//System.out.println("result: " + result);
		}
		// i = power

		return result;
	}

	public boolean hasRoot(double x) {
		// determines whether or not x is a root of the polynomial or not
			// root -- value of x for which polynomial=0

		if (evaluate(x)==0) { 
			return true;
		}
		return false;
		}
	
}