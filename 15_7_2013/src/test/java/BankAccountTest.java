import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dao.impl.AccountDAOImpl;
import com.qsoft.dao.impl.TransactionDAOImpl;
import com.qsoft.model.BankAccount;
import com.qsoft.service.AccountService;
import com.qsoft.service.impl.AccountServiceImpl;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/15/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest{
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    private AccountService service;
    private Calendar calendar;

    public void setUp(){
        service = new AccountServiceImpl();
        accountDAO = mock(AccountDAOImpl.class);
        transactionDAO = mock(TransactionDAOImpl.class);
        calendar = mock(Calendar.class);
        service.setUserDAO(accountDAO);
        service.setTransactionDAO(transactionDAO);
        service.setCalendar(calendar);
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
        verify(accountDAO).getAccount(accountNumber);
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

    @Test
    public void depositAccountAndSaveTransactionTest() {
        setUp();
        String accountNumber = "0123456789";
        final long amount = 1000;
        String description = "Some thing";

        when(calendar.getTimeInMillis()).thenReturn(2000L);
        service.deposit(accountNumber, amount, description);
        verify(transactionDAO).deposit(accountNumber, 2000L, amount, description);
    }

    @Test
    public void withdrawAccountAndReturnBalanceAfterChangingTest() {
        setUp();
        String accountNumber = "0123456789";
        final long amount = 1000;
        String description = "Some thing";

        when(accountDAO.getAccount(accountNumber)).thenReturn(new BankAccount(accountNumber));
        final BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();

        when(accountDAO.withdraw(accountNumber, amount, description)).thenReturn(account.getBalance() - amount);
        assertTrue(service.withdraw(accountNumber, amount, description) == oldBalance - amount);
    }

    @Test
    public void withdrawAccountAndSaveTransactionTest() {
        setUp();
        String accountNumber = "0123456789";
        final long amount = 1000;
        String description = "Some thing";

        when(calendar.getTimeInMillis()).thenReturn(2000L);
        service.withdraw(accountNumber, amount, description);
        verify(transactionDAO).withdraw(accountNumber, 2000L, amount, description);
    }

    @Test
    public void getTransactionsOccurredTest() {
        setUp();
        String accountNumber = "0123456789";

        service.getTransactionsOccurred(accountNumber);
        verify(transactionDAO).getTransactionsOccurred(accountNumber);
    }

    @Test
    public void getTransactionsOccurredFromStartTimeToStopTimeTest() {
        setUp();
        String accountNumber = "0123456789";
        Date startTime = new Date(2013, 7, 15);
        Date stopTime = new Date(2013, 7, 20);

        service.getTransactionsOccurred(accountNumber, startTime, stopTime);
        verify(transactionDAO).getTransactionsOccurred(accountNumber, startTime, stopTime);
    }

    @Test
    public void getNewTransactionsOccurredTest() {
        setUp();
        String accountNumber = "0123456789";
        int times = 100;

        service.getNewTransactionsOccurred(accountNumber, times);
        verify(transactionDAO).getNewTransactionsOccurred(accountNumber, times);
    }
}
