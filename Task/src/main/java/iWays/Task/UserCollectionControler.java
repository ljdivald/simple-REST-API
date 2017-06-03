package iWays.Task;

import iWays.Task.DL.UserDL;
import iWays.Task.Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class UserCollectionControler {
    @RequestMapping(value = "/users", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> options() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody User user) {
        UserDL userDL = new UserDL();
        try {
            userDL.Create(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
