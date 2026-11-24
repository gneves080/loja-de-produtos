public final class Radio extends Produto {

    public Radio() {
        super("Rádio", 199.90);
    }

    @Override
    public String getDescricao() {
        return "Rádio portátil AM/FM";
    }
}