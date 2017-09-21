package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));
	
	GildedRose store;
	List<Item> items;
	@Before
	public void setUp(){
		store = new GildedRose();
	}

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		//GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		items = store.getItems();
		//List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    
	// My codes
	@Test
	public void testUpdateEndOfDay_common_item_SellIn_decrease_1_per_day() {
		//fail("Test not implemented");
		store.addItem(new Item("common item", 3, 3));
		store.updateEndOfDay();
		items = store.getItems();
		Item commonItem = items.get(0);
		assertEquals("2", 2, commonItem.getSellIn());
	}
	@Test
	public void testUpdateEndOfDay_common_item_Quality_decrease_1_per_day() {
		//fail("Test not implemented");
		store.addItem(new Item("common item", 2, 2));
		store.updateEndOfDay();
		items = store.getItems();
		Item commonItem = items.get(0);
		assertEquals("1", 1, commonItem.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_Quality_is_never_negative(){
		store.addItem(new Item("common item", 2, 0));
		items = store.getItems();
		Item commonItem = items.get(0);
		assertEquals("quality == 0", 0, commonItem.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_commonItem_quality_degrades_twice_as_fast_when_sellIn_equals_0(){
		store.addItem(new Item("common item", -1, 4));
		items = store.getItems();
		Item commonItem = items.get(0);
		assertEquals("quality == 2", 2, commonItem.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_50(){
		store.addItem(new Item("Aged Brie", 10, 50));
		items = store.getItems();
		Item agedBrie = items.get(0);
		assertEquals("quality remains 50", 50, agedBrie.getQuality());
	}
}
