package es.uam.eps.multij;

import java.util.ArrayList;

public class Tablero3Raya extends Tablero {
	int[] casillas;
	
	public Tablero3Raya() {
		super();
		for(int i=0;i<9;i++){
			this.casillas[i]=0;
		}
	}

	@Override
	protected void mueve(Movimiento m) throws ExcepcionJuego {
		if(((Movimiento3Raya) m).getCasilla()<0||((Movimiento3Raya) m).getCasilla()>8){
			throw new ExcepcionJuego("El movimiento debe hacerse dentro del tablero");
		}
		if(this.casillas[((Movimiento3Raya) m).getCasilla()]!=0){
			throw new ExcepcionJuego("El movimiento debe hacerse en una casilla libre");
		}
		this.casillas[((Movimiento3Raya) m).getCasilla()]=this.getTurno();
		
	}

	@Override
	public boolean esValido(Movimiento m) {
		if (this.casillas[((Movimiento3Raya) m).getCasilla()]==0)return true;
		return false;
	}

	@Override
	public ArrayList<Movimiento> movimientosValidos() {
		ArrayList<Movimiento> ret=new ArrayList<Movimiento>();
		for(int i=0;i<9;i++){
			if(this.casillas[i]==0){
				ret.add(new Movimiento3Raya(i));
			}
		}
		return ret;
	}

	@Override
	public String tableroToString() {
		String ret =new String();
		for(int i=0;i<9;i++){
			ret+=this.casillas[i];
		}
		return ret;
	}

	@Override
	public void stringToTablero(String cadena) throws ExcepcionJuego {
		if(cadena.length()!=9) throw new ExcepcionJuego("String no válido para un Tablero3Raya");
		for(int i=0;i<9;i++){
			if(Character.getNumericValue(cadena.charAt(i))<0||Character.getNumericValue(cadena.charAt(i))>2) throw new ExcepcionJuego("String no válido para un Tablero3Raya");
			this.casillas[i]=Character.getNumericValue(cadena.charAt(i));
		}

	}

	@Override
	public String toString() {
		String ret= new String();
		String aux = new String();
		for (int i =0; i<9;i++){
			if(this.casillas[i]==0)aux+=i;
			if(this.casillas[i]==1)aux+="x";
			if(this.casillas[i]==2)aux+="o";
		}
		ret+="-------";
		ret=ret+"|"+aux.charAt(0)+"|"+aux.charAt(1)+"|"+aux.charAt(2)+"|";
		ret+="-------";
		
		ret=ret+"|"+aux.charAt(3)+"|"+aux.charAt(4)+"|"+aux.charAt(5)+"|";
		ret+="-------";
		
		ret=ret+"|"+aux.charAt(6)+"|"+aux.charAt(7)+"|"+aux.charAt(8)+"|";
		ret+="-------";
		return ret;
	}
	public boolean reset(){
		this.numJugadas = 0;	
		for(int i=0;i<9;i++){
			this.casillas[i]=0;
		}
		return true;
		
	}
}
