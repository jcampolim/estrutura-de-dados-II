//Nome / RA: 
//Enzo - 10410074
//Erika - 10403716
//Júlia - 10408802



public class Main {

	public static void main(String[] args) {
		VeiculoTerrestre[] lista = new VeiculoTerrestre[8];
		lista[0] = new CarroDePasseio(1990, "Azul", 5);
		lista[1] = new CarroDePasseio(2004, "Cinza", 4);
		lista[2] = new Caminhao(1999, "Verde", 4);
		lista[3] = new CarroDePasseio(2010, "Preta", 5);
		lista[4] = new CarroDePasseio(2009, "Preta", 7);
		lista[5] = new Caminhao(2011, "Verde", 4);
		lista[6] = new Caminhao(2000, "Azul", 4);
		lista[7] = new Caminhao(2005, "Preto", 3);
		
		
		System.out.println("Lista de Carro de Passeio que comportem 5 passageiros e tenham ano menor do que 2010:");
		for(VeiculoTerrestre p : lista) {
			if( p instanceof  CarroDePasseio  && ((CarroDePasseio)p).getTotalDePassageiros() > 4 && ((CarroDePasseio)p).getAno() < 2010 ) {
				System.out.println((CarroDePasseio)p);
			}
		}
		
		int soma = 0;
		for(VeiculoTerrestre p : lista) {
			if( p instanceof  Caminhao  && ((Caminhao)p).getTotalDeEixos() == 4 && ((Caminhao)p).getCor().equalsIgnoreCase("Verde")) {
				++soma;
			}
		}
		System.out.println("\nA soma de caminhões que são verdes e que tenham quatro eixos é de: " + soma);


	}

}