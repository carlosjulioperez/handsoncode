package ec.carper.javacore.code;

public class DetalleHabilitadorDTO {
    private Integer codigo;
    private String detalle;
    public DetalleHabilitadorDTO(Integer codigo, String detalle) {
        this.codigo = codigo;
        this.detalle = detalle;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}