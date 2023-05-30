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
		instrucciones_maquinaP = new ArrayList<Instruccion>();
		
		this.maquina = new MaquinaP(5,10,10,2);
		this.pila = new RecolectaProcs();
		
		genera(this.programa);
		
		System.out.println("GeneraCodigo OK !!!!!!");
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
		for(Instruccion_programa i : is.getElements()) {
			switch(i.getTipo()) {
			case ASIG:
				gen_cod(i.getE0());
				gen_cod(i.getE1());
				instrucciones_maquinaP.add(new IDesapilaind(this.maquina));
				break;
			case WRITE:
				//WTF man un cout y tirando
				System.out.println(i.getE0().getValor().toString());
				break;
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
				instrucciones_maquinaP.add(new IAlloc(this.maquina, tamTipo(i.getE0().getTipoSol())));
				break;
			case DEL_MEM:
				instrucciones_maquinaP.add(new IDealloc(this.maquina, tamTipo(i.getE0().getTipoSol())));
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
				break;
			case WHILE:
				gen_cod(i.getE0());
				instrucciones_maquinaP.add(new IIrF(this.maquina, i.getDir_sig()));
				gen_cod(i.getIs0());
				instrucciones_maquinaP.add(new IIrA(this.maquina, i.getDir_ini()));
				break;
			case CALL:
				gen_cod(i.getE0());
				instrucciones_maquinaP.add(new IActiva(this.maquina,i.getDir_ini(),tamTipo(i.getE0().getTipoSol()), i.getDir_sig()));
			}
				
		}
	}
	
	public int tamTipo(Tipo t) {
		if (t == Tipo.BOOL || t == Tipo.INT || t == Tipo.PUNTERO) {
			return 1;
		}else if(t == Tipo.REAL) {
			return 2;
		}
		return 1;
	}
	
	public void gen_cod(Exp e) {
		
		ExpCompuesta ec;
		ExpDRef edr;
		ExpBasica eb;
		int valor;
		double valor1;
		Boolean valor_bool;
		Tipo t;
		
		switch(e.tipo()) {
		case NUM:
			eb = (ExpBasica) e;
			valor = Integer.parseInt(eb.getValor().toString());
			instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
			break;
		case ID:
			instrucciones_maquinaP.add(new IApilaInt(this.maquina, e.getEtiqueta()));

			break;
		case DREF:
			edr = (ExpDRef)e;
			gen_cod(edr.getReferencia());
			instrucciones_maquinaP.add(new IApilaind(this.maquina));
			break;
		case SUMA:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new ISuma(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new ISumaReal(this.maquina));
			}
			break;
		case RESTA:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IResta(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new ISumaReal(this.maquina));
			}
			break;
			
		case MUL:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IMul(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new IMulReal(this.maquina));
			}
			break;
			
		case DIV:
			ec = (ExpCompuesta) e;
			if(ec.getTipoSol() == Tipo.INT) {
				
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IDiv(this.maquina));
			}else if (ec.getTipoSol() == Tipo.REAL) {
				
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new IDivReal(this.maquina));
			}
			break;
			
		case AND:
			ec = (ExpCompuesta) e;
			
			valor_bool = Boolean.parseBoolean(ec.getE0().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			valor_bool = Boolean.parseBoolean(ec.getE1().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			instrucciones_maquinaP.add(new IAnd(this.maquina));
			break;
			
		case OR:
			ec = (ExpCompuesta) e;
			
			valor_bool = Boolean.parseBoolean(ec.getE0().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			valor_bool = Boolean.parseBoolean(ec.getE1().getValor().toString());
			instrucciones_maquinaP.add(new IApilaBool(this.maquina, valor_bool));
			
			instrucciones_maquinaP.add(new IOr(this.maquina));
			break;
			
		case PORC:
			ec = (ExpCompuesta) e;
			
			if(ec.getTipoSol() == Tipo.INT){
				valor = Integer.parseInt(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				valor = Integer.parseInt(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
				
				instrucciones_maquinaP.add(new IPorc(this.maquina));
			
			}else {
				valor1 = Double.parseDouble(ec.getE0().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				valor1 = Double.parseDouble(ec.getE1().getValor().toString());
				instrucciones_maquinaP.add(new IApilaReal(this.maquina, valor1));
				
				instrucciones_maquinaP.add(new IPorc(this.maquina));
			}
			break;
			
		case INDEX:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			
			valor = Integer.parseInt(ec.getE0().getValor().toString());
			instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
			valor = Integer.parseInt(ec.getE1().getValor().toString());
			instrucciones_maquinaP.add(new IApilaInt(this.maquina, valor));
			break;
			
		case GREATER:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			gen_cod(ec.getE1());
			
			t = ec.getTipoSol();
			instrucciones_maquinaP.add(new IComparacion(this.maquina, t, TipoExpresion.GREATER));
			break;
		case GREATER_EQUAL:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			gen_cod(ec.getE1());
			
			 t = ec.getTipoSol();
			instrucciones_maquinaP.add(new IComparacion(this.maquina, t, TipoExpresion.GREATER_EQUAL));
			break;
			
		case LESS:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			gen_cod(ec.getE1());
			
			 t = ec.getTipoSol();
			instrucciones_maquinaP.add(new IComparacion(this.maquina, t, TipoExpresion.LESS));
			break;
		case LESS_EQUAL:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			gen_cod(ec.getE1());
			
			 t = ec.getTipoSol();
			instrucciones_maquinaP.add(new IComparacion(this.maquina, t, TipoExpresion.LESS_EQUAL));
			break;

		case NOT:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			
			 t = ec.getTipoSol();
			instrucciones_maquinaP.add(new IComparacion(this.maquina, t, TipoExpresion.NOT));
			break;
			
		case NOT2:
			ec = (ExpCompuesta) e;
			gen_cod(ec.getE0());
			 t = ec.getTipoSol();
			instrucciones_maquinaP.add(new IComparacion(this.maquina, t, TipoExpresion.NOT2));
			break;
			
		}
		
		
	}
	
	public void gen_cod(Dec dec) {
		
		switch(dec.tipo()) {
		case DEC_PROC:
			
		}
	}
	
	
	//------------------------------------- MAIN ----------------------------------------
	
	public static void main(String args[]) {
		// Programa A
		// int a
		// int b
		// a = 5
		// b = 2

		// Programa B
		// int a
		// a = 5 + 2
		
		//Programa C
		// int a
		// int b
		// a = 5
		// b = 2
		// a = a + b

		TinyASint tiny = new TinyASint();

		StringLocalizado int_a = new StringLocalizado("a", 0, 0);
		StringLocalizado int_b = new StringLocalizado("b", 0, 0);
		StringLocalizado valor_a = new StringLocalizado("5", 1, 1);
		StringLocalizado valor_b = new StringLocalizado("2", 1, 1);

		Dec decA = tiny.dec_var(int_a, valor_a, TipoDeclaracion.DEC_VAR);
		Dec decB = tiny.dec_var(int_b, valor_b, TipoDeclaracion.DEC_VAR);
		
		Instruccion_programa asig_a = tiny.asig(new ExpBasica(int_a, null, Tipo.INT, TipoExpresion.ID),
				new ExpBasica(null, valor_a, Tipo.INT, TipoExpresion.NUM));
		
		Instruccion_programa asig_a2 = tiny.asig(new ExpBasica(int_b, null, Tipo.INT, TipoExpresion.ID),
				new ExpBasica(null, valor_b, Tipo.INT, TipoExpresion.NUM));

		ExpCompuesta suma_b = new ExpCompuesta(new ExpBasica(null, valor_a, Tipo.INT, TipoExpresion.NUM),
				new ExpBasica(null, valor_b, Tipo.INT, TipoExpresion.NUM), TipoExpresion.SUMA);
		Instruccion_programa asig_b = tiny.asig(new ExpBasica(int_a, null, Tipo.INT, TipoExpresion.ID), suma_b);

		Instrucciones isA = new Instrucciones();
		Decs decsA = new Decs();

		Instrucciones isB = new Instrucciones();
		Decs decsB = new Decs();

		isA = isA.add(asig_a);
		isA = isA.add(asig_a2);
		decsA = decsA.add(decA);
		decsA = decsA.add(decB);
		Programa pA = new Programa(decsA, isA);

		isB = isB.add(asig_b);
		decsB = decsB.add(decA);
		Programa pB = new Programa(decsB, isB);
	
		ComprobacionTipo ct = new ComprobacionTipo(pB);
		Vinculacion v = new Vinculacion(pB);
		AsignacionEspacios asigna = new AsignacionEspacios(pB);
		Etiquetado etiqueta = new Etiquetado(pB);
		GeneraCodigo genCod = new GeneraCodigo(pB);

		
		
	}
	
	
}
