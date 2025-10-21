package com.example.gestion.util;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordUtil {
    public static String hash(String plain) { return BCrypt.hashpw(plain, BCrypt.gensalt()); }
    public static boolean check(String plain, String stored) {
        if (stored == null) return false;
        // detect bcrypt hash ($2a$ or $2b$)
        if (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$")) {
            return BCrypt.checkpw(plain, stored);
        }
        // fallback: compare plaintext (for existing plain passwords)
        return plain.equals(stored);
    }
}
