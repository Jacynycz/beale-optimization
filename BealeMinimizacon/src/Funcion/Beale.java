package Funcion;

// @author : Viktor Jacynycz García
// https://github.com/Re1del/beale-optimization/

import javax.swing.JTextArea;

public class Beale {

	private static double INTERVALO_INICIO=-5;
	private static double INTERVALO_FIN=5;
	private static double VALOR_INICIAL=4;
	private static double VALOR_ALFA=0.33;
	private static double UMBRAL_SOLUCION=0.0001;
	private static JTextArea textArea;
	
	
	private static double alfa,a,d,ultimo_valor;
	private static double[] ultimos_valores;	
	private static double VAL_INICIAL_Y;
	private static double VAL_INICIAL_X;
	
	public static double BealeMulti(double x,double y){
		double x2= Math.pow(x,2);
		double y2= Math.pow(y,2);
		double y4= Math.pow(y,4);
		double y6= Math.pow(y,6);
		double y3= Math.pow(y,3);
		return 14.203125-12.750*x+3.0*x*y+3*x2-2*x2*y-x2*y2+4.50*x*y2+x2*y4+5.250*x*y3-2*x2*y3+x2*y6;
	}
	
	 public static double[][] BealeMulti(double[] x, double[] y) {
         double[][] z = new double[y.length][x.length];
         for (int i = 0; i < x.length; i++)
                 for (int j = 0; j < y.length; j++)
                         z[j][i] = BealeMulti(x[i], y[j]);
         return z;
 }
	
	public static double BealeUni(double x){
		//Versión unidimensional de la funcion de Beale
		return Math.pow((1.5 - x*(1-x)),2) + 
			   Math.pow((2.25 - x*(1-Math.pow(x,2))),2) + 
			   Math.pow((2.625 - x*(1-Math.pow(x,3))),2);
	}	
	
	public static double BealeUniDiff1(double x){
		//1º Derivada de la funcion unidimensional de Beale
		double x2=Math.pow(x,2);
		double x3=Math.pow(x,3);
		return 2*(1.5 - x*(1-x))*(-1+2*x) + 
			   2*(2.25 - x*(1-x2))*(3*x2-1) + 
			   2*(2.625 - x*(1-x3))*(4*x3-1);
	}
	
	public static double BealeUniDiff2(double x){
		//2º Derivada de la funcion unidimensional de Beale
		double x2=Math.pow(x,2);
		double x3=Math.pow(x,3);
		return 2*Math.pow((-1+2*x), 2) + 6.0 -
			   4*x*(1-x)+2*Math.pow((3*x2-1),2)+
			   12*(2.25-x*(-x2+1))*x+
			   2*Math.pow((4*x3-1),2)+
			   24*(2.625-x*(-x3+1))*x2;
	}

	public static void newtonUni(){
		/* 
		 * f(x)=BealeUni(x)
		 * definimos un intervalo (a,d)
		 *
		 */
		a = INTERVALO_INICIO;
		d = INTERVALO_FIN;
		
		/*
		 * elegimos un x0 perteneciente a (a,d)
		 * tal que f(x0)< Min{f(a),f(b)}
		 *
		 */

		textArea.append("----------------------\n f(x) = Math.pow((1.5 - x*(1-y)),2) +\n"+ 
						"Math.pow((2.25 - x*(1-Math.pow(y,2))),2) +\n"+ 
						"Math.pow((2.625 - x*(1-Math.pow(y,3))),2)\n ----------------------\n");
		
		textArea.append("Intervalo (a,d)=("+a+","+d+")\n");
		
		
		
		double x0=VALOR_INICIAL;
		double fdex0 = BealeUni(x0);
		double fdea = BealeUni(a);
		double fded = BealeUni(d);
		textArea.append("Valor inicial x0="+x0+"\n f(x0)="+fdex0+"\n");
		textArea.append("f(a)="+fdea+"\n f(d)="+fded+"\n");
		
		if(!(fdex0<fdea && fdex0<fded)){
			textArea.append("Es necesario que f(x0)< Min{f(a),f(d)}\n");
			return;
		}
		else textArea.append("Ejecutando algotirmo: \n ----------------------\n");
		
		/*
		 * elegimos un alfa entre 0 y 1
		 * 
		 */
		alfa = VALOR_ALFA;
		
		ultimo_valor=Float.MAX_VALUE;
		//Comienza el algoritmo
		newtonRec(x0);
		
		return;
	}

