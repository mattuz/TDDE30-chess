package schack;

public abstract class Pieces
{
    protected String color;

    protected Pieces(final String color){
        this.color = color;
    }

    public String getColor() {
	return color;
    }
}
