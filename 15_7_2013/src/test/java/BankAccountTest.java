import com.qsoft.dao.AccountDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.service.impl.AccountServiceImpl;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest{
    private AccountServiceImpl service;
    private AccountDAO mockDao;

    public void setUp() {
        service = new AccountServiceImpl();
        mockDao = createStrictMock(AccountDAO.class);
        service.setUserDAO(mockDao);
    }

    @Test
    public void openNewAccountWithBalanceEqualToZeroTest() {
        BankAccount account = service.open("0123456789");
        assertTrue(account.getBalance() == 0);
    }

    @Test
    public void getAccountInformationTest() {
        String accountNumber = "0123456789";
        expect(mockDao.getAccount(accountNumber));

        replay(mockDao);
        assertEquals(true, service.getAccount(accountNumber));
        verify(mockDao);
    }

    @Test
    public void depositAccountAndReturnBalanceAfterChangingTest() {
        String accountNumber = "0123456789";
        long amount = 1000;
        String description = "Some thing";
        BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();
        service.deposite(accountNumber, amount, description);
        assertTrue(account.getBalance() == oldBalance + amount);
    }
}
