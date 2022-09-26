
public class Polynomial {
	private double[] coefficients;

	public Polynomial() {
		coefficients = new double[] {0};
	}

	public Polynomial(double [] coef) {
		coefficients = new double[coef.length];
		for ( int i = 0; i < coef.length; i++ ) {
			coefficients[i] = coef[i];
		}
	}

	public Polynomial add(Polynomial other) {
		Polynomial poly = new Polynomial();
		if(coefficients.length<=other.coefficients.length) {
			poly = new Polynomial(other.coefficients);
		}
		
		else if(coefficients.length>other.coefficients.length) {
			poly = new Polynomial(coefficients);
		}
		

		if(coefficients.length<=other.coefficients.length)
		for(int i=0; i<coefficients.length; i++) {
			poly.coefficients[i] += coefficients[i];	
			}
		if(coefficients.length>other.coefficients.length)
			for(int i=0; i<other.coefficients.length; i++) {
				poly.coefficients[i] += other.coefficients[i];	
				}
		return poly;
			
		}

	public double evaluate(double x) {
		int degree = 0;
		double ans = 0;
		for(int j=0; j<coefficients.length; j++)
		{
			ans += coefficients[j] * Math.pow(x,degree);
			degree ++;
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


