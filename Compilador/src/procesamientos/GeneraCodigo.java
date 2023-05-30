package procesamientos;

import tiny.Programa;
import asint.*;
import maquinaP.*;
import maquinaP.MaquinaP.Instruccion;

import java.util.*;

public class GeneraCodigo {
	
	private RecolectaProcs pila;
	private Programa programa;
	private ArrayList<Instruccion> instrucciones_maquinaP;
	private MaquinaP maquina;
	
	public GeneraCodigo(Programa p) {
		this.programa = p;
		this.maquina = new MaquinaP(5,10,10,2);
		this.pila = new RecolectaProcs();
		genera(this.programa);
		instrucciones_maquinaP = new ArrayList<Instruccion>();
	}
	
	public void genera(Programa p) {
		gen_cod(p.getIs());
		pila.recolecta_procs(p.getDecs());
		while(!pila.getPila().empty()) {
			gen_cod(pila.getPila().pop());
		}
	}
	
	public void gen_cod(Instrucciones is) {
		int valor;
		//TODO call
		//TODO PREAL
		for(Instruccion_programa i : is.getElements()) {
			switch(i.getTipo()) {
			case INT:
				valor = Integer.parseInt(i.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				break;
			case ID:
				if(i.getNivel() == 0) {
					valor = i.getDir_ini();
					instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				}else {
					instrucciones_maquinaP.add(new IApilad(this.maquina, i.getNivel()));
					valor = i.getDir_ini();
					instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
					instrucciones_maquinaP.add(new ISuma(maquina));
					if(i.getPforms().getTipo() == TipoParametro.P_VAR) {
						instrucciones_maquinaP.add(new IApilaind(this.maquina));
					}
				}
				break;
			case WRITE:
				//WTF man un cout y tirando
				System.out.println(i.getE0().getValor().toString());
			case READ:
				StringLocalizado input = new StringLocalizado("",-1,-1);
				StringLocalizado id = i.getE0().ID();
				Dec dec = new Dec(id, input, TipoDeclaracion.DEC_VAR);
				//asigna_espacio(dec);
				//generar instruccion scanner
				break;
			case NL:
				//Imprimir en patalla new line
				System.out.println("NEW LINE");
				break;
			case RES_MEM:
				instrucciones_maquinaP.add(new IAlloc(this.maquina, i.getDir_sig()-i.getDir_ini()));
				break;
			case DEL_MEM:
				instrucciones_maquinaP.add(new IDealloc(this.maquina, i.getDir_sig()-i.getDir_ini()));
			case IF_THEN:
				gen_cod(i.getE0());
				instrucciones_maquinaP.add(new IIrF(this.maquina, i.getDir_sig()));
				gen_cod(i.getIs0());
				break;
			case IF_THEN_ELSE:
				gen_cod(i.getE0());
				instrucciones_maquinaP.add(new IIrF(this.maquina, i.getDir_sig()));
				gen_cod(i.getIs0());
				instrucciones_maquinaP.add(new IIrA(this.maquina, i.getDir_sig()));
				gen_cod(i.getIs1());
			case WHILE:
				gen_cod(i.getE0());
				instrucciones_maquinaP.add(new IIrF(this.maquina, i.getDir_sig()));
				gen_cod(i.getIs0());
				instrucciones_maquinaP.add(new IIrA(this.maquina, i.getDir_ini()));
			}
			
		}
	}
	
	public void gen_cod(Exp e) {
		
		ExpCompuesta ec;
		ExpDRef edr;
		int valor;
		double valor1;
		Boolean valor_bool;
		
		switch(e.tipo()) {
		case DREF:
			edr = (ExpDRef)e;
			gen_cod(edr.getReferencia());
			instrucciones_maquinaP.add(new IApilaind(this.maquina));
			break;
		case SUMA:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				gen_cod(ec.getE0());
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				gen_cod(ec.getE1());
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new ISuma(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				gen_cod(ec.getE0());
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				gen_cod(ec.getE1());
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new ISumaReal(this.maquina));
			}
			break;
		case RESTA:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				gen_cod(ec.getE0());
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				gen_cod(ec.getE1());
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IResta(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				gen_cod(ec.getE0());
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				gen_cod(ec.getE1());
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new ISumaReal(this.maquina));
			}
			break;
			
		case MUL:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				gen_cod(ec.getE0());
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				gen_cod(ec.getE1());
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IMul(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				gen_cod(ec.getE0());
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				gen_cod(ec.getE1());
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new IMulReal(this.maquina));
			}
			break;
			
		case DIV:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				gen_cod(ec.getE0());
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				gen_cod(ec.getE1());
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IDiv(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				gen_cod(ec.getE0());
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				gen_cod(ec.getE1());
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new IDivReal(this.maquina));
			}
			break;
			
		case AND:
			ec = (ExpCompuesta) e;
			
			gen_cod(ec.getE0());	
			valor_bool = Boolean.parseBoolean(ec.getE0().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			gen_cod(ec.getE1());
			valor_bool = Boolean.parseBoolean(ec.getE1().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			instrucciones_maquinaP.add(new IAnd(this.maquina));
			break;
			
		case OR:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			
			valor_bool = Boolean.parseBoolean(ec.getE0().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			valor_bool = Boolean.parseBoolean(ec.getE1().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			instrucciones_maquinaP.add(new IOr(this.maquina));
			break;
			
		case PORC:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			gen_cod(ec.getE1());
			
			if(ec.getTipoSol() == Tipo.INT){
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(null);
			}else {
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
			}
			
			
		}
		
		
	}
	
	public void gen_cod(Dec dec) {
		
		switch(dec.tipo()) {
		case DEC_PROC:
			
		}
	}
	
}
