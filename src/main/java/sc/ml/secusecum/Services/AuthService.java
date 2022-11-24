package sc.ml.secusecum.Services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    public Map<String, String> authenticate(
            String grantType,
            String username,
            String password,
            boolean withRefreshToken,
            String refreshToken
    ){
        return null;
    }
}
