
import java.util.Scanner;

public class App {

    static String nombreProducto = "N/A";
    static double precioUnitario = 0.0;
    static int cantidad = 0;
    static Scanner sc = new Scanner(System.in);
    static String estado = "N/A";

    public static void main(String[] args) throws Exception {

        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    mostrarProducto();
                    ;
                    break;

                case 3:
                    double total = valorTotal();
                    if (total != 0) {
                        System.out.printf("Valor Total en Inventario: %.2f", total);
                    }
                    break;

                case 4:
                    mostrarResumen();
                    break;

                case 5:
                    limpiarDatos();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema. No vuelva pronto!");
                    break;

                default:
                    System.out.println("Opción inválida, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

    }

    static void mostrarMenu() {
        var opciones = """

                 --- Sistema de Gestión de Productos ---

                1. Registrar nuevo producto
                2. Mostrar información del producto actual
                3. Calcular valor total del inventario
                4. Mostrar resumen completo del producto
                5. Limpiar datos del producto actual
                0. Salir
                """;

        System.out.println(opciones);
        System.out.println("Ingrese su opción: ");
    }

    static void registrarProducto() {
        if (nombreProducto.equals("N/A")) {
            leerProducto();

        } else {
            System.out.println("Ya hay un producto registrado. ¿Desea sobreescribir? (s/n):");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                leerProducto();
            } else {
                System.out.println("Operación cancelada. No se modificaron los datos.");
            }
        }

    }

    static void leerProducto() {
        System.out.println("Ingrese el nombre del producto: ");
        nombreProducto = sc.nextLine();
        System.out.println("Ingrese el precio unitario: ");
        precioUnitario = solicitarPrecio();
        System.out.println("Ingrese la cantidad de inventario: ");
        cantidad = solicitarCantidad();

        sc.nextLine(); // Limpia buffer

    }

    static boolean esValidoPrecio(double precioUnitario) {
        return precioUnitario > 0;
    }

    static boolean esValidaCantidad(int cantidad) {
        return cantidad > 0;
    }

    static double solicitarPrecio() {
        double precioUnitario;
        do {
            precioUnitario = sc.nextDouble();
            if (!esValidoPrecio(precioUnitario)) {
                System.out.println("Precio inválido, debe ser mayor a 0.");
            }
        } while (!esValidoPrecio(precioUnitario));
        return precioUnitario;
    }

    static int solicitarCantidad() {
        int cantidad;
        do {
            cantidad = sc.nextInt();
            if (!esValidaCantidad(cantidad)) {
                System.out.println("Cantidad inválida, debe ser mayor a 0.");
            }
        } while (!esValidaCantidad(cantidad));
        return cantidad;
    }

    static void mostrarProducto() {
        if (nombreProducto.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
        } else {
            System.out.println("Nombre del producto: " + nombreProducto);
            System.out.println("Precio unitario: " + precioUnitario);
            System.out.println("Cantidad en inventario: " + cantidad);
        }
    }

    static double valorTotal() {
        if (nombreProducto.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
            return 0;
        } else {
            return (precioUnitario * cantidad);
        }

    }

    static void mostrarResumen() {
        if (nombreProducto.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
        } else {
            System.out.println("Nombre del producto: " + nombreProducto);
            System.out.println("Precio unitario: " + precioUnitario);
            System.out.println("Cantidad en inventario: " + cantidad);
            System.out.printf("Valor Total en Inventario: %.2f", valorTotal());
            System.out.println(" ");
            setEstado();
            System.out.println("Estado del Stock: " + estado);
            
        }
    }

    static void setEstado() {
        
        if (cantidad < 5) {
            estado = "Stock bajo";
        } else if (cantidad >= 5 && cantidad <= 20) {
            estado = "Stock suficiente";
        } else if (cantidad > 20) {
            estado = "Stock alto";
        }
    }

    static void limpiarDatos() {
        nombreProducto = "N/A";
        precioUnitario = 0.0;
        cantidad = 0;
        estado = "N/A";
        System.out.println("Los datos del producto actual han sido borrados exitosamente.");
    }

    static boolean validarCantidad(int cantidad) {
        return cantidad > 0;
    }

    static boolean validarNombreProducto() {
        return !nombreProducto.equals("N/A") && !nombreProducto.isBlank();
    }

    static boolean validarPrecio(double precioUnitario) {
        return precioUnitario > 0.0;
    }

}
