package sample.DataBase;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.Server.MyServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static sample.Server.ChatConstants.*;

public class BaseAuthService implements AuthService {
    //private static final Logger LOGGER = LogManager.getLogger(BaseAuthService.class);
    private static final com.sun.org.slf4j.internal.Logger LOGGER = LoggerFactory.getLogger(MyServer.class);



    private class Entry {
        private final String nick;
        private final String login;
        private final String pass;

        public Entry(String nick, String login, String pass) {
            this.nick = nick;
            this.login = login;
            this.pass = pass;
        }
    }

    private final List<Entry> entries;

    public BaseAuthService() {
        entries = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler();
        ResultSet set = db.searchForUserTest();
        try {
            while (set.next()){
                entries.add(new Entry(
                        set.getString(USER_NICK),
                        set.getString(USER_LOGIN),
                        set.getString(USER_PASSWORD)));
            }
            set.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
        }
        /*entries.add(new Entry("nick1", "login1", "pass1"));
        entries.add(new Entry("nick2", "login2", "pass2"));
        entries.add(new Entry("nick3", "login3", "pass3"));
        entries.add(new Entry("nick4", "login4", "pass4"));
        */       /* new Entry("nick2", "login2", "pass2"),
                new Entry("nick3", "login3", "pass3"),
                new Entry("nick4", "login4", "pass4")*/
    }

    @Override
    public void start() {
        //LOGGER.warn(this.getClass().getName() + " server started");
        LOGGER.warn("Server started");
        //System.out.println(this.getClass().getName() +" server started");
    }

    @Override
    public void stop() {
       // System.out.println(this.getClass().getName() +" server stopped");
        LOGGER.warn(this.getClass().getName() +" server stopped");
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String pass) {
        return entries.stream()
                .filter(entry -> entry.login.equals(login) && entry.pass.equals(pass))
                .map(entry -> entry.nick)
                .findFirst();
       /* for (Entry entry : entries) {
            if (entry.login.equals(login) && entry.pass.equals(pass)) {
                return entry.nick;
            }
        }*/
        //return null;
    }
}
