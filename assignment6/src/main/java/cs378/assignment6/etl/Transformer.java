package cs378.assignment6.etl;

public interface Transformer {
	public Object transform(Object source) throws Exception;
}
