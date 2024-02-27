//Nome / RA: 
//Enzo - 10410074
//Erika - 10403716
//Júlia - 10408802

public class Caminhao extends VeiculoTerrestre{
	private int totalDeEixos;
	
	//Construtores
	public Caminhao(){
		super();
		totalDeEixos = 0;
				
	}
	public Caminhao(int ano, String cor, int totalDeEixos){
		super(ano, cor);
		this.totalDeEixos = totalDeEixos;
	}
	
		//Getters e Setters
		public int getTotalDeEixos() {
			return totalDeEixos;
		}
		public void setTotalDeEixos(int totalDeEixos) {
			this.totalDeEixos = totalDeEixos;
		}
		
		// Override do metodo toString
		public String toString() {
			return "Carro de Passeio: ano " + super.getAno() + "," + " cor " + super.getCor() + "," + " Total de Eixos " + totalDeEixos; 
		}
}