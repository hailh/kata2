import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.impl.AccountDAOImpl;
import com.qsoft.model.BankAccount;
import com.qsoft.service.AccountService;
import com.qsoft.service.impl.AccountServiceImpl;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    private AccountDAO accountDAO;
    private AccountService service;

    public void setUp(){
        service = new AccountServiceImpl();
        accountDAO = mock(AccountDAOImpl.class);
        service.setAccountDAO(accountDAO);
    }

    @Test
    public void openNewAccountWithBalanceEqualToZeroTest() {
        setUp();
        String accountNumber = "0123456789";
        when(accountDAO.createAccount(accountNumber)).thenReturn(new BankAccount(accountNumber));
        BankAccount account = service.open(accountNumber);
        verify(accountDAO).createAccount(accountNumber);
        assertTrue(account.getBalance() == 0);
    }

    @Test
    public void getAccountInformationTest() {
        setUp();
        String accountNumber = "0123456789";
        when(accountDAO.getAccount(accountNumber)).thenReturn(new BankAccount(accountNumber));
        assertTrue(service.getAccount(accountNumber) != null);
    }

    @Test
    public void depositAccountAndReturnBalanceAfterChangingTest() {
        setUp();
        String accountNumber = "0123456789";
        final long amount = 1000;
        String description = "Some thing";

        when(accountDAO.getAccount(accountNumber)).thenReturn(new BankAccount(accountNumber));
        final BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();

        when(accountDAO.deposit(accountNumber, amount, description)).thenReturn(account.getBalance() + amount);
        assertTrue(service.deposit(accountNumber, amount, description) == oldBalance + amount);
    }
}
