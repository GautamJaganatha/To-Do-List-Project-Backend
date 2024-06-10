package Learning.demo_Spring_Security.Dto;

import Learning.demo_Spring_Security.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    private String firstname;
    private String lastname;
    private String username;
    private String password;

}
