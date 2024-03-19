package com.Myrestaurant.Myrestaurant.Controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Myrestaurant.Myrestaurant.Constant;

@Controller
public class MainController {

	
@GetMapping("/{restid}/{tblid}")
public String index(@PathVariable Long restid, @PathVariable Long tblid, HttpSession session) {
    // Set session attributes
	Constant.setmapdata("restid", restid);
	Constant.setmapdata("tableId", tblid);
    System.out.println(restid);
  //System.out.println(Constant.map.get(restid));
    // Return the name of the HTML template
    
    
    return "redirect:/index";
} 
    @GetMapping("/index")
    public String index() {
    	
        return "pages/index"; // Return the name of the HTML template (without the ".html" extension)
        
        
    }
    
    
    @GetMapping("/cookpage")
    public String cook() {
        return "pages/Cook"; // Return the name of the HTML template (without the ".html" extension)
    }
    
    @GetMapping("/")
    public String Error() {
        return "pages/Error"; // Return the name of the HTML template (without the ".html" extension)
    }
    
    // Add more mapping methods for other pages as needed...
}
