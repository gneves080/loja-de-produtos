public final class Celular extends Produto {

    public Celular() {
        super("Celular", 2999.00);
    }

    @Override
    public String getDescricao() {
        return "Smartphone 256 GB";
    }
}