	private static void newtonRec(double xk) {
		double fdex=BealeUni(xk);
		textArea.append("f("+xk+")="+fdex+"\n");
		double relacioniteracion=Math.abs(xk-ultimo_valor);
		if(relacioniteracion<UMBRAL_SOLUCION  || Math.abs(fdex)<UMBRAL_SOLUCION){
			textArea.append("La iteración de la solución no supera el umbral de "+UMBRAL_SOLUCION+"\n");
			return;
		}
		else
			ultimo_valor=xk;
		if(BealeUniDiff2(xk)>0)
			newtonCaso1(xk);
		else
			newtonCaso3(xk);
		
	}

	private static void newtonCaso3(double xk) {
		System.out.println("caso3");
		
	}

	private static void newtonCaso1(double xk) {
		double xkd0 = BealeUni(xk);
		double xkd1 = BealeUniDiff1(xk);
		double xkd2 = BealeUniDiff2(xk);
		double yk = xk -(xkd1/xkd2);
		double ykd0 = BealeUni(yk);
		double evaluaAlfa=-0.5*Math.pow(xkd1, 2)/(2*xkd2);
		
		if(!pertenece(yk) || (ykd0-xkd0>evaluaAlfa))
			newtonCaso2(xk);
		else{
			xk=yk;
			newtonRec(xk);
		}
		
	}

	private static boolean pertenece(double n) {
		// Método para comprobar que n pertenece a (a,d)
		if(n>a && n<d) return true;
		else return false;
	}

	private static double ysubk(double xk,int i){
		return xk-Math.pow(2, -i)*BealeUniDiff1(xk);
	}
	
	private static void newtonCaso2(double xk) {
		int i=0;
		double yki= ysubk(xk, i);
		double evaluaAlfa= -alfa*Math.pow(2, -1)*Math.pow(BealeUniDiff1(xk), 2);
		while(!pertenece(yki)&&
				BealeUni(yki)-BealeUni(xk)<= evaluaAlfa){
			i++;
			yki= ysubk(xk, i);
			evaluaAlfa= -alfa*Math.pow(2, -1)*Math.pow(BealeUniDiff1(xk), 2);
		}
		xk=yki;
		newtonRec(xk);
		
	}

	public static void hallarMinimoUnidimensional(JTextArea textArea,double intInicio,double intFinal,double valInicial, double alfa, double threshold) {
		INTERVALO_INICIO=intInicio;
		INTERVALO_FIN=intFinal;
		VALOR_INICIAL=valInicial;
		VALOR_ALFA=alfa;
		UMBRAL_SOLUCION=threshold;
		Beale.textArea=textArea;
		newtonUni();
		
	}
	
