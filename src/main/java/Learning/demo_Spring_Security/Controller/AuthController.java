package Learning.demo_Spring_Security.Controller;

import Learning.demo_Spring_Security.Dto.LoginRequest;
import Learning.demo_Spring_Security.Dto.Register;
import Learning.demo_Spring_Security.Serivce.AuthService;
import Learning.demo_Spring_Security.Utils.JwtUtils;
import Learning.demo_Spring_Security.entity.User;
import Learning.demo_Spring_Security.repo.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final AuthService authService;


    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) throws IOException {
        return ResponseEntity.ok(authService.register(register));
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginRequest register){
//        return ResponseEntity.ok(authService.authenticate(register));
//    }


    @PostMapping("/authenticate")
    public void createAuthentication(@RequestBody LoginRequest request, HttpServletResponse response)throws IOException,
            JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username");
        }
        Optional<User> optionalUser = userRepository.findByEmail(request.getUsername());

        final String jwt = jwtUtils.generateToken(optionalUser.get().getEmail());

        if (optionalUser.isPresent()){
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .put("jwt",jwt)
                    .toString()
            );
        }
        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.addHeader("Access-Control-Allow","Authorization, X-PINGOTHER, Origin,"+
                "X-Requested-Width, Content-Type, Accept, X-Custom-header");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }
}
