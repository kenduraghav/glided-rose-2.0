package com.gildedrose.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

	private Item createAndUpdate(String name, int sellIn, int quality) {
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		return app.items[0];
	}

	@Test
	void junitFrameworkWorks() {
		Item item = createAndUpdate("foo", 0, 0);
		assertEquals("foo", item.name);
	}

	@Test
	void systemLowersValues() {
		Item item = createAndUpdate("foo", 15, 25);
		assertEquals(14, item.sellIn);
		assertEquals(24, item.quality);
	}

	@Test
	void qualityDegradesTwicesAsFast() {
		Item item = createAndUpdate("foo", 0, 17);
		assertEquals(15, item.quality);
	}

	@Test
	void qualityIsNeverNegative() {
		Item item = createAndUpdate("foo", 0, 0);
		assertEquals(0, item.quality);
	}

	@Test
	void agedBrieIncreaseQuality() {
		Item item = createAndUpdate("Aged Brie", 15, 25);
		assertEquals(26, item.quality);
	}

	@Test
	void qualityIsNeverMoreThan50() {
		Item item = createAndUpdate("foo", 15, 52);
		assertEquals(51, item.quality);

		item = createAndUpdate("Aged Brie", 15, 50);
		assertEquals(50, item.quality);
	}

	@Test
	void sulfurasNeverToBeSoldOrDecreaseQuality() {
		Item item = createAndUpdate("Sulfuras, Hand of Ragnaros", 1, 25);
		assertEquals(1, item.sellIn);
		assertEquals(25, item.quality);
	}

	@Test
	void backStagePassesIncreaseInQuality() {
		Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 15, 25);
		assertEquals(26, item.quality);
	}

	@Test
	void backstagePassesIncreaseBy2() {
		Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 25);
		assertEquals(27, item.quality);
	}

	@Test
	void backstagePassesIncreaseBy3() {
		Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 5, 25);
		assertEquals(28, item.quality);
	}

	@Test
	void backstageQualityDropsToZeroAfterConcert() {
		Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 0, 25);
		assertEquals(0, item.quality);
	}

	@Test
	void agedBrieNeverExpires() {
		Item item = createAndUpdate("Aged Brie", 0, 42);
		assertEquals(-1, item.sellIn);
		assertEquals(44, item.quality);
	}

	@Test
	void backstagePassMaximumQuality() {
		Item item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 48);
		assertEquals(50, item.quality);

		item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 10, 49);
		assertEquals(50, item.quality);

		item = createAndUpdate("Backstage passes to a TAFKAL80ETC concert", 5, 49);
		assertEquals(50, item.quality);
	}

	@Test
	void degradeInQualityUnlessSulfuras() {
		Item item = createAndUpdate("foo", -1, 1);
		assertEquals(0, item.quality);
		
		item = createAndUpdate("Sulfuras, Hand of Ragnaros", -1, 1);
		assertEquals(1, item.quality);
	}

	@Test
	void agedBrieMaximumQuality() {
		Item item = createAndUpdate("Aged Brie", -1, 49);
		assertEquals(50, item.quality);
	}
}