	public static void hallarMinimoMultidimensional(JTextArea textArea,double valInicialX,double valInicialY, double threshold) {
		VAL_INICIAL_X=valInicialX;
		VAL_INICIAL_Y=valInicialY;
		UMBRAL_SOLUCION=threshold;
		Beale.textArea=textArea;
		newtonMulti();
		
	}
	private static double[][] hessianaInversa(double x,double y){
		double[][] sol = new double[2][2];
		double x2= Math.pow(x,2);
		double y2= Math.pow(y,2);
		double y3= Math.pow(y,3);
		double y4= Math.pow(y,4);
		double y5= Math.pow(y,5);
		double y6= Math.pow(y,6);
		double y7= Math.pow(y,7);
		double y8= Math.pow(y,8);
		double y10= Math.pow(y,10);
		
		sol[0][0]=-(-2.*x+9.+12.*x*y2+31.50000000*y-12.*x*y+30.*x*y4)*x/(-78.*x+175.5000000*y2+283.5000000*y3+54.*y+315.*x*y7-144.*x2*y7+84.*x2*y10+108.*x2*y8+198.*x*y6-96.*x2*y5+261.*x*y5-126.*x*y4+248.0625000*y4+9.+28.*x2+96.*x2*y-12.*x2*y2-120.*x2*y4+48.*x2*y3+8.*x2*y6-249.*x*y-126.*x*y2-195.*x*y3);
		sol[0][1]=(3.-4.*x-4.*x*y+9.*y+8.*x*y3+15.75000000*y2-12.*x*y2+12.*x*y5)/(-78.*x+175.5000000*y2+283.5000000*y3+54.*y+315.*x*y7-144.*x2*y7+84.*x2*y10+108.*x2*y8+198.*x*y6-96.*x2*y5+261.*x*y5-126.*x*y4+248.0625000*y4+9.+28.*x2+96.*x2*y-12.*x2*y2-120.*x2*y4+48.*x2*y3+8.*x2*y6-249.*x*y-126.*x*y2-195.*x*y3);
		sol[1][0]=(3.-4.*x-4.*x*y+9.*y+8.*x*y3+15.75000000*y2-12.*x*y2+12.*x*y5)/(-78.*x+175.5000000*y2+283.5000000*y3+54.*y+315.*x*y7-144.*x2*y7+84.*x2*y10+108.*x2*y8+198.*x*y6-96.*x2*y5+261.*x*y5-126.*x*y4+248.0625000*y4+9.+28.*x2+96.*x2*y-12.*x2*y2-120.*x2*y4+48.*x2*y3+8.*x2*y6-249.*x*y-126.*x*y2-195.*x*y3);
		sol[1][1]=-(2.*(3.-2.*y-1.*y2+y4-2.*y3+y6))/(-78.*x+175.5000000*y2+283.5000000*y3+54.*y+315.*x*y7-144.*x2*y7+84.*x2*y10+108.*x2*y8+198.*x*y6-96.*x2*y5+261.*x*y5-126.*x*y4+248.0625000*y4+9.+28.*x2+96.*x2*y-12.*x2*y2-120.*x2*y4+48.*x2*y3+8.*x2*y6-249.*x*y-126.*x*y2-195.*x*y3);
		return sol;
	}
	
	
	private static double[] gradiente(double x,double y){
		double[] sol = new double[2];
		double x2= Math.pow(x,2);
		double y2= Math.pow(y,2);
		double y3= Math.pow(y,3);
		double y5= Math.pow(y,5);
		double y4= Math.pow(y,4);
		double y6= Math.pow(y,6);
		sol[0]=-12.750+3.0*y+6.*x-4.*x*y-2*x*y2+4.50*y2+2*x*y4+5.250*y3-4.*x*y3+2*x*y6;
		sol[1]=3.0*x-2*x2-2.*x2*y+9.00*x*y+4*x2*y3+15.750*x*y2-6.*x2*y2+6.*x2*y5;
		return sol;
	}
	
	
	public static void newtonMulti(){
		ultimos_valores = new double[2];
		ultimos_valores[0]=Float.MAX_VALUE;
		ultimos_valores[1]=Float.MAX_VALUE;
		textArea.append("Ejecutando algoritmo... \n Valores iniciales: [X="+VAL_INICIAL_X+",Y="+VAL_INICIAL_Y+"] \n");
		alfa=0.5;
		newtonMultiRec(VAL_INICIAL_X, VAL_INICIAL_Y);
		
	}
	
	public static void newtonMultiRec(double x, double y){
		double fdex=BealeMulti(x, y);
		textArea.append("f("+x+","+y+")="+fdex+"\n");
		double[] relacioniteracion= new double[2];
		
		//si |Xk-Xk-1|<e o f(xk)<e con e=umbral de solución, el algoritmo se para 
		relacioniteracion[0]=Math.abs(ultimos_valores[0]-x);
		relacioniteracion[1]=Math.abs(ultimos_valores[1]-y);
		if((relacioniteracion[1]<UMBRAL_SOLUCION && relacioniteracion[0]<UMBRAL_SOLUCION) || Math.abs(fdex)<UMBRAL_SOLUCION){
			textArea.append("La iteración de la solución no supera el umbral de "+UMBRAL_SOLUCION+"\n");
			return;
		}
		else{
			ultimos_valores[0]=x;
			ultimos_valores[1]=y;
		}
		newtonMultiCaso1(x,y);		
	}

	private static void newtonMultiCaso1(double x, double y) {
		//Se calcula la Matriz Hessiana Iversa de los valores X e Y
		double[][] hessiana = hessianaInversa(x, y);
		
		//Se calcula el Gradiente evaluado en X e Y
		double[] gradiente = gradiente(x, y);
		
		//Cada uno de los valores es igual a la multiplicación correspondiente
		//a la matriz Hessiana por el Gradiente
		double newx = x -((gradiente[0]*hessiana[0][0])+(gradiente[1]*hessiana[1][0]));
		double newy = y -((gradiente[0]*hessiana[0][1])+(gradiente[1]*hessiana[1][1]));
		
		//Se regresa al metodo para calcular si el algoritmo se detiene
			newtonMultiRec(newx, newy);
	}
	
	
}
