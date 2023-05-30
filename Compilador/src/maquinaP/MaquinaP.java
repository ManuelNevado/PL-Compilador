package maquinaP;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;




public class MaquinaP {
   public static class EAccesoIlegitimo extends RuntimeException {} 
   public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
      public EAccesoAMemoriaNoInicializada(int pc,int dir) {
         super("pinst:"+pc+" dir:"+dir); 
      } 
   } 
   public static class EAccesoFueraDeRango extends RuntimeException {} 
   
   GestorMemoriaDinamica gestorMemoriaDinamica;
   GestorPilaActivaciones gestorPilaActivaciones;
    
   class Valor {
      public int valorInt() {throw new EAccesoIlegitimo();}  
      public boolean valorBool() {throw new EAccesoIlegitimo();}
      public double valorReal() {throw new EAccesoIlegitimo();}
   } 
   class ValorReal extends Valor{
	   private double valor;
	   public ValorReal(double valor) {this.valor = valor;}
	   public void setValor(double valor) {this.valor = valor;}
	   @Override
	   public double valorReal() {return this.valor;}
	   @Override
	   public String toString() {return String.valueOf(valor);}
   }
   class ValorInt extends Valor {
      private int valor;
      public ValorInt(int valor) {
         this.valor = valor; 
      }
      public int valorInt() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }
   class ValorBool extends Valor {
      private boolean valor;
      public ValorBool(boolean valor) {
         this.valor = valor; 
      }
      public boolean valorBool() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }

   List<Instruccion> codigoP;
   Stack<Valor> pilaEvaluacion;
   Valor[] datos; 
   int pc;

   public interface Instruccion {
      void ejecuta();  
   }
   private ISuma ISUMA;
   private ISumaReal ISUMAREAL;
   private IMul IMUL;
   private IMulReal IMULREAL;
   private IAnd IAND;
   private IApilaind IAPILAIND;
   private IDesapilaind IDESAPILAIND;
   private IDup IDUP;
   private Instruccion ISTOP;
   private Instruccion IIRIND;
   public Instruccion suma() {return ISUMA;}
   public Instruccion mul() {return IMUL;}
   public Instruccion sumaReal() {return ISUMAREAL;}
   public Instruccion mulReal() {return IMULREAL;}
   public Instruccion and() {return IAND;}
   public Instruccion apilaInt(int val) {return new IApilaInt(this, val);}
   public Instruccion apilaReal(double val) {return new IApilaReal(this, val);}
   public Instruccion apilaBool(boolean val) {return new IApilaBool(this, val);}
   public Instruccion apilad(int nivel) {return new IApilad(this, nivel);}
   public Instruccion apilaInd() {return IAPILAIND;}
   public Instruccion desapilaInd() {return IDESAPILAIND;}
   public Instruccion mueve(int tam) {return new IMueve(this, tam);}
   public Instruccion irA(int dir) {return new IIrA(this, dir);}
   public Instruccion irF(int dir) {return new IIrF(this, dir);}
   public Instruccion irInd() {return IIRIND;}
   public Instruccion alloc(int tam) {return new IAlloc(this, tam);} 
   public Instruccion dealloc(int tam) {return new IDealloc(this, tam);} 
   public Instruccion activa(int nivel,int tam, int dirretorno) {return new IActiva(this, nivel,tam,dirretorno);}
   public Instruccion desactiva(int nivel, int tam) {return new IDesactiva(this, nivel,tam);}
   public Instruccion desapilad(int nivel) {return new IDesapilad(this, nivel);}
   public Instruccion dup() {return IDUP;}
   public Instruccion stop() {return ISTOP;}
   public void ponInstruccion(Instruccion i) {
      codigoP.add(i); 
   }

   private int tamdatos;
   private int tamheap;
   private int ndisplays;
   public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
      this.tamdatos = tamdatos;
      this.tamheap = tamheap;
      this.ndisplays = ndisplays;
      this.codigoP = new ArrayList<>();  
      pilaEvaluacion = new Stack<>();
      datos = new Valor[tamdatos+tampila+tamheap];
      this.pc = 0;
      ISUMA = new ISuma(this);
      ISUMAREAL = new ISumaReal(this);
      IAND = new IAnd(this);
      IMUL = new IMul(this);
      IMULREAL = new IMulReal(this);
      IAPILAIND = new IApilaind(this);
      IDESAPILAIND = new IDesapilaind(this);
      IIRIND = new IIrind(this);
      IDUP = new IDup(this);
      ISTOP = new IStop(this);
      gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos,(tamdatos+tampila)-1,ndisplays); 
      gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos+tampila,(tamdatos+tampila+tamheap)-1);
   }
   public void ejecuta() {
      while(pc != codigoP.size()) {
          codigoP.get(pc).ejecuta();
      } 
   }
   public void muestraCodigo() {
     System.out.println("CodigoP");
     for(int i=0; i < codigoP.size(); i++) {
        System.out.println(" "+i+":"+codigoP.get(i));
     }
   }
   public void muestraEstado() {
     System.out.println("Tam datos:"+tamdatos);  
     System.out.println("Tam heap:"+tamheap); 
     System.out.println("PP:"+gestorPilaActivaciones.pp());      
     System.out.print("Displays:");
     for (int i=1; i <= ndisplays; i++)
         System.out.print(i+":"+gestorPilaActivaciones.display(i)+" ");
     System.out.println();
     System.out.println("Pila de evaluacion");
     for(int i=0; i < pilaEvaluacion.size(); i++) {
        System.out.println(" "+i+":"+pilaEvaluacion.get(i));
     }
     System.out.println("Datos");
     for(int i=0; i < datos.length; i++) {
        System.out.println(" "+i+":"+datos[i]);
     }
     System.out.println("PC:"+pc);
   }
   
   public static void main(String[] args) {
       MaquinaP m = new MaquinaP(5,10,10,2);
        
          /*
            int x;
            proc store(int v) {
             x = v
            }
           &&
            call store(5)
          */
            
       
       m.ponInstruccion(m.activa(1,1,8));
       m.ponInstruccion(m.dup());
       m.ponInstruccion(m.apilaReal(1.5));
       m.ponInstruccion(m.apilaReal(2.0));
       m.ponInstruccion(m.sumaReal());
       /*
       m.ponInstruccion(m.apilaReal(5.0));
       m.ponInstruccion(m.desapilaInd());
       m.ponInstruccion(m.desapilad(1));
       m.ponInstruccion(m.irA(9));
       m.ponInstruccion(m.stop());
       m.ponInstruccion(m.apilaReal(0.0));
       m.ponInstruccion(m.apilad(1));
       m.ponInstruccion(m.apilaReal(0.0));
       m.ponInstruccion(m.sumaReal());  
       m.ponInstruccion(m.mueve(1));
       m.ponInstruccion(m.desactiva(1,1));
       m.ponInstruccion(m.irInd());
       */
       m.ejecuta();
       m.muestraEstado();
   }
}