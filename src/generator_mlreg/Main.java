package generator_mlreg;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			throw new Exception("length arguments must be 1");
		}
		
		MLRegGenerator gen = new MLRegGenerator();
		gen.generate(args[0]);
	}
	
}
