package generator_mlreg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MLRegGenerator {

	private static List<String> targetLanguages = List.of("r", "python");
	private static List<String> evaluations = List.of("partition : 20%", "cross_validation : 5");
	private static List<String> algos = List.of("line_regress", "decision_tree_regressor", "svr");
	private static List<String> calculs = List.of("mean_squared_error", "mean_absolute_error", "median_absolute_error");
	
	
	private String datas = "soccer.csv";
	
	private List<String> colsPred = List.of("odds_ft_draw", "odds_ft_away_team_win");
	private List<String> colsTarg = List.of("odds_ft_home_team_win");
	
	
	
	public void generate(String dir) {
		for (String l : targetLanguages) {
			int num = 0;
			for (String eval : evaluations) {
				for (String algo : algos) {
					for (String cal : calculs) {
						num++;
						MLRegProg prog = new MLRegProg(datas, colsPred, colsTarg, l, eval, algo, cal);
						String code = prog.getCode();
						Path dirPath = Path.of(dir);
						Path filePath = Path.of(dir, this.getFileName(l, num));
						try {
							if (! Files.exists(dirPath)) {
								Files.createDirectories(dirPath);
							}
							if (! Files.exists(filePath)) {
								Files.createFile(filePath);	
							}
							Files.write(filePath, code.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	
	private String getFileName(String language, int num) {
		String il = "" + language.charAt(0);
		String in = "" + num;
		if (num < 10) {
			in = "0" + in; 
		}
		
		return il + in + ".mlreg";
	}
}
