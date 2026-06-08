package modelo;

public class Producto {
	private int idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private int idCategoria;
	private String nombreCategoria;
	private int idProveedor;
	private String nombreProveedor;

	public Producto() {
	}

	public Producto(int idProducto, String nombre, String descripcion, double precio, int stock, int idCategoria,
			int idProveedor) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.idCategoria = idCategoria;
		this.idProveedor = idProveedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int id) {
		this.idProducto = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int id) {
		this.idCategoria = id;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int id) {
		this.idProveedor = id;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
