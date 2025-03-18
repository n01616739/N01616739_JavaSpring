package com.finalproject.assignment3.security;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "2b18725b3c21ac61652969f28ee8ede54026b289a6af9821e74d4e9a0746b03c04718670488b27980bb42ac9bba73ba993eafa5fbc9bf2fe5f4e7e128cb62d43";

    public static String generateToken(String username, String role) {
        try {
            long expirationTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000); // 24 hours expiry
            Map<String, Object> payload = new HashMap<>();
            payload.put("sub", username);
            payload.put("role", role);
            payload.put("exp", expirationTime);

            String payloadJson = new ObjectMapper().writeValueAsString(payload);
            String payloadEncoded = Base64.getUrlEncoder().encodeToString(payloadJson.getBytes());

            String signature = sign(payloadEncoded);
            return payloadEncoded + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public static boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 2) return false;

            String computedSignature = sign(parts[0]);
            return computedSignature.equals(parts[1]);
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String token) {
        try {
            String payloadJson = new String(Base64.getUrlDecoder().decode(token.split("\\.")[0]));
            Map<String, Object> payload = new ObjectMapper().readValue(payloadJson, Map.class);
            return (String) payload.get("sub");
        } catch (Exception e) {
            return null;
        }
    }

    private static String sign(String data) throws Exception {
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return Base64.getUrlEncoder().encodeToString(hmac.doFinal(data.getBytes()));
    }
}
