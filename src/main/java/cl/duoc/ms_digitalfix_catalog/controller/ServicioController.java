package cl.duoc.ms_digitalfix_catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_digitalfix_catalog.service.ServicioService;

@RestController
@RequestMapping("/api/catalog/services") 
public class ServicioController {

    @Autowired 
    private ServicioService servicioService;
    

}
