import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Polynomial {
	double [] coeff;
	int[] exp;
	
	// no-arg constructor
	public Polynomial() {
		coeff = null;
		exp=null;

	}

	public Polynomial(double[] coeff, int[] exp) {
		this.coeff=new double[coeff.length];
		for (int i=0; i<coeff.length; i++) {
			this.coeff[i]=coeff[i];
		}

		this.exp=new int[exp.length];
		for (int i=0; i<exp.length; i++) {
			this.exp[i]=exp[i];
		}
	}

	public Polynomial(File file) throws Exception {
		// initialize polynomial using contents of file
		// assume contents contains: 1 line, no whitespace rep valid polynomial
			// ex. 5-3x2+7x8
		// use of split, parseInt, parseDouble
		coeff=new double[1];
		exp=new int[1];
		exp[0]=-1;
		
		BufferedReader input=new BufferedReader(new FileReader("myfile.txt"));

		String contents=input.readLine();
		input.close();

		String[] substr=contents.split("(((?=\\+))|((?=\\-)))");

		for (int i=0; i<substr.length; i++) {
			// parse all terms, split w delimiter x, if substr1.length==1 - exponent=0
			String[] split=substr[i].split("[x]");

			//exp=0
			if (split.length==1) {
				if (coeff.length==1 && exp[0]==-1) {
					exp[0]=0;
					coeff[0]=Double.parseDouble(split[0]);
				}
				else {
					coeff=Arrays.copyOf(coeff, coeff.length+1);
					exp=Arrays.copyOf(exp, exp.length+1);

					exp[exp.length-1]=0;
					coeff[coeff.length-1]=Double.parseDouble(split[0]);
				}
			}
		
			// exp!=0
			else {
				// exp=cast as int, coeff=cast as double
				if (coeff.length==1 && exp[0]==-1) {
					exp[0]=Integer.parseInt(split[1]);
					coeff[0]=Double.parseDouble(split[0]);
				}
				else {
					coeff=Arrays.copyOf(coeff, coeff.length+1);
					exp=Arrays.copyOf(exp, exp.length+1);

					exp[exp.length-1]=Integer.parseInt(split[1]);
					coeff[coeff.length-1]=Double.parseDouble(split[0]);
				}
			}
		}
	}

	public void saveToFile(String str) throws Exception {
		// String str = file name
		// translates polynomial to text and saves to file

		File file=new File(str+".txt");
		file.createNewFile();

		FileWriter writer=new FileWriter(str+".txt");
		BufferedWriter output=new BufferedWriter(writer);

		int empty=0;
		for (int i=0; i<exp.length; i++) {
			// omit + sign or neg coeff
			if ((empty==0 && coeff[i]>0) || coeff[i]<0) {
				output.write(String.valueOf((int)(coeff[i])));
				empty++;
			}
			// else - positive coeff, prepend +
			else {
				output.write("+"+String.valueOf((int)(coeff[i])));
				empty++;
			}

			// exponents!
			if (exp[i]!=0) {
				output.write("x"+String.valueOf(exp[i]));
			}
		}
		output.close();
	}
	
	public Polynomial add(Polynomial poly) {
		// returns result of adding calling obj + arg

		Polynomial result=new Polynomial();

		// initialized to content of calling obj
		double[] coefficients=new double[exp.length];
		int[] exponents=new int[exp.length];
		coefficients=coeff;
		exponents=exp;

		// int check - case that no exp in arg matches
		int check;
		for (int i=0; i<poly.exp.length; i++) {
			// if expB in expA - update coefficients
			check=0;
			int j=0;
			for (j=0; j<exponents.length; j++) {
				if (poly.exp[i]==exponents[j]) {
					coefficients[j]=coefficients[j]+poly.coeff[i];
					check=1;
					break;
				}
			}
			// else reinitialize to append
			if (check!=1) {
				// append coeff and exp
				// helper fn to update 
				coefficients=Arrays.copyOf(coefficients, coefficients.length+1);
				exponents=Arrays.copyOf(exponents, exponents.length+1);
				coefficients[coefficients.length-1]=poly.coeff[i];
				exponents[exponents.length-1]=poly.exp[i];
			}
		}
		
		// deleting 0 coefficients
		double[] tempCoeff=Arrays.copyOf(coefficients, coefficients.length);
		int[] tempExp=Arrays.copyOf(exponents, exponents.length);

		int length=0;
		for (int i=0; i<tempCoeff.length; i++) {
			if (tempCoeff[i]!=0) {
				length++;
			}
		}
		coefficients=new double[length];
		exponents=new int[length];
		for (int i=0, j=0; i<tempCoeff.length; i++) {
			if (tempCoeff[i]!=0) {
				coefficients[j]=tempCoeff[i];
				exponents[j]=tempExp[i];
				j++;
			}
		}
		result=new Polynomial(coefficients, exponents);
		return result;
	}

	public Polynomial multiply(Polynomial poly) {
		// returns result of multiping calling obj + arg
			// no redundant exp/0 coeff's
		
		double[] coefficients=new double[1];
		int[] exponents=new int[1];
		Polynomial result=new Polynomial(coefficients, exponents);

		// multiply, reinitialize Polynomial, add()
		for (int i=0; i<exp.length; i++) {
			for (int j=0; j<poly.exp.length; j++) {
				double[] addCoeff={coeff[i]*poly.coeff[j]};
				int[] addExp={exp[i]+poly.exp[j]};

				Polynomial add=new Polynomial(addCoeff, addExp);
				result=result.add(add);
			}
		}
		return result;
	}
	
	public double evaluate(double x) {
		// sub in variable for double x, evaluate
		double result=0.0;

		for (int i=0; i<coeff.length; i++) {
			result=result+(Math.pow(x, exp[i]))*coeff[i];
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		// determines whether or not x is a root of the polynomial
			// root -- value of x for which polynomial=0
		if (evaluate(x)==0) { 
			return true;
		}
		return false;
	}
}