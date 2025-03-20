package es.vrivas.dagil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ContenedorTest {
    /**
     * Inicialización de los tests
     */
    @BeforeAll
    public void setUp() {
        System.out.println("Iniciando test clase Contenedor...");
    }

    /**
     * Test para el método add del contenedor.
     */
    @Test
    public void add_excepcion_si_objeto_nulo() {
        // Salta exepción si se intenta añadir un objeto nulo
        Contenedor contenedor = new Contenedor();
        Contenido objeto = null;
        try {
            contenedor.add(objeto);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción lanzada: " + e.getMessage() + " para objeto nulo");
        }

    }

    @Test
    public void add_devuelve_mismo_objeto() {
        // El método debe devolver el mismo objeto
        Contenedor contenedor = new Contenedor();
        Contenido objeto = new Contenido();
        objeto.setDescripcion("Descripción en add_devuelve_mismo_objeto");
        objeto.setId(1);
        assertSame(contenedor, contenedor.add(objeto));

    }

    @Test
    public void add_inserta_objeto() {
        // El objeto se ha añadido al contenedor
        final String DESCRIPCION = "Descripción en add_inserta_objeto";
        Contenedor contenedor = new Contenedor();
        Contenido objeto = new Contenido();
        objeto.setDescripcion(DESCRIPCION);
        objeto.setId(1);
        Contenido objetoRecuperado = contenedor.add(objeto).getPorId(1);
        assert (objetoRecuperado != null);
        assert (objetoRecuperado.getDescripcion().equals(DESCRIPCION));
    }

    @Test
    public void add_excepcion_si_objeto_ya_existe() {
        try {// Se intenta añadir otra vez el mismo objeto
            Contenedor contenedor = new Contenedor();
            Contenido objeto = new Contenido();
            objeto.setDescripcion("add_excepcion_si_objeto_ya_existe");
            objeto.setId(1);
            contenedor.add(objeto);
            contenedor.add(objeto);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción lanzada: " + e.getMessage() + " para objeto ya añadido");
        }
    }

    /**
     * Test para el método getNumObjetosContenidos del contenedor.
     */
    @Test
    public void getNumObjetosContenidos_contenedor_vacio() {
        // Devuelve 0 si no hay objetos en el contenedor
        Contenedor contenedor = new Contenedor();
        assert (contenedor.getNumObjetosContenidos() == 0);
    }

    // Devuelve el número de objetos en el contenedor
    @Test
    public void getNumObjetosContenidos_contenedor_no_vacio() {
        Contenedor contenedor = new Contenedor();
        Contenido objeto = new Contenido();
        objeto.setDescripcion("Descripción en testGetNumObjetosContenidos");
        objeto.setId(1);
        contenedor.add(objeto);
        assert (contenedor.getNumObjetosContenidos() == 1);
    }

    /**
     * Test para el método getPorPosicion del contenedor.
     */
    @Test
    public void getPorPosicion_excepcion_posicion_negativa() {
        // Salta excepción si la posición no es válida
        // Prueba para posiciones menores que 0
        Contenedor contenedor = new Contenedor();
        try {
            contenedor.getPorPosicion(-1);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción lanzada: " + e.getMessage() + " para posición -1");
        }

    }

    @Test
    public void getPorPosicion_excepcion_posicion_superior_existentes() { // Prueba para una posición igual o mayor al número de elementos que hay
        Contenedor contenedor = new Contenedor();
        try {
            contenedor.getPorPosicion(0);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción lanzada: " + e.getMessage() + " para posición 0");
        }
        try {
            contenedor.getPorPosicion(1);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción lanzada: " + e.getMessage() + " para posición 1");
        }
    }

    @Test
    public void getPorPosicion_valores_limite() { // Prueba usando valores límite
        Contenedor contenedor = new Contenedor();
        Contenido objeto1 = new Contenido();
        objeto1.setDescripcion("Descripción en testGetPorPosicion para objeto1");
        objeto1.setId(1);
        contenedor.add(objeto1);
        Contenido objeto2 = new Contenido();
        objeto2.setDescripcion("Descripción en testGetPorPosicion para objeto2");
        objeto2.setId(2);
        contenedor.add(objeto2);
        Contenido objetoRecuperado = contenedor.getPorPosicion(0);
        assert (objetoRecuperado != null);
        assert (objetoRecuperado.getDescripcion().equals("Descripción en testGetPorPosicion para objeto1"));

        objetoRecuperado = contenedor.getPorPosicion(1);
        assert (objetoRecuperado != null);
        assert (objetoRecuperado.getDescripcion().equals("Descripción en testGetPorPosicion para objeto2"));
    }

    /**
     * Test para el método getPorId del contenedor.
     */
    @Test
    public void getPorId_contenedor_vacio() {
        // Devuelve null si no hay objetos en el contenedor
        {
            Contenedor contenedor = new Contenedor();
            Contenido objeto = contenedor.getPorId(1);
            assert (objeto == null);
        }
    }

    @Test
    public void getPorId_contenedor_con_elementos() {

        // Devuelve el objeto si está en el contenedor
        Contenedor contenedor = new Contenedor();
        Contenido objeto = new Contenido();
        objeto.setDescripcion("Descripción en testGetPorId");
        objeto.setId(1);
        contenedor.add(objeto);
        Contenido objetoRecuperado = contenedor.getPorId(1);
        assert (objetoRecuperado != null);
        assert (objetoRecuperado.getDescripcion().equals("Descripción en testGetPorId"));
    }

    /**
     * Test para el método toString del contenedor.
     */
    @Test
    public void toString_contenedor_vacio() {
        // Devuelve una cadena vacía si no hay objetos en el contenedor
        {
            Contenedor contenedor = new Contenedor();
            assertEquals("[]", contenedor.toString());
        }
    }

    @Test
    public void toString_contenedor_no_vacio() {
        // Devuelve una cadena con los objetos en el contenedor

        Contenedor contenedor = new Contenedor();
        Contenido objeto1 = new Contenido();
        objeto1.setDescripcion("Descripción en testToString para objeto1");
        objeto1.setId(1);
        contenedor.add(objeto1);
        // Para un solo objeto
        assertEquals("[\n{id: 1, descripcion: 'Descripción en testToString para objeto1'},\n]",
                contenedor.toString());
        Contenido objeto2 = new Contenido();
        objeto2.setDescripcion("Descripción en testToString para objeto2");
        objeto2.setId(2);
        contenedor.add(objeto2);
        // Para dos objetos
        String cadenaJSONEsperada = "[\n" +
                "{id: 1, descripcion: 'Descripción en testToString para objeto1'},\n" +
                "{id: 2, descripcion: 'Descripción en testToString para objeto2'},\n" +
                "]";
        assertEquals(cadenaJSONEsperada, contenedor.toString());

    }

    @AfterAll
    public void setDown() {
        System.out.println("Finalizando test clase Contenedor...");
    }

}
