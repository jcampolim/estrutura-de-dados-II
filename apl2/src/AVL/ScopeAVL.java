package AVL;

// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

public class ScopeAVL extends NodeAVL {

    // Atributo
    private String identifier;


    // Construtores
    public ScopeAVL() {
        super();
    }

    public ScopeAVL(String identifier) {
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
