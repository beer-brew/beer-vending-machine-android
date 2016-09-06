package beer.brew.vendingmachine;

import android.database.Cursor;

import beer.brew.vendingmachine.data.local.DatabaseHelper;
import beer.brew.vendingmachine.data.local.Db;
import beer.brew.vendingmachine.data.local.DbOpenHelper;
import beer.brew.vendingmachine.data.model.Ribot;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.Arrays;
import java.util.List;

import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
public class DatabaseHelperTest extends ApplicationTestCase {

    private final DatabaseHelper mDatabaseHelper =
            new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application));

    @Before
    public void setUp() {
        mDatabaseHelper.clearTables().subscribe();
    }

    @Test
    public void setRibots() {
        Ribot ribot1 = TestDataFactory.makeRibot("r1");
        Ribot ribot2 = TestDataFactory.makeRibot("r2");
        List<Ribot> ribots = Arrays.asList(ribot1, ribot2);

        TestSubscriber<Ribot> result = new TestSubscriber<>();
        mDatabaseHelper.setRibots(ribots).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        for (Ribot ribot : ribots) {
            cursor.moveToNext();
            assertEquals(ribot.profile, Db.RibotProfileTable.parseCursor(cursor));
        }
    }

    @Test
    public void getRibots() {
        Ribot ribot1 = TestDataFactory.makeRibot("r1");
        Ribot ribot2 = TestDataFactory.makeRibot("r2");
        List<Ribot> ribots = Arrays.asList(ribot1, ribot2);

        mDatabaseHelper.setRibots(ribots).subscribe();

        TestSubscriber<List<Ribot>> result = new TestSubscriber<>();
        mDatabaseHelper.getRibots().subscribe(result);
        result.assertNoErrors();
        result.assertValue(ribots);
    }

}