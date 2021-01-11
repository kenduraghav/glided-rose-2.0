package com.gildedrose.kata;

import static com.gildedrose.kata.GildedRose.AGED_BRIE;
import static com.gildedrose.kata.GildedRose.BACKSTAGE_PASSES;
import static com.gildedrose.kata.GildedRose.CONJURED;
import static com.gildedrose.kata.GildedRose.MAXIMUM_QUALITY;
import static com.gildedrose.kata.GildedRose.SULFURAS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

	private static final String FOO = "foo";

	private Item createAndUpdate(String name, int sellIn, int quality) {
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		return app.items[0];
	}

	@Test
	void junitFrameworkWorks() {
		Item item = createAndUpdate(FOO, 0, 0);
		assertEquals(FOO, item.name);
	}

	@Test
	void systemLowersValues() {
		Item item = createAndUpdate(FOO, 15, 25);
		assertEquals(14, item.sellIn);
		assertEquals(24, item.quality);
	}

	@Test
	void qualityDegradesTwicesAsFast() {
		Item item = createAndUpdate(FOO, 0, 17);
		assertEquals(15, item.quality);
	}

	@Test
	void qualityIsNeverNegative() {
		Item item = createAndUpdate(FOO, 0, 0);
		assertEquals(0, item.quality);
	}

	@Test
	void agedBrieIncreaseQuality() {
		Item item = createAndUpdate(AGED_BRIE, 15, 25);
		assertEquals(26, item.quality);
	}

	@Test
	void qualityIsNeverMoreThanMaximum() {
		Item item = createAndUpdate(FOO, 15, 52);
		assertEquals(51, item.quality);

		item = createAndUpdate(AGED_BRIE, 15, MAXIMUM_QUALITY);
		assertEquals(GildedRose.MAXIMUM_QUALITY, item.quality);
	}

	@Test
	void sulfurasNeverToBeSoldOrDecreaseQuality() {
		Item item = createAndUpdate(SULFURAS, 1, 25);
		assertEquals(1, item.sellIn);
		assertEquals(25, item.quality);
	}

	@Test
	void backStagePassesIncreaseInQuality() {
		Item item = createAndUpdate(BACKSTAGE_PASSES , 15, 25);
		assertEquals(26, item.quality);
	}

	@Test
	void backstagePassesIncreaseBy2() {
		Item item = createAndUpdate(BACKSTAGE_PASSES , 10, 25);
		assertEquals(27, item.quality);
	}

	@Test
	void backstagePassesIncreaseBy3() {
		Item item = createAndUpdate(BACKSTAGE_PASSES , 5, 25);
		assertEquals(28, item.quality);
	}

	@Test
	void backstageQualityDropsToZeroAfterConcert() {
		Item item = createAndUpdate(BACKSTAGE_PASSES , 0, 25);
		assertEquals(0, item.quality);
	}

	@Test
	void agedBrieNeverExpires() {
		Item item = createAndUpdate(AGED_BRIE, 0, 42);
		assertEquals(-1, item.sellIn);
		assertEquals(44, item.quality);
	}

	@Test
	void backstagePassMaximumQuality() {
		Item item = createAndUpdate(BACKSTAGE_PASSES , 10, 48);
		assertEquals(MAXIMUM_QUALITY, item.quality);

		item = createAndUpdate(BACKSTAGE_PASSES , 10, 49);
		assertEquals(MAXIMUM_QUALITY, item.quality);

		item = createAndUpdate(BACKSTAGE_PASSES , 5, 49);
		assertEquals(MAXIMUM_QUALITY, item.quality);
	}

	@Test
	void degradeInQualityUnlessSulfuras() {
		Item item = createAndUpdate(FOO, -1, 1);
		assertEquals(0, item.quality);
		
		item = createAndUpdate(SULFURAS, -1, 1);
		assertEquals(1, item.quality);
	}

	@Test
	void agedBrieMaximumQuality() {
		Item item = createAndUpdate(AGED_BRIE, -1, 49);
		assertEquals(MAXIMUM_QUALITY, item.quality);
	}
	
	@Test
	void conjuredDegradeAsTwiceAsFast() {
		Item item = createAndUpdate(CONJURED, 15, 25);
		assertEquals(23, item.quality);
	}
}