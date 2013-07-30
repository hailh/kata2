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

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 7/29/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;
    private AccountService service;
    private Calendar calendar;

    String accountNumber = "0123456789";
    long timestamp = 5000L;
    long amount = 1000;
    String description = "Some thing";
    Date startTime = new Date(2013, 7, 15);
    Date stopTime = new Date(2013, 7, 20);
    int times = 100;

    public void setUp(){
        service = new AccountServiceImpl();
        accountDAO = mock(AccountDAOImpl.class);
        transactionDAO = mock(TransactionDAOImpl.class);
        calendar = mock(Calendar.class);
        service.setAccountDAO(accountDAO);
        service.setTransactionDAO(transactionDAO);
        service.setCalendar(calendar);

        when(accountDAO.createAccount(accountNumber, timestamp)).thenReturn(new BankAccount(accountNumber));
        when(accountDAO.getAccount(accountNumber)).thenReturn(new BankAccount(accountNumber));
    }

    @Test
    public void openNewAccountWithBalanceEqualToZeroTest() {
        setUp();
        BankAccount account = service.open(accountNumber, timestamp);
        verify(accountDAO).createAccount(accountNumber, timestamp);
        assertTrue(account.getBalance() == 0);
    }

    @Test
    public void getAccountInformationTest() {
        setUp();
        assertTrue(service.getAccount(accountNumber) != null);
    }

    @Test
    public void depositAccountAndReturnBalanceAfterChangingTest() {
        setUp();
        BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();

        when(accountDAO.deposit(accountNumber, amount, description)).thenReturn(account.getBalance() + amount);
        assertTrue(service.deposit(accountNumber, amount, description) == oldBalance + amount);
        verify(accountDAO).deposit(accountNumber, amount, description);
    }

    @Test
    public void depositAccountAndSaveTransactionTest() {
        setUp();
        when(calendar.getTimeInMillis()).thenReturn(2000L);
        service.deposit(accountNumber, amount, description);
        verify(transactionDAO).deposit(accountNumber, 2000L, amount, description);
    }

    @Test
    public void withdrawAccountAndReturnBalanceAfterChangingTest() {
        setUp();
        BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();

        when(accountDAO.withdraw(accountNumber, amount, description)).thenReturn(account.getBalance() - amount);
        assertTrue(service.withdraw(accountNumber, amount, description) == oldBalance - amount);
        verify(accountDAO).withdraw(accountNumber, amount, description);
    }

    @Test
    public void withdrawAccountAndSaveTransactionTest() {
        setUp();
        when(calendar.getTimeInMillis()).thenReturn(2000L);
        service.withdraw(accountNumber, amount, description);
        verify(transactionDAO).withdraw(accountNumber, 2000L, amount, description);
    }

    @Test
    public void getTransactionsOccurredTest() {
        setUp();
        service.getTransactionsOccurred(accountNumber);
        verify(transactionDAO).getTransactionsOccurred(accountNumber);
    }

    @Test
    public void getTransactionsOccurredFromStartTimeToStopTimeTest() {
        setUp();
        service.getTransactionsOccurred(accountNumber, startTime, stopTime);
        verify(transactionDAO).getTransactionsOccurred(accountNumber, startTime, stopTime);
    }

    @Test
    public void getNewTransactionsOccurredTest() {
        setUp();
        service.getNewTransactionsOccurred(accountNumber, times);
        verify(transactionDAO).getNewTransactionsOccurred(accountNumber, times);
    }
}
