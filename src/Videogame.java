public final class Videogame extends Produto {

    public Videogame() {
        super("Videogame", 3999.00);
    }

    @Override
    public String getDescricao() {
        return "Console de última geração";
    }
}