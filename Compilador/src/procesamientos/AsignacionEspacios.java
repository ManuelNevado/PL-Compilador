package procesamientos;
import asint.*;
import tiny.*;

public class AsignacionEspacios {
	private int dir;
	private int nivel;
	private Programa programa;
	
	public AsignacionEspacios() {
		this.dir = 0;
		this.nivel=0;
		
		Asigna(programa.getDecs());
		Asigna(programa.getIs());
	}
	
	
	public void Asigna(Decs decs) {
		for(Dec dec : decs.getElements()) {
			switch(dec.tipo()) {
				case DEC_TYPE:
					Asigna(dec.getTipoVinculacion(), dec);
				case DEC_PROC:
					int ant_dir = dir;
					nivel++;
					this.dir = 0;
					Asigna(dec.getPforms());
					Asigna(dec.getDecs());
					dec.setTam_datos(dir);
					this.dir = ant_dir;
					nivel--;
			}
		}
	}
	
	public void Asigna(Instrucciones Is) {
		for(Instruccion_programa i: Is.getElements()) {
			switch(i.getTipo()) {
			case IF_THEN:
				Asigna(i.getIs0());
			case IF_THEN_ELSE:
				Asigna(i.getIs0());
				Asigna(i.getIs1());
			case WHILE:
				Asigna(i.getIs0());
			case CALL:
				Asigna(i.getPreal());
			case COMP:
				int ant_dir = dir;
				Asigna(i.getDecs());
				Asigna(i.getIs0());
				dir = ant_dir;
			}
		}
	}
	
	public void Asigna(Tipo t, Dec dec) {
		switch(t) {
		case INT:
			dec.setTam_datos(1);
			break;
		case REF:
			dec.setTam_datos(tamTipo(dec.getTipoVinculacion()));
		case BOOL:
			dec.setTam_datos(1);
		case ARRAY:
			dec.setTam_datos(1);
			break;
		case REGISTRO:
			dec.setNivel(nivel);
			dec.setTam_datos(Asigna(dec.getCampos()));
			break;
		case PUNTERO:
			dec.setTam_datos(1);
			break;
		}
	}
	
	public void Asigna(Tipo t, Parametro pform) {
		switch(t) {
		case INT:
			pform.setTam_datos(1);
			break;
		case REF:
			pform.setTam_datos(tamTipo(pform.getTipo()));
		case BOOL:
			pform.setTam_datos(1);
		case ARRAY:
			pform.setTam_datos(1);
			break;
		case REGISTRO:
			Asigna(pform.getCampo());
			break;
		case PUNTERO:
			pform.setTam_datos(1);
			break;
		}
	}
	
	public void Asigna(Parametros pforms) {
		for(Parametro pform : pforms.getElements()) {
			switch(pform.getTparametro()) {
			case P_VAL:
				pform.setDir(dir);
				pform.setNivel(nivel);
				Asigna(pform.getTipo(),pform);
				this.dir += tamTipo(pform.getTipo());
				break;
			case P_VAR:
				pform.setDir(dir);
				pform.setNivel(nivel);
				Asigna(pform.getTipo(),pform);
				this.dir++;
				break;
			}
			
		}
	}
	
	public int Asigna(Campos cs) {
		int espacio_total = 0;
		for(Campo c : cs.getLista()) {
			espacio_total += tamTipo(c.getTipo());
		}
		return espacio_total;
	}
	
	private int tamTipo(Tipo t) {
		if (t == Tipo.BOOL || t == Tipo.INT || t == Tipo.PUNTERO) {
			return 1;
		}else if(t == Tipo.REAL) {
			return 2;
		}
		return 1;
	}
	
}
