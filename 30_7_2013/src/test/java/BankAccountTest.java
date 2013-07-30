import com.qsoft.dao.AccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dao.impl.AccountDAOImpl;
import com.qsoft.dao.impl.TransactionDAOImpl;
import com.qsoft.model.BankAccount;
import com.qsoft.service.AccountService;
import com.qsoft.service.impl.AccountServiceImpl;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

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

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @BeforeClass
    public static void createSchema() throws Exception {
        String schemaFileName = System.class.getResource("/schema.sql").toString().substring(6);
        RunScript.execute(JDBC_URL, USER, PASSWORD, schemaFileName, Charset.forName("UTF8"), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public void setUp() throws SQLException {
        service = new AccountServiceImpl();
        accountDAO = new AccountDAOImpl(dataSource());
        transactionDAO = new TransactionDAOImpl(dataSource());
        calendar = Calendar.getInstance();
        service.setAccountDAO(accountDAO);
        service.setTransactionDAO(transactionDAO);
        service.setCalendar(calendar);
    }


    @Test
    public void openNewAccountWithBalanceEqualToZeroTest() throws SQLException {
        setUp();
        BankAccount account = service.open(accountNumber, timestamp);
        assertTrue(account.getBalance() == 0);
    }

    @Test
    public void getAccountInformationTest() throws SQLException {
        setUp();
        assertTrue(service.getAccount(accountNumber) != null);
    }

    @Test
    public void depositAccountAndReturnBalanceAfterChangingTest() throws SQLException {
        setUp();
        BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();
        assertTrue(service.deposit(accountNumber, amount, description) == oldBalance + amount);
    }

    @Test
    public void depositAccountAndSaveTransactionTest() throws SQLException {
        setUp();
        service.deposit(accountNumber, amount, description);
        assertTrue(!transactionDAO.getTransactionsOccurred(accountNumber).isEmpty());
    }

    @Test
    public void withdrawAccountAndReturnBalanceAfterChangingTest() throws SQLException {
        setUp();
        BankAccount account = service.getAccount(accountNumber);
        long oldBalance = account.getBalance();

        assertTrue(service.withdraw(accountNumber, amount, description) == oldBalance - amount);
    }
}
