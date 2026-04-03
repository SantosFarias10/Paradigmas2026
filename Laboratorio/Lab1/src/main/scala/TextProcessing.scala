// Importamos la clase de Instant, que nos permite representar un instante en el tiempo en UTC
import java.time.Instant

object TextProcessing {
    // Esta funcion convierte un timeStamp Unix (segundos) a un String ISO en UTC
    def formatDateFromUTC(timeStamp: Double): String = {
        val isoDate = Instant.ofEpochSecond(timeStamp.toLong)
        isoDate.toString()
    }
}