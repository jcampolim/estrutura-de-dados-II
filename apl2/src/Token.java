// Nomes:
// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802
// Fontes:
// Materiais disponibilizados pelos professores
// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

public class Token {

    // Atributos
    private TokenType type;
    private String value;

    // Construtor
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    // Getters
    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + ": " + value;
    }
}
