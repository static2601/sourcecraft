package minecraft;

import basic.NameSupplier;


public enum Property implements NameSupplier {
	east,
	half,
	face,
	facing,
	north,
	open,			// true, false
	shape,			//straight, inner_left, inner_right, outer_left,
	south,
	type,
	waterlogged,
	age, 			// 0 - 7
	rotation,		// 0 - 15
	pickles,		// 1 - 4
	lit, 		    // true, false
	powered,        // true, false
	in_wall,		// true, false
	stage, 			// 0 - 1 ?
	hanging,		// true, false
	signal_fire,	// true, false
	enabled, 		// true, false
	hinge, 			// left, right
	extended, 		// true, false
	falling, 		// true, false
	level, 			// 8 - 1
	delay, 			// 1 - 4
	locked, 		// true, false
	mode, 			// subtract, compare
	power, 			// 0 - 15
	attached, 		// true, false
	axis,			// x, y, z
	has_book,		// true, false
	honey_level,	//
	distance, 		// 0 - 6
	bottom, 		// true, false
	leaves,			// none, small, large
	note, 			// 0 - 24
	instrument, 	// basedrum , ?
	inverted,		// true, false
	attachment,		// floor, ceiling, single_wall
	layers,			// 1 - 8
	up,				// true, false
	down,			//
	west,
	berries,		// true, false
	flower_amount,
	part,			//foot, head
	candles;		//1, 2, 3, 4

	public enum East implements NameSupplier {
		true$,
		false$;
	}

	public enum Half implements NameSupplier {
		top,
		bottom,
		double$;
	}

	public enum Face implements NameSupplier {
		ceiling,
		floor,
		wall
	}

	public enum Facing implements NameSupplier {
		north,
		east,
		south,
		west
	}

	public enum North implements NameSupplier {
		true$,
		false$;
	}

	public enum Open implements NameSupplier {
		true$,
		false$;
	}

	public enum Shape implements NameSupplier {
		inner_left,
		inner_right,
		outer_left,
		outer_right,
		straight
	}

	public enum South implements NameSupplier {
		true$,
		false$;
	}

	public enum Type implements NameSupplier {
		top,
		bottom,
		double$;
	}

	public enum Waterlogged implements NameSupplier {
		true$,
		false$;
	}

	public enum West implements NameSupplier {
		true$,
		false$;
	}
}