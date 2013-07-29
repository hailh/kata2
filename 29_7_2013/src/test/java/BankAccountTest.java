import com.qsoft.model.BankAccount;
import com.qsoft.service.AccountService;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    @Test
    public void openNewAccountWithBalanceEqualToZeroTest() {
        String accountNumber = "0123456789";
        AccountService service = new AccountService();
        BankAccount account = service.open(accountNumber);

        assertTrue(account.getBalance() == 0);
    }
}
