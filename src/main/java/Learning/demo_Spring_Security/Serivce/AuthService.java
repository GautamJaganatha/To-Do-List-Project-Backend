package Learning.demo_Spring_Security.Serivce;

import Learning.demo_Spring_Security.Controller.AuthenticationResponse;
import Learning.demo_Spring_Security.Dto.LoginRequest;
import Learning.demo_Spring_Security.Dto.Register;
import Learning.demo_Spring_Security.Utils.JwtUtils;
import Learning.demo_Spring_Security.entity.User;
import Learning.demo_Spring_Security.enums.Role;
import Learning.demo_Spring_Security.repo.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;


    public Register register(Register register) throws IOException {
        var user = User.builder()
                .firstname(register.getFirstname())
                .lastname(register.getLastname())
                .email(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return register;

    }

    public AuthenticationResponse authenticate(LoginRequest register) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(register.getUsername(), register.getPassword())
        );
        var user = userRepository.findByEmail(register.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found in User"));
        var jwtToken = jwtUtils.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
