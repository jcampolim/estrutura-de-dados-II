package BST;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

public class ScopeBST extends NodeBST {

    // Atributo
    private String identifier;

    // Construtor
    public ScopeBST() {
        super();
    }

    public ScopeBST(String identifier) {
        super();
        this.identifier = identifier;
    }

    // Getter e Setter
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
