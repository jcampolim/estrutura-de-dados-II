package AVL;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

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
