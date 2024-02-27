//Nome / RA: 
//Enzo - 10410074
//Erika - 10403716
//Júlia - 10408802

//Fontes utilizadas para esse trabalho: 
// Material disponibilizado pelo professor.
// e os seguintes sites;
// - https://www.devmedia.com.br/trabalhando-com-arrays-em-java/25530
// - https://pt.stackoverflow.com/questions/3905/como-comparar-strings-em-java

public class VeiculoTerrestre {
	private int ano;
	private String cor;
	
	//Construtores
	public VeiculoTerrestre(){
		ano = -1;
		cor = "";
		
	}
	public VeiculoTerrestre(int ano, String cor){
		this.ano = ano;
		this.cor = cor;
	}
	
	//Getter e Setters
	public int getAno() {
		return ano;
	}
	public String getCor() {
		return cor;
	}
	
	public void setANo(int ano) {
		this.ano = ano;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	// Override do metodo toString
	public String toString() {
		return "Veiculo Terrestre: ano " + ano + ","+ " cor " + cor + ","; 
	}

}