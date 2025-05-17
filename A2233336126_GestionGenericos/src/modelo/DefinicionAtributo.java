package modelo;

public class DefinicionAtributo {
    private String nombre;
    private String tipoDato;
    private String valor;

    // Constructor por defecto requerido por Gson
    public DefinicionAtributo() {}

    public DefinicionAtributo(String nombre, String tipoDato, Object valor) {
        this.nombre = nombre;
        this.tipoDato = tipoDato;
        this.valor = valor != null ? valor.toString() : "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Object getValorParseado() {
        switch (tipoDato.toLowerCase()) {
            case "int": return Integer.parseInt(valor);
            case "double": return Double.parseDouble(valor);
            case "boolean": return Boolean.parseBoolean(valor);
            default: return valor;
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}