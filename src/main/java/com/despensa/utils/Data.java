
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.despensa.model.Cliente;
import com.despensa.model.Producto;
import com.despensa.model.Ventas;
import com.despensa.service.ClienteService;
import com.despensa.service.ProductoService;
import com.despensa.service.VentasService;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * Clase encargada de la generación de datos para el llenado de la base de datos con datos de muestra.
 *
 * @author  Elva Kheler: mekdy.20@gmail.com
 *          Héctor Liceaga: lice2187@gmail.com
 *  		Nicolás Carsaniga: nikitobombero@gmail.com
 *  		Sergio Yañez: sergiomyanez02@gmail,.com
 * @version 1.0
 * @since 25/06/2022
 */
@Configuration
public class Data {
    /**
     * Variable auxiliar
     */
    long ID =1;


    @Bean
    public CommandLineRunner cargaDB(ClienteService clientes, ProductoService productos, VentasService ventas){
        return args-> {
            //Se insertan 10 productos
            IntStream.range(0, 10).forEach(i -> {
                Producto p = new Producto("Producto " + i, 10 + i * 2, 100 + i * 5);
                try {
                    productos.create(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            //Se insertan 10 clientes
            IntStream.range(0, 10).forEach(i -> {
                Cliente c = new Cliente("Cliente " + i, "Apellido " + i);
                try {
                    clientes.create(c);
                    ID ++;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            ID = 11;
            //Se insertan 10 ventas
            IntStream.range(0, 10).forEach(i -> {
               Date f1=new Date(2022-8-10);
               Date f2 =new Date(2022-7-23);
               Date f3 =new Date(2022-7-12);
               Cliente c1= new Cliente("nombre","apellido");
                try {
                	//ventas(cliente,producto,fecha,cant)
                    Ventas v = new Ventas (clientes.find((int)ID),productos.find((int)ID),(java.sql.Date) f1,1);
                    ventas.create(v);
                    if((ID+2)<20){
                        v = new Ventas (clientes.find(ID+1),f2);
                        ventas.create(v);
                        v = new Ventas (clientes.find(ID+2),f3);
                        ventas.create(v);
                    }
                    ID++;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            ID = 21;
            //Se insertan 10 itemsVenta
            IntStream.range(0, 10).forEach(i -> {
                long id = i + 1;
                LocalDate date = LocalDate.of(2020, 1, 8);
                ItemVenta item = null;
                try {
                    item = new ItemVenta(productos.findById(ID-20), ventas.findById(ID),  1);
                    itemsVenta.save(item);
                    if((ID-18)<10){
                        item = new ItemVenta(productos.findById(ID-19), ventas.findById(ID),  2);
                        itemsVenta.save(item);
                        item = new ItemVenta(productos.findById(ID-18), ventas.findById(ID),  3);
                        itemsVenta.save(item);
                    }
                    ID++;

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        };
    }
}

