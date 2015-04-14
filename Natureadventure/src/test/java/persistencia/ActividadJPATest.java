package persistencia;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import modelo.dao.ActividadJPA;
import modelo.datos.Actividad;

import org.junit.Test;
public class ActividadJPATest {

    @Test
    public void testListaTodasActividades() throws Exception {
        ActividadJPA actividadJPA = new ActividadJPA();
        Actividad primera = mock(Actividad.class);
        when(primera.getNombre()).thenReturn("1");
        Actividad segunda = mock(Actividad.class);
        when(segunda.getNombre()).thenReturn("2");
        actividadJPA.nuevaActividad(primera);
        actividadJPA.nuevaActividad(segunda);
        Actividad[] personas = new Actividad[2];
        Arrays.asList(primera, segunda).toArray(personas);
        assertThat(actividadJPA.listaTodasActividades(), is(personas));
    }

    @Test
    public void testBuscaActividadPorNombre() throws Exception {
        ActividadJPA actividadJPA = new ActividadJPA();
        Actividad actividadMock = mock(Actividad.class);
        when(actividadMock.getNombre()).thenReturn("Caminata Penyagolosa");
        when(actividadMock.getDuracion()).thenReturn("300");
        when(actividadMock.getDescripcion()).thenReturn("Excursion a la montaña del Penyagolosa");
        when(actividadMock.getNivel()).thenReturn("Medio");
        when(actividadMock.getFechaInicio()).thenReturn("01/01/2015");
        when(actividadMock.getFechaFin()).thenReturn("02/01/2015");
        when(actividadMock.getHoraInicio()).thenReturn("10:00");
        when(actividadMock.getPrecio()).thenReturn((float) 5);
        when(actividadMock.getParticipantesMin()).thenReturn((int)20);
        when(actividadMock.getParticipantesMax()).thenReturn((int)30);
        when(actividadMock.getLugar()).thenReturn("C/falsa 123");
        when(actividadMock.getImagen()).thenReturn("imagen");
        actividadJPA.nuevaActividad(actividadMock);
        Actividad encontrada = actividadJPA.buscaActividadPorNombre("Caminata Penyagolosa");
        assertThat(encontrada.getNombre(), is("Caminata Penyagolosa"));
        assertThat(encontrada.getDuracion(), is("300"));
        assertThat(encontrada.getDescripcion(), is("Excursion a la montaña del Penyagolosa"));
    }

    @Test
    public void testNuevaActividad() throws Exception {
    	ActividadJPA actividadJPA = new ActividadJPA();
        Actividad actividadMock = mock(Actividad.class);
        when(actividadMock.getNombre()).thenReturn("Caminata Penyagolosa");
        when(actividadMock.getDuracion()).thenReturn("300");
        when(actividadMock.getDescripcion()).thenReturn("Excursion a la montaña del Penyagolosa");
        when(actividadMock.getNivel()).thenReturn("Medio");
        when(actividadMock.getFechaInicio()).thenReturn("01/01/2015");
        when(actividadMock.getFechaFin()).thenReturn("02/01/2015");
        when(actividadMock.getHoraInicio()).thenReturn("10:00");
        when(actividadMock.getPrecio()).thenReturn((float) 5);
        when(actividadMock.getParticipantesMin()).thenReturn((int)20);
        when(actividadMock.getParticipantesMax()).thenReturn((int)30);
        when(actividadMock.getLugar()).thenReturn("C/falsa 123");
        when(actividadMock.getImagen()).thenReturn("imagen");
        actividadJPA.nuevaActividad(actividadMock);
        Actividad encontrada = actividadJPA.buscaActividadPorNombre("Caminata Penyagolosa");
        assertThat(encontrada.getNombre(), is("Caminata Penyagolosa"));
        assertThat(encontrada.getDuracion(), is("300"));
        assertThat(encontrada.getDescripcion(), is("Excursion a la montaña del Penyagolosa"));
    }

