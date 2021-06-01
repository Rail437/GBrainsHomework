package sample.Server;

import java.util.Optional;

public interface AuthService {
    /**
     * запустить сервис
     */
    void start();

    /**
     * Остановить сервис
     */
    void stop();

    Optional<String> getNickByLoginAndPass(String part, String part1);
}
