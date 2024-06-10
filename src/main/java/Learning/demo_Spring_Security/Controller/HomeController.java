package Learning.demo_Spring_Security.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo-controller/")
public class HomeController {




    @GetMapping("getData")
    public ResponseEntity<Map<String, String>> getData() {
        Map<String, String> response = new HashMap<>();
        response.put("data", "This is the data, Hello World");
        return ResponseEntity.ok(response);
    }
}
