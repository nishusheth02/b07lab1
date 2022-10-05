import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial {
	public double[] coefficients;
	public int[] exponents;

	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}

	public Polynomial(double [] coef, int [] exp) {
		coefficients = new double[coef.length];
		exponents = new int[exp.length];
		for ( int i = 0; i < coef.length; i++ ) {
			coefficients[i] = coef[i];
			exponents[i]=exp[i];
		}
	}
	
	public Polynomial(File file) throws FileNotFoundException{
		  
		  
		  
		  Scanner input = new Scanner(file);
		  String line = input.nextLine();
		  
		  
		  int terms = 1;
		  
		  if(line.charAt(0)=='-') {
			  for (int i = 0; i<line.length(); i++) {
				  if (line.charAt(i) == '+' || line.charAt(i) == '-') {
					  terms++;
				  }
				  
		  }
			  terms=terms-1;
		  }
		  else {
			  for (int i = 0; i<line.length(); i++) {
				  if (line.charAt(i) == '+' || line.charAt(i) == '-') {
					  terms++;
				  }
		  }
		  }
		  
		  int id = 0;
		  int [] expo = new int[terms];
		  double [] coeff = new double[terms];
		  int ic =0;
		  int ie =0;
		  String coef = "";
		  String exp = "";
		  
		  while(id<line.length()){
			  coef = "";
			  exp = "";
			  
			  if(line.charAt(id)=='+') {
				  id++;
			  }
			  
			  else if(line.charAt(id)=='-' ) {
				  coef += line.charAt(id);
				  id++;
			  }
			  
			  while(id<line.length() && line.charAt(id)!='+' && line.charAt(id)!='-' && line.charAt(id)!='x') {
				  coef += line.charAt(id);
				  id++;
			  }
			  
			  
			  if(coef!="") {
				  coeff[ic] = Double.parseDouble(coef);
				  ic++;
			  }
			
			  
			  if(id==line.length()) {
				  expo[ie]=0;
				  id++;
			  }
			  
			  
			  else if(id<line.length() && line.charAt(id)=='+' || line.charAt(id)=='-') {
				  expo[ie]=0;
				  
				  ie++;
				  
			  }
			  
			  
			  
			  else {
				  id++;
				  while(id<line.length() && line.charAt(id)!='+' && line.charAt(id)!='-') {
					  exp = exp + line.charAt(id);
					  id++;
				  }
			  }
			  
			  
			  if(exp!="") {
				  int k = Integer.parseInt(exp);
			      expo[ie] = k; 
			      ie++;
			  }
			  
			  
			  
			  
			  
			  
			 
			  
		      
		  }
		  
		coefficients = new double[coeff.length];
		exponents = new int[expo.length];
		for ( int i = 0; i < coeff.length; i++ ) {
			coefficients[i] = coeff[i];
			exponents[i]= expo[i];
			}
		  input.close();
		  }
		  
		  
		  
			  



	public Polynomial add(Polynomial other) {
		int newarrsize = size(coefficients, other.coefficients);
		
		double[]coef = new double [newarrsize];
		int []exp = new int [newarrsize];
		int id = 0;
		int counter = 0;
		
		for(int i=0; i<coefficients.length; i++) {
			for(int j=0; j<other.coefficients.length; j++) {
				if(exponents[i]==other.exponents[j])
				{
					coef[id]=coefficients[i]+other.coefficients[j];
					exp[id]=exponents[i];
					id++;
				}
			}
		}
		
		for(int i=0; i<coefficients.length; i++) {
			counter = 0;
			for(int j=0; j<other.coefficients.length; j++) {
				if(exponents[i]==other.exponents[j]) {
					counter = 1;
				}
			
			}
			if(counter!=1) {
				coef[id]=coefficients[i];
				exp[id] = exponents[i];
				id++;
			}
			
		}
		
		
		
		for(int i=0; i<other.coefficients.length; i++) {
			counter = 0;
			for(int j=0; j<coefficients.length; j++) {
				if(other.exponents[i]==exponents[j]) {
					counter = 1;
				}
			
			}
			if(counter!=1) {
				coef[id]=other.coefficients[i];
				exp[id] = other.exponents[i];
				id++;
			}
		}
		
		
				
		Polynomial poly = new Polynomial(coef,exp);
		return poly;

		
	}
	
	int size(double[] p1, double[] p2) {
		int newArrSize = 0;
		
		if(p1.length<=p2.length) {
			newArrSize = p1.length;
		}
		
		if(p1.length>p2.length) {
			newArrSize = p2.length;
		}
		
		for (int i = 0; i<p2.length; i++) {
			for (int j = 0; j<p1.length; j++) {
				if(p1[j]!=p2[i]) {
					newArrSize++;
					}
				}
				
			}
		return newArrSize;
	}
	
	public Polynomial multiply(Polynomial other) {
		int newArrSize = coefficients.length*other.coefficients.length;
		double []newcoef = new double[newArrSize];
		int [] newexp = new int[newArrSize];
		int [] newexp2 = new int[newArrSize];
		int id = 0;
		
		for(int i=0; i<coefficients.length; i++){
			for(int j=0; j<other.coefficients.length; j++) {
				newexp[id]=exponents[i]+other.exponents[j];
				newexp2[id]=exponents[i]+other.exponents[j];
				newcoef[id]=coefficients[i]*other.coefficients[j];
				id++;
			}
		}
		
		int counter = newArrSize;
		for(int i=0; i<newArrSize; i++) {
			for(int j=0; j<newArrSize; j++) {
				if(newexp[i]==newexp[j] && newexp[i]!=-1 && j!=i) {
					newexp[j]=-1;
					counter--;
				}
			}
			
		}
		
		double []coef = new double[counter];
		int [] exp = new int[counter];
		int []unique = new int[counter];
		int id2=0;
		double coefAdder = 0;
		int id3 = 0;
		
		for(int i=0; i<newArrSize; i++) {
			if(newexp[i]!=-1) {
				unique[id2]= newexp[i];
				id2++;
			}
		}
		
		
			
		for(int i=0; i<counter; i++) {
			coefAdder = 0;
			for(int j=0; j<newArrSize; j++) {
				if(unique[i]==newexp2[j])
				{
					coefAdder += newcoef[j];
				}
			}
			coef[id3] = coefAdder;
			exp[id3] = unique[i];
			id3++;
			
		}
		

		Polynomial poly = new Polynomial(coef,exp);
		return poly;
	}
	
	void saveToFile(String name) throws Exception{
		 
		  String str = "";
		  for(int i=0; i<coefficients.length; i++) {
				  str += String.valueOf(coefficients[i]);
				  if(exponents[i]!=0) {
					  str += 'x';
					  str += exponents[i];
				  }
				  else if(exponents[i]==0 && i+1<coefficients.length && coefficients[i+1]>0 ) {
					  str += '+';
				  }
					  
				  if(i+1<coefficients.length && coefficients[i+1]>0 && exponents[i]!=0) {
					  str += '+';
				  }
			  }
		  
		  PrintStream out = new PrintStream(name);
		  out.println(str);
		  out.close();
		  		  
	  }
	
	
	  



	public double evaluate(double x) {
		double ans = 0;
		for(int j=0; j<coefficients.length; j++)
		{
			ans += coefficients[j] * Math.pow(x,exponents[j]);
		}
		return ans;	
	}
	
	public boolean hasRoot(double x) {
		if(evaluate(x)==0) {
			return true;
		}
		else {
			return false;
		}
				
	}

	}


