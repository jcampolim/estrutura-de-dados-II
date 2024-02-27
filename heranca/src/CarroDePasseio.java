//Nome / RA: 
//Enzo - 10410074
//Erika - 10403716
//Júlia - 10408802


public class CarroDePasseio extends VeiculoTerrestre{
	private int totalDePassageiros;
	
		//Construtores
		public CarroDePasseio(){
			super();
			totalDePassageiros = 0;
			
		}
		public CarroDePasseio(int ano, String cor, int totalDePassageiros){
			super(ano, cor);
			this.totalDePassageiros = totalDePassageiros;
		}
		
		//Getters e Setters
		public int getTotalDePassageiros() {
			return totalDePassageiros;
		}
		public void setTotalDePassageiros(int totalDePassageiros) {
			this.totalDePassageiros = totalDePassageiros;
		}
		
		// Override do metodo toString
		public String toString() {
			return "Carro de Passeio: ano " + super.getAno() + "," + " cor " + super.getCor() + "," + " Total de Passageiros " + totalDePassageiros; 
		}
}
