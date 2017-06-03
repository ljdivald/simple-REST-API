package iWays.Task;

import iWays.Task.DL.UserAccountDL;
import iWays.Task.Models.UserAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class UserAccountControler {

    @RequestMapping(value = "/users/{usersId}", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> options() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public UserAccount users(@PathVariable(value="userId") int userId) {
        UserAccountDL userAccountDL = new UserAccountDL();
        return userAccountDL.Read(userId);
    }

    @RequestMapping(value = "/users/{userId}", headers="Content-Type=application/json", method = RequestMethod.PATCH)
    public ResponseEntity<String> update(@PathVariable(value="userId") int userId, @RequestBody UserAccount userAccount) {
        UserAccountDL userAccountDL = new UserAccountDL();
        try {
            userAccountDL.Update(userAccount);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/{userId}", headers="Content-Type=application/json", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(value="userId") int userId) {
        UserAccountDL userAccountDL = new UserAccountDL();
        try {
            userAccountDL.Delete(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
