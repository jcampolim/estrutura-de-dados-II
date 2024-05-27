package AVL;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
// https://www.javatpoint.com/java-tokens
// https://www.devmedia.com.br/tokenizacao-com-scanner-em-java/26508
// SZWARCFITER, J.L.; MARKENZON, L. Estruturas de Dados e seus Algoritmos. 3ª. ed. Rio de Janeiro: LTC, 2010.

public class KeyAVL extends NodeAVL {

    // Atributos
    private String identifier;
    private String value;


    // Construtores
    public KeyAVL() {
        super();
    }

    public KeyAVL(String identifier, String value) {
        super();
        this.identifier = identifier;
        this.value = value;
    }

    // Getters e Setters
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
