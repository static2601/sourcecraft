package converter.actions.actions;

import basic.Loggger;
import converter.actions.Action;
import converter.mapper.Mapper;
import minecraft.Block;
import minecraft.Position;
import minecraft.Property;
import periphery.Periphery;
import periphery.SourceGame;
import vmfWriter.Solid;
import vmfWriter.entity.solidEntity.AddTfPlayerCondition;
import vmfWriter.entity.solidEntity.FuncLadder;

public class Ladder extends Action {

	private Mapper context;

	@Override
	public void add(Mapper context, Position pos, Block block) {
		this.context = context;
		Position end = context.getCuboidFinder()
				.getBestY(pos, block);
		this.handleLadderDirection(pos, end, block);
		this.handleClipDirection(pos, end, block);
		context.markAsConverted(pos, end);
	}

	private void handleLadderDirection(Position pos, Position end, Block block) {
		Position startOffset, endOffset;
		String dir = block.getProperty(Property.facing);
		int pixels = 16;
        switch (dir) {
            case "north":
                startOffset = new Position(0, 0, 15);
                endOffset = new Position(0, 0, 0);
                break;
            case "south":
                startOffset = new Position(0, 0, 0);
                endOffset = new Position(0, 0, 15);
                break;
            case "east":
                startOffset = new Position(0, 0, 0);
                endOffset = new Position(15, 0, 0);
                break;
            default:
                startOffset = new Position(15, 0, 0);
                endOffset = new Position(0, 0, 0);
                break;
        }
		this.context.addDetail(context.createCuboid(
                pos, end, pixels, startOffset, endOffset, block));
	}

	private void handleClipDirection(Position pos, Position end, Block block) {
		Position startOffset, endOffset;

		String dir = block.getProperty(Property.facing);
		int pixels = 8;
        switch (dir) {
            case "north":
                startOffset = new Position(1, 0, 7);
                endOffset = new Position(1, 0, 0);
                break;
            case "south":
                startOffset = new Position(1, 0, 0);
                endOffset = new Position(1, 0, 7);
                break;
            case "east":
                startOffset = new Position(0, 0, 1);
                endOffset = new Position(7, 0, 1);
                break;
            default:
                startOffset = new Position(7, 0, 1);
                endOffset = new Position(0, 0, 1);
                break;
        }

        SourceGame game = Periphery.CONFIG.getGame();

        if (game.getShortName().equals("tf")){
            this.context.addSolidEntity(new AddTfPlayerCondition(this.createArea(
                    this.context, pos, end, pixels, startOffset, endOffset, block), 107, -1));
        }
        else {
            this.context.addSolidEntity(new FuncLadder(this.createArea(
                    this.context, pos, end, pixels, startOffset, endOffset, block)));
        }
	}
    private Solid createArea(Mapper context, Position p, Position end,
            int pixels, Position startOffset, Position endOffset, Block material) {
        return context.createCuboid(p, end, pixels, startOffset, endOffset, material);
    }
}
