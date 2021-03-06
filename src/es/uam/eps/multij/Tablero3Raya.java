

import java.util.ArrayList;

import es.uam.eps.multij.ExcepcionJuego;
import es.uam.eps.multij.Movimiento;
import es.uam.eps.multij.Tablero;

public class Tablero3Raya extends Tablero {
	int[] casillas ;
	
	public Tablero3Raya() {
		super();
		this.numJugadas=0;
		this.numJugadores=2;
		this.estado=EN_CURSO;
		this.casillas=new int[9];
		for(int i=0;i<9;i++){
			this.casillas[i]=0;
		}
	}

	@Override
	public void mueve(Movimiento m) throws ExcepcionJuego {
		if(((Movimiento3Raya) m).getCasilla()<0||((Movimiento3Raya) m).getCasilla()>8){
			throw new ExcepcionJuego("El movimiento debe hacerse dentro del tablero");
		}
		if(this.casillas[((Movimiento3Raya) m).getCasilla()]!=0){
			throw new ExcepcionJuego("El movimiento debe hacerse en una casilla libre");
		}
		this.casillas[((Movimiento3Raya) m).getCasilla()]=this.getTurno()+1;
		this.numJugadas++;
		this.cambiaTurno();
		if(this.getTurno()==0)
		System.out.println(this.toString());
		if(this.comprobar_ganador(1)){
			System.out.println("Ha ganado el Jugador 1, queda el tablero:");
			System.out.println(this.toString());
			this.estado=FINALIZADA;
		}
		if(this.comprobar_ganador(2)){
			System.out.println("Ha ganado el Jugador 2, queda el tablero:");
			System.out.println(this.toString());
			this.estado=FINALIZADA;
		}
		if(this.numJugadas==9){
			System.out.println("Ha habido un empate, queda el tablero:");
			System.out.println(this.toString());
			this.estado=TABLAS;
		}
	}
	public boolean comprobar_ganador(int ganador){
	    
	    if((this.casillas[0]==ganador) && (this.casillas[1]==ganador) && (this.casillas[2]==ganador)){
	        return true;
	    }
	    if((this.casillas[3]==ganador) && (this.casillas[4]==ganador) && (this.casillas[5]==ganador)){
	        return true;
	    }
	    if((this.casillas[6]==ganador) && (this.casillas[7]==ganador) && (this.casillas[8]==ganador)){
	        return true;
	    }
	    if((this.casillas[0]==ganador) && (this.casillas[3]==ganador) && (this.casillas[6]==ganador)){
	        return true;
	    }
	    if((this.casillas[1]==ganador) && (this.casillas[4]==ganador) && (this.casillas[7]==ganador)){
	        return true;
	    }
	    if((this.casillas[2]==ganador) && (this.casillas[5]==ganador) && (this.casillas[8]==ganador)){
	        return true;
	    }
	    if((this.casillas[0]==ganador) && (this.casillas[4]==ganador) && (this.casillas[8]==ganador)){
	        return true;
	    }
	    if((this.casillas[2]==ganador) && (this.casillas[4]==ganador) && (this.casillas[6]==ganador)){
	        return true;
	    }
	    return false;
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
		ret+="-------\n";
		ret=ret+"|"+aux.charAt(0)+"|"+aux.charAt(1)+"|"+aux.charAt(2)+"|\n";
		ret+="-------\n";
		
		ret=ret+"|"+aux.charAt(3)+"|"+aux.charAt(4)+"|"+aux.charAt(5)+"|\n";
		ret+="-------\n";
		
		ret=ret+"|"+aux.charAt(6)+"|"+aux.charAt(7)+"|"+aux.charAt(8)+"|\n";
		ret+="-------\n";
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