    @Test
    public void testActualizaPersona() throws Exception {
    	ActividadJPA actividadJPA = new ActividadJPA();
        Actividad actividadMock = mock(Actividad.class);
        when(actividadMock.getNombre()).thenReturn("Caminata Penyagolosa");
        when(actividadMock.getDuracion()).thenReturn("300");
        when(actividadMock.getDescripcion()).thenReturn("Excursion a la montaña del Penyagolosa");
        when(actividadMock.getNivel()).thenReturn("Medio");
        when(actividadMock.getFechaInicio()).thenReturn("01/01/2015");
        when(actividadMock.getFechaFin()).thenReturn("02/01/2015");
        when(actividadMock.getHoraInicio()).thenReturn("10:00");
        when(actividadMock.getPrecio()).thenReturn((float) 5);
        when(actividadMock.getParticipantesMin()).thenReturn((int)20);
        when(actividadMock.getParticipantesMax()).thenReturn((int)30);
        when(actividadMock.getLugar()).thenReturn("C/falsa 123");
        when(actividadMock.getImagen()).thenReturn("imagen");
        actividadJPA.nuevaActividad(actividadMock);
        Actividad nuevaMock = mock(Actividad.class);
        when(nuevaMock.getNombre()).thenReturn("Caminata Penyagolosa");
        when(nuevaMock.getDuracion()).thenReturn("300");
        when(nuevaMock.getDescripcion()).thenReturn("Excursion a la montaña del Penyagolosa");
        when(nuevaMock.getNivel()).thenReturn("Alto");
        when(nuevaMock.getFechaInicio()).thenReturn("01/01/2015");
        when(nuevaMock.getFechaFin()).thenReturn("02/01/2015");
        when(nuevaMock.getHoraInicio()).thenReturn("10:00");
        when(nuevaMock.getPrecio()).thenReturn((float) 5);
        when(nuevaMock.getParticipantesMin()).thenReturn((int)20);
        when(nuevaMock.getParticipantesMax()).thenReturn((int)30);
        when(nuevaMock.getLugar()).thenReturn("C/falsa 123");
        when(nuevaMock.getImagen()).thenReturn("imagen");
        actividadJPA.actualizaActividad(nuevaMock);
        Actividad encontrada = actividadJPA.buscaActividadPorNombre("Caminata Penyagolosa");
        assertThat(encontrada.getNombre(), is("Caminata Penyagolosa"));
        assertThat(encontrada.getDuracion(), is("300"));
        assertThat(encontrada.getDescripcion(), is("Excursion a la montaña del Penyagolosa"));
        assertThat(encontrada.getNivel(), is("Alto"));
        assertThat(encontrada.getFechaInicio(), is("01/01/2015"));
        assertThat(encontrada.getFechaFin(), is("02/01/2015"));
        assertThat(encontrada.getHoraInicio(), is("10:00"));
        assertThat(Float.toString(encontrada.getPrecio()), is("5"));
        assertThat(encontrada.getParticipantesMin(), is(20));
        assertThat(encontrada.getParticipantesMax(), is(30));
        assertThat(encontrada.getLugar(), is("C/falsa 123"));
        assertThat(encontrada.getImagen(), is("imagen"));
    }

    @Test
    public void testBorraPersona() throws Exception {
    	ActividadJPA actividadJPA = new ActividadJPA();
        Actividad actividadMock = mock(Actividad.class);
        when(actividadMock.getNombre()).thenReturn("Caminata Penyagolosa");
        when(actividadMock.getDuracion()).thenReturn("300");
        when(actividadMock.getDescripcion()).thenReturn("Excursion a la montaña del Penyagolosa");
        when(actividadMock.getNivel()).thenReturn("Medio");
        when(actividadMock.getFechaInicio()).thenReturn("01/01/2015");
        when(actividadMock.getFechaFin()).thenReturn("02/01/2015");
        when(actividadMock.getHoraInicio()).thenReturn("10:00");
        when(actividadMock.getPrecio()).thenReturn((float) 5);
        when(actividadMock.getParticipantesMin()).thenReturn((int)20);
        when(actividadMock.getParticipantesMax()).thenReturn((int)30);
        when(actividadMock.getLugar()).thenReturn("C/falsa 123");
        when(actividadMock.getImagen()).thenReturn("imagen");
        actividadJPA.nuevaActividad(actividadMock);
        Actividad encontrada = actividadJPA.buscaActividadPorNombre("Caminata Penyagolosa");
        assertThat(encontrada.getNombre(), is("Caminata Penyagolosa"));
        assertThat(encontrada.getDuracion(), is("300"));
        assertThat(encontrada.getDescripcion(), is("Excursion a la montaña del Penyagolosa"));
        assertThat(encontrada.getNivel(), is("Alto"));
        assertThat(encontrada.getFechaInicio(), is("01/01/2015"));
        assertThat(encontrada.getFechaFin(), is("02/01/2015"));
        assertThat(encontrada.getHoraInicio(), is("10:00"));
        assertThat(Float.toString(encontrada.getPrecio()), is("5"));
        assertThat(encontrada.getParticipantesMin(), is(20));
        assertThat(encontrada.getParticipantesMax(), is(30));
        assertThat(encontrada.getLugar(), is("C/falsa 123"));
        assertThat(encontrada.getImagen(), is("imagen"));
        actividadJPA.borraActividad("Caminata Penyagolosa");
        encontrada = actividadJPA.buscaActividadPorNombre("Caminata Penyagolosa");
        assertThat(encontrada, is(ActividadJPA.ENTRADA_NULL));
    }
}