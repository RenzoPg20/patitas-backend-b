//PARTE 6
package pe.edu.cibertec.patitas_backend_b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.service.AutenticacionService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class AutenticacionServiceimpl implements AutenticacionService {

    @Autowired
//PERMITE HACER LA CARGA DE UN ARCHIVO EN MEMORIA
    ResourceLoader resourceLoader;


    //DERIVAMOS LAS EXECPCIONES AL CONTROLADOR IOEXCEPTIOM
    //IOEXCEPTION SALE ERROR EXPLICACION---> Porque una excepcion mapeada en la implementación tiene que ser mapeada tambien en la Interface(AutenticacionService)
    @Override
    public String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException  {

        //DEFINIMOS TIPO DE DATO RETORNAR INFORMACION

        String[] datosUsuario=null;
        Resource resource= resourceLoader.getResource("classpath:usuarios.txt");


        try(BufferedReader br =new BufferedReader(new FileReader(resource.getFile()))){


            //IMPLEMENT
            String linea;
            while((linea = br.readLine()) != null){
                String[] datos=linea.split(";");
                if(loginRequestDTO.tipoDocumento().equals(datos[0]) && loginRequestDTO.numeroDocumento().equals(datos[1])
                && loginRequestDTO.password().equals(datos[2])){



                    datosUsuario =new String[2];
                    datosUsuario[0]=datos[3];//Recuperar nombre
                    datosUsuario[1]=datos[4];//Recuperar email


                }
            }

        }catch (IOException e){
            datosUsuario=null;
            throw new IOException(e);
        }






        return datosUsuario;
    }



}
