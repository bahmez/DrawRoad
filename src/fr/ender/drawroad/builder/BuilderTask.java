package fr.ender.drawroad.builder;

public class BuilderTask {
	
	private BlockBuilder[] blocks;

	public BuilderTask(BlockBuilder[] blocks) {
		this.blocks = blocks;
	}

	public void run() {
		for (int i = 0; i < blocks.length; i++)
			blocks[i].placeBlock();
	}
}
