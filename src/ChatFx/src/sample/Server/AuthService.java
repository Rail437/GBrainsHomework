package sample.Server;

import java.util.Optional;

/**
 * Сервис авторизации
 */
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
