package com.tienda_m.controller;

import com.tienda_m.domain.Item;
import com.tienda_m.domain.Producto;
import com.tienda_m.service.ItemService;
import com.tienda_m.service.ProductoService;
import com.tienda_m.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ItemService itemService;
    
    // "/producto/listado"
    @GetMapping("/carrito/listado")
    public String listado(Model model){
        var lista = itemService.gets();
        
        var total=0;
        for(Item i : lista){
            
            total += (i.getCantidad() * i.getPrecio());
        }
        
        model.addAttribute("items", lista);
        model.addAttribute("carritoTotal", total);
        return "/carrito/listado";
    }
    
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregar(Item item, Model model){
        Item item2=itemService.get(item);
        if(item2==null){
            Producto p = productoService.getProducto(item);
            item2 = new Item(p);
        }
        itemService.save(item2);
        var lista = itemService.gets();
        var totalCarrito=0;
        var carritoTotalVenta=0;
        for(Item i : lista){
            totalCarrito += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        
        model.addAttribute("listaItems",lista);
        model.addAttribute("listaTotal",totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminar(Item item){
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }
    
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificar(Item item, Model model){
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }
    
    @PostMapping("/carrito/guardar/{idProducto}")
    public String guardar(Item item){
        itemService.update(item);
        return "redirect:/carrito/listado";
    }
}
